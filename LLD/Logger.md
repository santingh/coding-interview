# Java Logging Library

## High-Level Requirements

1. **Log Levels & Filtering**

   * Support standard levels (`DEBUG`, `INFO`, `WARN`, `ERROR`)
   * Allow per-logger level thresholds
2. **Message Formatting**

   * Pluggable `Layout` interface for formatting log events
   * Provide a default pattern‑based layout
3. **Multiple Destinations (Appenders)**

   * Console, file, and future extensions (e.g., network, database)
   * Pluggable `Appender` interface
4. **Thread Safety & Performance**

   * Safe for concurrent use from many threads
   * Optional asynchronous buffering via a `BlockingQueue<LogEvent>`
5. **Configuration & Extensibility**

   * Register/unregister appenders at runtime
   * Open/Closed: add new appenders or layouts without modifying core

## Hint of Major Trick

Use a `ReentrantLock` to guard the appenders list and I/O in each appender, and decouple logging from I/O by enqueuing `LogEvent` objects into a `BlockingQueue<LogEvent>` drained by a background worker thread.

## High-Level UML

```
        +-------------------+
        |     Logger        |◄───┐
        |-------------------|    │
        | - name:String     |    │
        | - level:LogLevel  |    │
        | - appenders:List  |    │
        | - lock:ReentrantLock | │
        | + log(...):void   |    │
        +-------------------+    │
               ▲  ▲              │
               │  │ uses         │
               │  │              │
   +-----------+  +------------+ │
   |                         |  │ │
+--------+              +---------+--------+
| Layout |              |    Appender      |
|--------|              |------------------|
| +format(LogEvent):String | +append(LogEvent):void |
+--------+              +---------+--------+
                               ▲          ▲
                +--------------+          +-------------+
                |                                     |
        +---------------+                     +--------------+
        | PatternLayout |                     | ConsoleAppender |
        +---------------+                     +--------------+
                                              | - layout:Layout |
                                              | - lock:Lock     |
        +--------------+                      | +append(...)    |
        | FileAppender |                      +--------------+
        +--------------+
        | - layout     |
        | - fileLock   |
        | +append(...) |
        +--------------+
```

## Code Implementation

```java
// LogLevel.java
public enum LogLevel {
    DEBUG, INFO, WARN, ERROR;
}

// LogEvent.java
import java.time.LocalDateTime;
public class LogEvent {
    private final LocalDateTime timestamp;
    private final LogLevel level;
    private final String loggerName;
    private final String message;

    public LogEvent(LogLevel level, String loggerName, String message) {
        this.timestamp = LocalDateTime.now();
        this.level = level;
        this.loggerName = loggerName;
        this.message = message;
    }
    public LocalDateTime getTimestamp() { return timestamp; }
    public LogLevel getLevel()      { return level; }
    public String getLoggerName()   { return loggerName; }
    public String getMessage()      { return message; }
}

// Layout.java
public interface Layout {
    String format(LogEvent event);
}

// PatternLayout.java
public class PatternLayout implements Layout {
    private final String pattern;

    public PatternLayout(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String format(LogEvent event) {
        String formatted = pattern
            .replace("%d", event.getTimestamp().toString())
            .replace("%p", event.getLevel().name())
            .replace("%c", event.getLoggerName())
            .replace("%m", event.getMessage())
            .replace("%n", System.lineSeparator());
        return formatted;
    }
}

// Appender.java
public interface Appender {
    void append(LogEvent event);
}

// ConsoleAppender.java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsoleAppender implements Appender {
    private final Layout layout;
    private final Lock lock = new ReentrantLock();

    public ConsoleAppender(Layout layout) {
        this.layout = layout;
    }

    @Override
    public void append(LogEvent event) {
        String msg = layout.format(event);
        lock.lock();
        try {
            System.out.print(msg);
        } finally {
            lock.unlock();
        }
    }
}

// FileAppender.java
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileAppender implements Appender {
    private final Layout layout;
    private final String filePath;
    private final Lock lock = new ReentrantLock();

    public FileAppender(String filePath, Layout layout) {
        this.filePath = filePath;
        this.layout = layout;
    }

    @Override
    public void append(LogEvent event) {
        String msg = layout.format(event);
        lock.lock();
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

// Logger.java
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Logger {
    private static final Map<String, Logger> INSTANCES = new HashMap<>();
    private final String name;
    private LogLevel level = LogLevel.DEBUG;
    private final List<Appender> appenders = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();

    private Logger(String name) { this.name = name; }

    public static Logger getLogger(String name) {
        synchronized (INSTANCES) {
            return INSTANCES.computeIfAbsent(name, Logger::new);
        }
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public void addAppender(Appender appender) {
        lock.lock();
        try {
            appenders.add(appender);
        } finally {
            lock.unlock();
        }
    }

    public void log(LogLevel lvl, String message) {
        if (lvl.ordinal() < level.ordinal()) return;
        LogEvent event = new LogEvent(lvl, name, message);
        lock.lock();
        try {
            for (Appender app : appenders) {
                app.append(event);
            }
        } finally {
            lock.unlock();
        }
    }

    public void debug(String msg) { log(LogLevel.DEBUG, msg); }
    public void info(String msg)  { log(LogLevel.INFO,  msg); }
    public void warn(String msg)  { log(LogLevel.WARN,  msg); }
    public void error(String msg) { log(LogLevel.ERROR, msg); }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        Logger log = Logger.getLogger("MyApp");
        Layout layout = new PatternLayout("[%d] [%p] [%c] - %m%n");
        log.addAppender(new ConsoleAppender(layout));
        log.addAppender(new FileAppender("app.log", layout));

        log.info("Application started");
        log.debug("Debug details here");
        log.error("An error occurred!");
    }
}
```
