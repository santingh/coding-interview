## Alarm/Alert Service for AWS

### High-Level Requirements
1. **Metric Ingestion**
   - Pull CloudWatch metrics (via SDK or push from Alarm Lambda)  
   - Support custom application metrics (via `PutMetricData`)  
2. **Rule Management**
   - CRUD REST APIs for “Alert Rules” (thresholds, durations, tags)  
   - Persist rules in DynamoDB  
3. **Evaluation Engine**
   - Periodically (or on-demand) fetch metrics, evaluate each rule  
   - Deduplicate flapping alerts (suppress repeat notifications within a cooldown)  
4. **Notification Delivery**
   - Pluggable channels: Email / SMS / Slack / Webhook  
   - Retry with exponential back-off on failures  
5. **Operational Concerns**
   - High throughput: horizontally-scalable (ECS / EKS / Lambda)  
   - At-least-once processing with idempotency keys  
   - Audit log of fired alerts  

---

### Hint of the Major Trick
Use a **ReentrantLock** (or Java’s `StampedLock`) inside your evaluation loop to guard:  
- concurrent rule-updates vs. evaluations  
- alert state transitions (OK → ALERTING → RESOLVED)  

Apply `tryLock` with timeouts to avoid blocking the entire service under high load.

---

### High-Level UML

```mermaid
classDiagram
    class AlertService {
        +start()
        +evaluateAll()
        +addRule(rule: Rule)
        +removeRule(ruleId: String)
    }
    class Rule {
        +id: String
        +name: String
        +condition: Condition
        +channels: List<NotificationChannel>
        +cooldownMillis: long
        +getCondition(condition : Condition)
    }
    interface Condition {
        +evaluate(data: MetricWindow): boolean
    }
    class ThresholdCondition {
        -metricName: String
        -threshold: double
        -op: ComparisonOperator
        +evaluate(window: MetricWindow): boolean
    }
    interface NotificationChannel {
        +send(alert: Alert): void
    }
    class EmailChannel
    class SlackChannel
    class MetricFetcher {
        +fetch(metricName: String, window: Duration): MetricWindow
    }
    class NotificationSender {
        +dispatch(alert: Alert, rule: Rule): void
    }

    AlertService o-- Rule
    Rule o-- Condition
    Rule o-- NotificationChannel
    AlertService --> MetricFetcher
    AlertService --> NotificationSender
```


### Java Code

## 1. Domain & Interfaces


```java
public class Alert {
    private final String ruleId;
    private final Instant timestamp;
    private final double value;
    // getters + ctor
}

public interface Condition {
    boolean evaluate(MetricWindow window);
}

public class ThresholdCondition implements Condition {
    private final String metricName;
    private final double threshold;
    private final ComparisonOperator op; // GT, LT, etc.

    public ThresholdCondition(String metricName, double threshold, ComparisonOperator op) {
        this.metricName = metricName;
        this.threshold = threshold;
        this.op = op;
    }

    @Override
    public boolean evaluate(MetricWindow window) {
        double val = window.getAverage(metricName);
        return op == ComparisonOperator.GT ? val > threshold
             : op == ComparisonOperator.LT ? val < threshold
             : false;
    }
}

public interface NotificationChannel {
    void send(Alert alert);
}

// Example: Email via SNS
public class EmailChannel implements NotificationChannel {
    private final SnsClient sns = SnsClient.create();
    private final String topicArn;

    public EmailChannel(String topicArn) { this.topicArn = topicArn; }

    @Override
    public void send(Alert alert) {
        PublishRequest req = PublishRequest.builder()
            .topicArn(topicArn)
            .subject("ALERT: " + alert.getRuleId())
            .message("Value=" + alert.getValue() + " at " + alert.getTimestamp())
            .build();
        sns.publish(req);
    }
}
```

## 2. Core Service

```java
public class AlertService {
    private final Map<String, Rule> rules = new ConcurrentHashMap<>();
    private final MetricFetcher fetcher;
    private final NotificationSender sender;
    private final ReentrantLock lock = new ReentrantLock();

    public AlertService(MetricFetcher fetcher, NotificationSender sender) {
        this.fetcher = fetcher;
        this.sender  = sender;
    }

    public void addRule(Rule rule) {
        lock.lock();
        try { rules.put(rule.getId(), rule); }
        finally { lock.unlock(); }
    }

    public void removeRule(String ruleId) {
        lock.lock();
        try { rules.remove(ruleId); }
        finally { lock.unlock(); }
    }

    public void evaluateAll() {
        for (Rule rule : rules.values()) {
            boolean triggered = false;
            MetricWindow window = null;
            try {
                if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
                    window = fetcher.fetchWindow(
                       rule.getCondition().getMetricName(), 
                       Duration.ofMinutes(5)
                    );
                    triggered = rule.getCondition().evaluate(window);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                if (lock.isHeldByCurrentThread()) lock.unlock();
            }

            if (triggered && window != null) {
                Alert alert = new Alert(
                   rule.getId(), 
                   Instant.now(), 
                   window.getAverage(rule.getCondition().getMetricName())
                );
                sender.dispatch(alert, rule);
            }
        }
    }

    public void start() {
        ScheduledExecutorService exec = 
           Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(this::evaluateAll, 0, 1, TimeUnit.MINUTES);
    }
}

```

## 3. Notification Sender with Deduplication

```java
public class NotificationSender {
    private final Map<String, Instant> lastSent = new ConcurrentHashMap<>();

    public void dispatch(Alert alert, Rule rule) {
        Instant now = alert.getTimestamp();
        Instant prev = lastSent.get(alert.getRuleId());

        if (prev == null 
         || Duration.between(prev, now).toMillis() 
            >= rule.getCooldownMillis()) {
            for (NotificationChannel ch : rule.getChannels()) {
                ch.send(alert);
            }
            lastSent.put(alert.getRuleId(), now);
        }
    }
}
```
## 4. MetricFetcher Stub (wraps CloudWatch SDK)

```java
public class MetricFetcher {
    private final CloudWatchClient cw = CloudWatchClient.create();

    public MetricWindow fetchWindow(String metricName, Duration window) {
        // build GetMetricStatisticsRequest, parse datapoints...
        return new MetricWindow(...);
    }
}
```

## 5. Wiring it up in main()

```java
public class Main {
    public static void main(String[] args) {
        MetricFetcher fetcher = new MetricFetcher();
        NotificationSender sender = new NotificationSender();

        AlertService svc = new AlertService(fetcher, sender);

        // load rules from DynamoDB, e.g.:
        svc.addRule(new Rule(
          "r1",
          "HighCPU",
          new ThresholdCondition("CPUUtilization", 80.0, ComparisonOperator.GT),
          List.of(new EmailChannel("arn:aws:sns:…")),
          5 * 60 * 1000
        ));

        svc.start();
    }
}
```
