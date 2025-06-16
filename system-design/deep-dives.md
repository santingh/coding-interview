Let’s turn “spotting the right pattern + sketching the workflow” into a muscle you can flex. Below are **six drill-style prompts**. For each:

1. **Identify the core design pattern(s)** (e.g. windowed stream-aggregation, batch ETL, request/response lookup, event-sourcing, graph-traversal, etc.).
2. **Outline a 4–5-step high-level workflow** (Ingest → Process → Compute → Persist → Serve).
3. **Name one non-functional trade-off** you’d call out (e.g. latency vs cost, accuracy vs throughput, consistency vs availability).

---

## 1. Dynamic Surge Pricing for E-Scooters

> “Every scooter reports GPS + battery every 10s. When city-zone demand spikes, rates should jump by 25% for the next 5 minutes in that zone. Riders should see updated prices in real time.”

* **Pattern?**
* **Workflow sketch?**
* **Key trade-off?**

---

## 2. Fraud-Alert for High-Value Card-Not-Present Transactions

> “Flag if two transactions from the same card happen in geographically distant locations within 2 minutes (e.g. one in Delhi, next in Mumbai).”

* **Pattern?**
* **Workflow sketch?**
* **Key trade-off?**

---

## 3. Personalized “What to Watch Next” on Video Platform

> “Suggest videos based on (a) what a user watched in their last session, (b) what 1,000 most-similar users are watching now, and (c) trending titles in the past hour.”

* **Pattern?**
* **Workflow sketch?**
* **Key trade-off?**

---

## 4. Real-Time Fleet Health Monitoring

> “Trucks stream OBD-II metrics every second. Detect engine-temperature outliers over a 30s sliding window and send an alert if avg. temp > threshold.”

* **Pattern?**
* **Workflow sketch?**
* **Key trade-off?**

---

## 5. “Read-Your-Writes” Social Feed

> “Users post updates; followers should always see their own posts instantly, but can tolerate up to 2-second delay for other users’ posts.”

* **Pattern?**
* **Workflow sketch?**
* **Key trade-off?**

---

## 6. Warehouse Slotting Optimization

> “Every incoming order triggers a pick-path recompute for its items. Optimize for shortest total travel distance across all active pickers.”

* **Pattern?**
* **Workflow sketch?**
* **Key trade-off?**

---

### How to Practice

* **Time-box** yourself:

  * 3 min to name the pattern,
  * 7 min to draw your 5-step flow,
  * 5 min to note trade-offs and key metrics (e.g. throughput, p99 latency, staleness).
* **Compare** to a model answer (I can provide after you’ve sketched).
* **Iterate**: swap in new parameters (longer windows, geo-joins, secondary lookups) to see how the pattern shifts.

Whenever you’re ready, pick one scenario, walk through your answer, and I’ll give you a critique + alternate approaches.
