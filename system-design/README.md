### Read-Heavy Systems 
These focus on scale, latency, and efficient data fetching. 
1. Design a URL Shortener (Bitly) 
 → Talk about key generation, collisions, and DB storage. 
 → Add caching and DB sharding if traffic is high.

2. Design an Image Hosting Service 
 → Talk about object storage (S3, GCS) + CDN usage. 
 → Consider image deduplication and resizing strategies.

3. Design a Social Media Platform (Twitter/Facebook) 
 → Talk about posts, timelines, relationships (follows, friends). 
 → Focus on denormalized storage and sharding.

4. Design a NewsFeed System (Hard) 
 → Push vs Pull models, Fanout on Write vs Read. 
 → Caching, pagination, and ranking algorithms.

### Write-Heavy Systems 
Here, durability, throughput, and ingestion speed are critical.

5. Design a Rate Limiter 
 → Token bucket or leaky bucket algorithms. 
 → Redis-backed counters + TTL logic.

6. Design a Log Collection and Analysis System 
 → Use Kafka for ingestion, and something like ELK for processing. 
 → Talk about partitioning, buffering, and real-time querying.

7. Design a Voting System 
 → Idempotency, fraud prevention, and result aggregation. 
 → Real-time vs eventual vote count updates.

8. Design a Trending Topics System 
 → Use count-min sketch or approximate counting. 
 → Talk about sliding window aggregation + ranking.

### Strong Consistency Systems 
Transactional integrity and failure handling become the focus.

9. Design an Online Ticket Booking System 
 → Handle race conditions with locking or optimistic concurrency. 
 → Talk about seat reservation + payment flow.

10. Design an E-Commerce Website (Amazon) 
 → Cover product catalog, cart service, order processing. 
 → Include DB consistency, checkout idempotency.

11. Design an Online Messaging App (WhatsApp/Slack) 
 → Talk about message queues, delivery receipts, retries. 
 → Offline storage, notification delivery, scaling chat infra.

12. Design a Task Management Tool 
 → CRUD APIs, user auth, task assignment. 
 → Background jobs, status updates, and audit trails.

### Scheduler Services 
Timing, reliability, and eventual execution are tested here.

13. Design a Web Crawler 
 → BFS vs DFS for crawling, politeness rules. 
 → Distributed queues, duplicate URL filters.

14. Design a Task Scheduler 
 → Job queues, retry logic, cron-based triggers. 
 → Priority queues and task deduplication.

15. Design a Real-Time Notification System 
 → Push vs Polling, webhooks, and device token mgmt. 
 → Scale delivery across millions of users.

### Trie / Proximity Systems 
Efficient data structures and latency-optimized retrieval.

16. Design a Search Autocomplete System 
 → Trie or Ternary Search Tree backed by frequency rank. 
 → Debouncing, caching, and typo-tolerance.

17. Design a Ride-Sharing App (Uber/Lyft) 
 → Matchmaking engine, real-time location tracking. 
 → Talk about ETA algorithms, surge pricing, DB design.

# System Design Practice Problems

Below are six staff-level, scenario-based system-design prompts. Each problem is described with a set of bullet-pointed requirements and edge cases. Use these to practice scoping, API design, component choices, trade-offs, and failure handling at scale.

---

## 1. Real-Time Collaborative Document Editing Service

**Scenario:**  
Design a Google-Docs–style service where thousands of users can concurrently edit rich-text documents (with formatting, embedded images, and tables).

### Key Requirements & Constraints

- **Low-Latency Collaboration**
  - Edits (insert, delete, format changes) by any user should propagate to all collaborators within ≤ 200 ms.
  - Support offline edits: users may go offline, continue editing, and then reconnect; their changes must merge without data loss.

- **Conflict Resolution & Consistency**
  - Guarantee **eventual consistency** with no “lost” edits.
  - Use an algorithm such as **CRDT (Conflict-Free Replicated Data Type)** or **Operational Transformation (OT)** to reconcile concurrent edits across multiple replicas and offline state.
  - Handle multi-document concurrency (e.g., two users editing the same paragraph vs. different paragraphs).

