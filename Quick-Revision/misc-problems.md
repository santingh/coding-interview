1. **Top-K Trending Topics in a Time Window**  
   - **Scenario:** You have a live feed of social-media posts tagged with topics.  
   - **Requirement:** At any moment, return the top K topics by frequency over the **last T minutes** (sliding window).  
   - **Constraints:**  
     - Amortized **O(log K)** per post update.  
     - Query top K in **O(K)**.  
     - Memory proportional to distinct topics in the window.  

2. **Distributed Global + Per-User Rate Limiter**  
   - **Scenario:** An API gateway cluster must enforce both a per-user limit (e.g. 100 reqs/min) and a global limit (e.g. 10 000 reqs/min) across all nodes.  
   - **Requirement:** Any node can accept/reject a request in **O(1)** time, with at most **one cross-node lookup** per request.  
   - **Challenges:**  
     - Clock skew between nodes.  
     - Avoiding hot-spots and small per-node memory.  

3. **Real-Time Active Session Count**  
   - **Scenario:** A website logs user “heartbeat” events. A session is active if no more than **M minutes** have passed since the last heartbeat.  
   - **Requirement:**  
     - `heartbeat(userId)` in **O(1)** amortized  
     - `getActiveCount()` in **O(1)**  
   - **Twist:** Millions of users, heartbeats can arrive out-of-order by up to **D seconds**.  

4. **First Unique Word in a Sliding Paragraph Window**  
   - **Scenario:** Processing a continuous transcript of dialogue. At each word, find the first word in the **most recent N** words that appears only once within that window.  
   - **Requirement:**  
     - Update per word in **O(1)** amortized.  
     - Support sliding window (drop oldest, add newest) in **O(1)**.  
     - Query first unique in **O(1)**.  

5. **Cancelable Task Scheduler with Priorities**  
   - **Scenario:** A background-job system schedules millions of tasks each with a priority. Tasks may be canceled before execution.  
   - **Requirement:**  
     - `schedule(taskId, priority, timestamp)` in **O(log n)**  
     - `cancel(taskId)` in **O(log n)**  
     - `getNextTask()` → highest-priority, earliest timestamp in **O(1)**  

6. **Real-Time Median for Sliding Numeric Stream**  
   - **Scenario:** Sensors emit a stream of numeric readings. You must report the median over the **last W** readings at each step.  
   - **Requirement:**  
     - Insertion + eviction (oldest reading) in **O(log W)**.  
     - Query median in **O(1)**.  
   - **Twist:** W up to 1 million, arrival rate 100 K/sec.  

7. **Unique Bidder in an Online Auction**  
   - **Scenario:** Bidders place integer bids on an item. You need, at any time, the **highest** bid amount that exactly **one** person has made.  
   - **Requirement:**  
     - `placeBid(userId, bidAmount)` in **O(log n)** amortized  
     - `getHighestUniqueBid()` in **O(1)**  
   - **Constraints:** Millions of users, bid amounts up to 10⁹.  

8. **Real-Time Spam Filter for Chat**  
   - **Scenario:** In addition to tracking unique senders, immediately flag messages whose **content** has been sent more than **K times in the last M minutes**.  
   - **Requirement:**  
     - Update counts and flag in **O(1)** amortized per message.  
     - Evict old message counts as time advances.  
     - Memory bounded by distinct messages in window.  
