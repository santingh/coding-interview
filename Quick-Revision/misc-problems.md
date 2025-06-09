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
    
9. **URL Shortener with Expiring Links**  
   - **Scenario:** A high-traffic URL shortening service must generate unique short codes and automatically expire them after **T** days.  
   - **Requirement:**  
     - `create(url)` in **O(1)** average  
     - `resolve(code)` in **O(1)**  
     - `cleanupExpired()` in **O(1)** per expired entry  
   - **Pattern:** Hashing + Min-Heap for expirations + Bi-directional maps  

10. **File Versioning with Delta Storage**  
   - **Scenario:** Store and retrieve file versions efficiently by saving only the differences (deltas) between versions.  
   - **Requirement:**  
     - `commit(versionId, fileContent)` stores delta in **O(d)** where _d_ = size of diff  
     - `checkout(versionId)` reconstructs full file in **O(v + d)** where _v_ = # of versions traversed  
   - **Pattern:** Persistent data structures (copy-on-write) + diff algorithms  

11. **Autocomplete with Typo Tolerance**  
   - **Scenario:** A search box should suggest up to **K** completions for a user’s query, allowing one character insertion/deletion/substitution.  
   - **Requirement:**  
     - Build index on _N_ dictionary words in **O(N·L²)** prep (_L_ = avg word length)  
     - `suggest(prefix)` in **O(L + R)** where _R_ = # of results returned  
   - **Pattern:** Trie + Levenshtein automaton (dynamic programming)  

12. **Distributed Cache with Consistent Hashing**  
   - **Scenario:** A distributed in-memory cache cluster must evenly spread keys across nodes and gracefully handle node additions/removals.  
   - **Requirement:**  
     - Key lookup in **O(log M)** (_M_ = # of virtual nodes)  
     - Rebalance ≤ 1/M of keys on node join/leave  
   - **Pattern:** Consistent hashing with virtual nodes  

13. **Real-Time Approximate Counting (Heavy Hitters)**  
   - **Scenario:** Identify items that occur more than φ fraction of events in a high-speed stream.  
   - **Requirement:**  
     - Process each event in **O(1)** amortized  
     - Report heavy hitters (freq ≥ φ·stream_length) with ≤ ε error  
   - **Pattern:** Misra–Gries algorithm or Count-Min Sketch  

14. **Collaborative Document Editing (CRDT)**  
   - **Scenario:** Multiple users edit a shared document concurrently; updates must merge conflict-free without central coordination.  
   - **Requirement:**  
     - Generate & apply edits in **O(log E)** where _E_ = total edits  
     - Converge to the same state on all replicas  
   - **Pattern:** Vector clocks + CRDT sequence (e.g., RGA or Logoot)  

15. **Real-Time Geospatial Range Queries**  
   - **Scenario:** Ingest moving-object location updates and answer “How many objects are inside this rectangle?” queries on the fly.  
   - **Requirement:**  
     - Update an object’s position in **O(log N)**  
     - Range query in **O(log N + R)** where _R_ = # objects in range  
   - **Pattern:** 2D Range Tree or k-d Tree  

16. **Dependency-Aware Task Scheduler**  
   - **Scenario:** Schedule tasks that have arbitrary dependencies (a DAG), executing ready tasks as soon as all prerequisites finish.  
   - **Requirement:**  
     - `addTask(id, deps[])` in **O(d)** (_d_ = # of deps)  
     - On dependency completion, enqueue dependent tasks in **O(1)** each  
     - `getNextReadyTask()` in **O(1)**  
   - **Pattern:** Topological sort using in-degree counts + queue  

17. **Dynamic Graph Connectivity**  
   - **Scenario:** Maintain an undirected graph under **online** edge insertions/removals and answer connectivity queries in real time.  
   - **Requirement:**  
     - `addEdge(u,v)` in **O(α(N))** amortized  
     - `removeEdge(u,v)` in **O(α(N))** amortized  
     - `isConnected(u,v)` in **O(α(N))** amortized  
   - **Pattern:** Union-Find with rollback or Euler Tour Trees  

18. **Real-Time Prefix Sums with Point Updates**  
    - **Scenario:** Maintain an array under point updates and prefix-sum queries in a high-throughput system.  
    - **Requirement:**  
      - `update(index, delta)` in **O(log N)**  
      - `prefixSum(index)` in **O(log N)**  
    - **Pattern:** Fenwick Tree (Binary Indexed Tree)  