- **Rich Content Model**
  - Document model must support:
    - Plain text + styling (bold, italic, lists).
    - Embedded objects: images, tables, code blocks.
    - Comments and suggestions (“track changes”).
  - Changes to large embedded objects (image replace) should not block small text edits.

- **Access Control & Sharing**
  - Per-document ACLs: Owner, Editor, Commenter, Viewer.
  - Real-time permission changes (e.g., Owner revokes Editor rights mid-session) must take effect immediately (within < 1 s).

- **Scalability & Multi-Region**
  - Target: 1 M active users, 100 K concurrent edits.
  - Users worldwide—minimize cross-region latency by using geo-distributed replicas.
  - If a region suffers an outage, clients must fail over to a nearby region and continue editing with minimal data loss.

- **Durability & History**
  - Persistent version history: unlimited undo/redo.
  - Ability to revert to any past state.
  - Store full change log (every operation) for auditing.

- **Non-Functional Considerations**
  - **Throughput:** 100 K ops/sec under peak.
  - **Latency SLO:** 99th percentile end-to-end update propagation ≤ 200 ms.
  - **Fault Tolerance:** No single point of failure.
  - **Security:** TLS in transit, encryption at rest for documents and metadata.
  - **Mobile & Web Clients:** Efficient bandwidth usage; drop reconnect and diff resolution gracefully.

---

## 2. Dynamic Ride-Sharing Surge-Pricing & Dispatch Engine

**Scenario:**  
Design a real-time engine for a ride-sharing platform (e.g., Amazon Rides) that handles surge pricing, driver dispatch, and real-time fare recalculation when demand spikes.

### Key Requirements & Constraints

- **Real-Time Demand/Supply Matching**
  - Continuous stream of **ride requests** (user → location A → destination B) and **driver availability updates** (driver’s GPS coordinates, online/offline).
  - Match riders with the “best” driver in ≤ 100 ms (considering proximity, driver rating, and current traffic).

- **Surge Pricing Algorithm**
  - When rider requests in a given geo-zone exceed driver availability thresholds (e.g., demand > supply × 1.2), automatically increase fare by a surge multiplier (e.g., × 1.5).
  - Surge must adjust every minute based on real-time data.
  - Never apply inconsistent fares: a rider’s fare must be locked in at request time even if surge factor fluctuates mid-trip.

- **Stateful Processing**
  - Maintain **per-zone** counters of pending requests vs. available drivers.
  - Guarantee that a driver can only be matched to one rider at a time.
  - If a match is not accepted in 15 s, requeue the rider for the next available driver.

- **High Availability & Scalability**
  - Support **20 K requests/sec** at peak in a large metro; scale to 200 K globally.
  - Partition data by geo-zones or grid cells; replicas in multiple regions.
  - Failover: if a zone’s dispatcher goes down, neighboring region dispatchers must pick up.

- **Pricing Consistency**
  - All rider “cost–estimates” shown on the app must match final fare after ride completes (except tolls/taxes).
  - Ensure no under- or overcharging due to state desync.

- **Data Storage & Analytics**
  - Store historical ride data (pickup, dropoff, timestamp, fare, surge factor) for BI and ML.
  - Real-time analytics dashboard: current active rides, supply/demand heatmaps, top surging zones.

- **Edge Cases & Fault Handling**
  - **Driver Cancels After Accept:** Re-match rider, possibly reapply a new surge.
  - **Network Partition between Dispatcher and GPS Stream:** Avoid double dispatch to drivers.
  - **Clock Skew:** Ensure consistent timekeeping for surge windows (use NTP or logical clocks).

- **Security & Privacy**
  - Rider’s pickup/dropoff should be visible only to assigned driver and logged securely.
  - GDPR/CCPA compliance: anonymize location data older than 30 days.

---

## 3. Multi-Tenant Distributed Cache with Strong Consistency

**Scenario:**  
Design a SaaS offering that provides a **distributed, in-memory key-value cache** (like Amazon ElastiCache) that supports **multiple tenants** (customers). Each tenant wants: fine-grained TTL, strong consistency on reads/writes, and data isolation.

