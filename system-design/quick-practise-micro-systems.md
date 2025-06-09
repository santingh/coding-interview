Here’s a curated list of tricky, reusable workflows and patterns to drill—each framed as a bite-sized practice problem rather than a full system:

1. **Distributed Lock Service**
   *Problem:* Design a lock manager (with TTL) that 100 K clients can use to coordinate access to shared resources, handling leader election, renewal, and failover.

2. **Cache Invalidation & Stampede Mitigation**
   *Problem:* For a hot read-heavy service, implement write-through and write-back caching strategies plus “singleflight” or randomized TTLs to prevent thundering herds on cache misses.

3. **Circuit Breaker & Bulkhead Isolation**
   *Problem:* Create a service-to-service call wrapper that tracks error rates, trips a circuit breaker on failures, and isolates “bulkheads” so one faulty downstream cannot cascade.

4. **Idempotent Message Processing**
   *Problem:* Build a consumer that guarantees at-most-once or exactly-once processing of messages (with retries) by using dedupe stores and idempotency keys.

5. **Saga Orchestration with Compensations**
   *Problem:* Orchestrate a multi-step order workflow (reserve inventory, charge payment, notify shipping) with compensating actions if any step fails.

6. **Two-Phase Commit Across Microservices**
   *Problem:* Simulate a 2PC protocol between three services, handling prepare/commit, coordinator failures, and participant lock timeouts.

7. **Back-Pressure & Rate-Limiting on Ingestion**
   *Problem:* Design an API gateway that applies global and per-user rate limits, emits 429s under load, and provides exponential-backoff hints to clients.

8. **Scatter-Gather Fan-Out/Fan-In**
   *Problem:* Parallelize a request by calling N downstream services, collect partial results, handle stragglers with timeouts, and merge into a single response.

9. **Consistent Hashing & Dynamic Sharding**
   *Problem:* Build a key-based routing layer that redistributes load when nodes are added/removed, minimizing key “movement” via virtual nodes.

10. **Geo-Replication & Cross-Region Failover**
    *Problem:* Keep two regions in sync (sync vs. async), manage read/write locality, and design automatic failover on region outage.

11. **Bulk Data Import with Checkpointing**
    *Problem:* Import a 10 TB data dump into a data warehouse, resuming from the last successful batch on failure, and ensuring exactly-once ingestion.

12. **Real-Time Sliding-Window Analytics**
    *Problem:* Maintain per-user counters over sliding windows (e.g. last 5 minutes), supporting high QPS with bounded memory and approximate algorithms (e.g. count-min sketch).

13. **Blue-Green & Canary Deployments**
    *Problem:* Roll out a new version of a service to 5% of traffic, automate health checks, and shift remaining traffic if metrics stay green—or roll back.

14. **Event Sourcing & CQRS**
    *Problem:* Architect a command side that writes append-only events and a query side that builds materialized views, handling replay and projection schema changes.

15. **Chunked Upload & Resume**
    *Problem:* Allow clients to upload large files in chunks, track progress, and resume from the last acknowledged chunk after network failures.

---

Pick any of these “micro-systems” to implement or sketch in 15 minutes—you’ll sharpen the exact patterns that come up again and again in real-world, large-scale designs.
