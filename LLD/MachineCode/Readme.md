# Real-World Scenario Practice Questions

1. **Live Traffic Flow Dashboard**
   Your city’s traffic control center receives vehicle-count readings from sensors on every street, updated once per second. At any moment, you need to display the median vehicle count across all sensors to detect abnormal congestion. Design the ingestion and query system so that each new reading is handled in O(log n) time and median retrieval is O(1).

2. **Edge-Node Content Cache**
   You operate a content-delivery network with edge nodes that cache popular video segments. Each node has limited storage and must evict the least-recently-viewed segment when full. Implement the cache API (`getSegment`, `putSegment`) so that both calls run in constant time.

3. **Prize Draw Platform**
   Your online raffle lets users buy numbered tickets (numbers may repeat). You need to support: buying a ticket (`insert`), canceling a ticket (`remove`), and drawing a random ticket (`getRandom`), all in average O(1) time—including when duplicates exist. Design the underlying collection.

4. **E-Commerce Best-Seller List**
   An e-commerce site logs millions of purchases per day. At any time, you must report the top K best-selling products. New purchases stream in constantly. Build a service that processes each sale efficiently and returns the top K list in O(k log n) at query time.

5. **Network Throughput Monitor**
   Your data-center network emits per-packet throughput samples. You need to compute the maximum throughput seen over every 5-minute sliding window, in overall O(n) time as samples stream in. Architect the sliding-window monitor.

6. **Search Suggestion Engine**
   A media-site search box should suggest the 3 most-clicked article titles matching the user’s prefix. Historical click counts update live as users click results. Design the suggestion API to allow fast prefix lookup and dynamic updates.

7. **API Abuse Protector**
   To protect your public API, enforce that each client IP makes at most M calls per minute. Extra calls should be rejected. Provide two variants: one using fixed one-minute windows, and one using a sliding window for smoother rate enforcement.

8. **Chat Moderation Filter**
   In a live chat, you want to highlight the first non-repeated username that sends messages at any point in the stream. As each message arrives, update your “first unique sender” in amortized O(1). Design the message-stream processor.

9. **Real-Time Scoreboard**
   A gaming platform tracks player scores. It must support: `increaseScore(player)`, `decreaseScore(player)`, `getTopScorer()`, and `getLowestScorer()`, all in O(1). Players may appear or disappear at any time. Build the scoreboard data structure.

10. **Flexible Meeting Rooms**
    Your company’s office has multiple meeting rooms. Given existing booked time intervals, implement `book(start, end)` so it checks for overlap and, if free, schedules a new meeting—both in O(log n) time per booking. Design the interval-management system.