### Key Requirements & Constraints

- **Data Model & API**
  - Standard operations: `GET(key)`, `SET(key, value, TTL)`, `DEL(key)`, `INCR(key)`.
  - Support **transactions** (MULTI/EXEC style) to update multiple keys atomically for a given tenant.
  - Tenants can define **per-key policies**: max TTL, encryption at rest.

- **Isolation & Multi-Tenancy**
  - Each tenant’s data must be strictly isolated. No tenant should “see” or slow down others.
  - Billing per-GB-month of memory used + number of operations.
  - Transparent sharding: tenants can autoscale from 1 GB → 100 GB cache.

- **Strong Consistency Guarantees**
  - After a successful `SET`, any subsequent `GET` (even from another replica) must reflect the new value.
  - No stale reads allowed.
  - Achieve via **synchronous replication** across at least two nodes per shard.

- **Partitioning & Sharding Strategy**
  - **Hash-based Sharding:** Each tenant’s keyspace is hashed across multiple shards if they exceed a single node’s capacity.
  - **Dynamic Rebalancing:** When a tenant’s dataset grows, re-partition without downtime or data loss.

- **Replication & Failover**
  - Primary-Replica pairs with automatic failover: if primary dies, promote replica.
  - Guarantee **zero data loss** on failover (acks=majority–style replication).
  - Fast reconnection for clients (e.g., use a cluster-endpoint DNS).

- **Performance & Scaling**
  - 1 ms median `GET` latency.
  - 10 Gbps network per node.
  - Support 50 K ops/sec per GB of memory.

- **Access Control & Security**
  - Tenant-specific authentication tokens.
  - In-transit encryption (TLS), plus optional at-rest encryption.
  - IP whitelisting and VPC peering options.

- **Cache Eviction & TTL Policies**
  - Per-tenant eviction policy: LRU, LFU, or TTL-only.
  - Support millions of keys per shard; eviction decisions must run in O(1) or O(log N).
  - Monitor eviction rates; alert when > 10% of writes cause evictions.

- **Monitoring & Billing**
  - Track per-tenant memory usage, ops/sec, hit/miss ratios.
  - Provide dashboards and billing reports.

- **Edge Cases & Trade-Offs**
  - **Hot Keys:** If a single key is “hot” (10 K req/s) for a tenant, handle via in-node replication or read-through to persistent store?
  - **Network Partition within Cluster:** Decide whether to block writes (CP) or allow stale reads (AP). Must be CP (no stale reads).
  - **Rebalancing Overhead:** Live migration of slots vs. rolling restarts.
  - **Tenant Overuse:** Limit per-tenant ops to prevent noisy neighbor issues; implement request rate-limiting.

---

## 4. Personalized News Feed Ranking with Real-Time Updates

**Scenario:**  
Design a social-media–style feed (like Amazon Social) that ranks and serves personalized news feed items (posts, ads, updates) for each user in real time.

### Key Requirements & Constraints

- **Personalization & Ranking**
  - Each user sees a personalized feed sorted by a ranking score (engagement probability).
  - Ranking model factors: recency, user’s interests, friend interactions, ad bids, and geolocation.
  - Recompute scores continuously as new signals arrive (likes, comments, new posts, new ad budgets).

- **Scale & Performance**
  - **1 billion** MAU (monthly active users).
  - Each second: 100 K new posts, 1 M likes/shares/comments.
  - Generate feed per user on request within < 100 ms p99.

- **High-Throughput Data Ingestion**
  - Ingest streams:
    - **Post Events:** `{postId, author, timestamp, contentMetadata}`.
    - **User Activity Events:** `{userId, actionType, timestamp}`.
    - **Ad Budget Updates:** `{adId, bidAmount, targetingCriteria}`.
  - Use **Apache Kafka** or similar for durable ingestion.

- **Feed Generation Architecture**
  1. **Candidate Generation**
     - Precompute a small set (~500) of candidate posts from:
       - User’s friends, followed pages, relevant ads.
       - Use offline ML to identify potential candidates (behavioral signals).
     - Candidate sets updated every 5 minutes (per user).

  2. **Real-Time Scoring Layer**
     - As new events arrive (friend posts, comment, like), recompute rank for that candidate in ~1 ms.
     - Use in-memory store (e.g., Redis or DynamoDB Accelerator) to hold per-user candidate lists & scores.

  3. **Feed View API**
     - When user opens app or scrolls, call:
       ```
       GET /feed?userId=XYZ&limit=20
       ```
     - System returns top-20 high-score items from in-memory sorted data.
     - If less than 20 “fresh” items, fallback to some static timeline or “popular” items.

- **Ad Insertion & Budget Control**
  - Ads must be inserted within the personalized feed at a frequency limit (e.g., 1 ad per 10 posts).
  - An ad’s remaining budget and real-time bid may cause its insertion position (higher bid = higher rank).
  - Guarantee no ad appears if budget exhausted.

- **Caching & Pre-Aggregation**
  - Per-user precomputed feed stored in a cache (Redis cluster) to reduce recompute.
  - TTL for cached feed = 30 s; after that, recompute or refresh.

- **Consistency & Freshness Trade-Offs**
  - Strict “read-your-writes” for user’s own posts: once a user posts, they should see it in their feed immediately (< 200 ms).
  - For others’ posts, can tolerate ≤ 5 seconds of propagation delay.

- **Fault Tolerance & Failover**
  - Ranked feed pipelines must be stateless or keep local state, so auto-scale and recover from node failure.
  - For in-memory caches (Redis), use replica-failover and automated recovery.

- **Monitoring & Metrics**
  - **Feed Latency:** time from user request → 20 items returned (p50/p99).
  - **Event Processing Lag:** difference between event ingestion and availability for scoring.
  - **Cache Hit Rate:** for per-user feed.
  - **Ad Budget Consumption Rate:** alerts if overspend.

- **Edge Cases & Challenges**
  - **Cold-Start Users:** no historic data → fill with trending items and broad topics.
  - **“Spammy” Content:** ML inference must detect and filter out low-quality posts < 2 s per post.
  - **Personal Data Privacy:** comply with GDPR; let users delete all data and remove from feed index.

---

## 5. IoT Sensor Data Ingestion & Real-Time Anomaly Detection Pipeline

**Scenario:**  
Design an IoT backend for tens of thousands of industrial sensors (e.g., on Amazon factories) sending temperature, vibration, and pressure readings every second. The system must ingest data, run real-time anomaly detection, and send alerts to operators.

### Key Requirements & Constraints

- **High-Throughput Ingestion**
  - **100 K sensors**, each sending 3 metrics per second → 300 K data points/sec.
  - Data is small (< 100 bytes each), but volume accumulates rapidly.
  - Ingestion endpoints must handle bursts of 500 K req/sec.

- **Data Pipeline**
  1. **Edge Gateway**
     - Sensors push to a lightweight gateway (MQTT broker) at the edge.
     - Gateway aggregates into batches (e.g., 50 msgs) to reduce overhead.
     - Encrypt in transit with TLS.
  2. **Cloud Ingestion Tier**
     - **Stream Processor (Kafka)** backed by a cluster with partitions keyed by `sensorId`.
     - Consumers read from Kafka in real time.

- **Real-Time Anomaly Detection**
  - **Sliding Window Analysis:** For each sensor, maintain a rolling 5-minute window to compute statistics (mean, standard deviation).
  - **Threshold Alerts:** If a reading > 3 σ from mean or exhibits spike/trend anomaly (e.g., 10% increase over last 10 s), fire an alert.
  - **Machine-Learning Model:** Optionally run a lightweight LSTM model per sensor group to catch subtle anomalies.
  - **Latency SLO:** Pipeline must detect anomalies and deliver alert to operator within ≤ 500 ms from reading.

- **Stateful Stream Processing**
  - Use a framework like **Apache Flink** or **Kafka Streams**:
    - Per-sensor keyed state to maintain window aggregates.
    - Model inference (if ML used) on per-group basis (e.g., 1 LSTM per 1,000 sensors).
  - Fault tolerance: exactly-once semantics so no data loss or duplicated anomaly alerts.

- **Storage & Historical Analysis**
  - Ingest all raw readings into a **time-series database** (e.g., InfluxDB or DynamoDB TimeStream) for offline analytics and dashboarding.
  - Use a **long-term cold storage** (S3) to store compressed data for 7 years.

- **Alerting & Notification**
  - If anomaly detected:
    1. Write an **alert event** to Kafka `alerts` topic.
    2. A downstream consumer picks up alerts and sends notifications via SMS, email, or in-app push to relevant operators (on-call).
    3. Deduplicate repeated alerts for the same sensor within 5 minutes.
  - Prioritize alerts: critical (shutdown needed), warning (monitor), info.

- **Edge Cases & Challenges**
  - **Sensor Offline/Bursty Data:** If sensor stops sending (offline), generate a “missing-data” alert after 10 s gap.
  - **Clock Skew:** Sensors’ local clocks may drift; buffer timestamps at ingestion and rely on server timestamp.
  - **Backpressure:** If downstream anomaly service lags, must buffer at Kafka—avoid dropping data.
  - **Network Partition at Edge:** Gateway must buffer sensor data locally (e.g., local disk) until connectivity restores.

- **Security & Device Management**
  - Each sensor has a device certificate (X.509). Mutual TLS for authentication.
  - Device registry: onboard/offboard sensors. Revoke certificates centrally.

- **Monitoring & Metrics**
  - **Ingestion Lag:** difference between sensor event time and processing time (p50/p99 < 200 ms).
  - **State Store Size:** per-sensor window state metrics.
  - **Anomaly Alerts Rate:** alerts/sec, false positives ratio.
  - **Backpressure alerts** if Kafka consumer lag > 50 K messages.

---

## 6. Distributed E-Commerce Inventory & Checkout System with Stock Consistency

**Scenario:**  
Design the inventory and checkout subsystem for a high-traffic e-commerce platform (like Amazon Retail) that must handle flash sales (e.g., Prime Day) without overselling.

### Key Requirements & Constraints

- **Inventory Model & Operations**
  - SKU-level inventory counts for millions of products across multiple warehouses.
  - **Operations:**
    - `ReserveItem(userId, sku, qty)` → temporarily reserve stock for a cart.
    - `CommitOrder(orderId)` → deduct permanently from inventory.
    - `ReleaseReservation(reservationId)` → release reserved stock on cart expire or cancel.
  - Each reservation has a **TTL** (e.g., 15 minutes) before automatic release.

- **Strong Consistency on Stock**
  - Prevent overselling: if only 100 units left, no combination of concurrent reservations/commits can exceed 100.
  - Guarantee **linearizable** reservations and commits.

- **High Throughput & Low Latency**
  - Peak: 10 K **Add to Cart** reservations/sec and 5 K **Checkout Commits**/sec.
  - Flash Sale spikes: spikes to 50 K reservations/sec for 5 minutes.
  - Satisfy < 100 ms end-to-end latency for reservation and commit.

- **Distributed Architecture**
  - **Shard by SKU Range or Warehouse:**
    - Partition inventory data so that each shard manages a set of SKUs (e.g., by hash or by warehouse zone).
    - Each partition should handle ~10 K ops/sec normally.
  - **Primary-Replica** model for each shard: synchronous replication to ensure no lost writes.
  - **Optimistic Locking vs. Atomic Counters:**
    - Use an atomic decrement (e.g., Redis `INCRBY –1` or database row lock with `UPDATE ... WHERE stock >= qty`).
    - If insufficient stock, return “out of stock.”

- **Reservation Timeout Handling**
  - Maintain a **Reservation Table**:  
    ```sql
    reservationId  PK  
    orderId  
    sku  
    qty  
    expiresAt  
    status  
    ```
  - A background job (or TTL index) scans for `expiresAt < now AND status=RESERVED`, then:
    - Transition `status=EXPIRED`.
    - Increment inventory back by `qty` (atomic).
  - Ensure idempotence: if commit tried after TTL, reject.

- **Cart Service Interaction**
  - When user clicks “Add to Cart”:
    1. Call `ReserveItem(…)` API.
    2. If successful, update Cart UI.
    3. If fail (stock insufficient), show “Out of Stock.”

- **Checkout Service Interaction**
  - On “Place Order”:
    1. Call `CommitOrder(orderId)`.
    2. Cart service sends all `reservationIds` tied to `orderId` to Inventory service.
    3. Inventory service atomically changes status to `COMMITTED` and does not re-release on TTL.
  - If payment fails, call `ReleaseReservation` explicitly for all reservations.

- **Atomicity & Exactly-Once Semantics**
  - Use a **distributed transaction** (e.g., Saga pattern) where:
    1. **Step 1:** Reserve stock → inventory shard → write `(reservationId, status=RESERVED)`.
    2. **Step 2:** Payment processing (third-party).
    3. **Step 3:** If payment succeeds → Commit → update `reservation.status=COMMITTED`.
    4. **Step 4:** If any step fails → Release → update to `CANCELLED`, add back stock.
  - Ensure compensating actions are idempotent.

- **Data Storage & Caching**
  - **Primary Storage:** Relational DB (e.g., Aurora) or NewSQL (e.g., Spanner) for durable inventory and reservation tables.
  - **Cache Layer:**
    - Use Redis for hot-item stock counts (flash sale SKUs).
    - Use atomic Redis commands (Lua scripts) for decrement/increment to avoid race.
    - Fall back to RDBMS when cache misses.

- **Analytics & Monitoring**
  - **Real-Time Dashboards:** track inventory levels, reservation rate, commit rate, TTL expirations.
  - **Alerts:**
    - If reservation rate spike > ×5 baseline for > 1 minute, autoscale capacity.
    - If commit success ratio < 98% (payment failures or stock inconsistencies).

- **Edge Cases & Failure Handling**
  - **Network Partition:** If Inventory service can’t reach RDBMS, return “temporary unavailable” to user; don’t allow stale reservations.
  - **Double-Reservation Attack:** Prevent a malicious client from repeatedly calling `ReserveItem` to hold stock without paying. Enforce per-user reservation limits or CAPTCHAs.
  - **Warehouse Sync Delays:** If inventory is updated by offline warehouse batch (e.g., returns), ensure near-real-time sync to cache (via CDC/Kafka).
  - **Out-Of-Sequence Requests:** If a user’s reservation TTL expires right as they click “Checkout,” ensure either reservation still valid or fail gracefully.

- **Security & Auditing**
  - All inventory changes (reserve/commit/release) must be **logged** in an audit trail with timestamps, userId, sku, qty.
  - Role-based access: admins can override stock levels; normal users cannot.

---

## Practice Tips for Each Scenario

1. **Clarify Scope & Requirements**
   - Ask which sub-problem to prioritize (consistency vs. latency, geo-distribution vs. simplicity).

2. **Define Data Models & APIs First**
   - Sketch table/schema upfront—this guides downstream discussion.

3. **Choose Key Components & Technologies**
   - Justify choices (e.g., why Redis vs. DynamoDB; Kafka vs. Kinesis).

4. **Draw a High-Level Diagram**
   - Show clients → API layer → queue/stream → processing → storage.

5. **Deep-Dive on a “Tricky” Aspect**
   - Show your understanding of distributed locks, CRDT vs. OT, exactly-once vs. at-least-once processing, etc.
   - Illustrate how you’d handle failure modes or race conditions.

6. **Discuss Trade-Offs & Alternatives**
   - E.g., opting for eventual consistency to reduce latency, or synchronous replication to avoid overselling.

7. **Outline Monitoring & Alerting**
   - Every staff-level design should include how you’ll know if things go wrong and recover quickly.

---

*Use this document as a living reference. As you solve each problem, sketch diagrams, write sample APIs, and outline failure-handling strategies. Good luck with your preparation!*

