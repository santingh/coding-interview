
# Sliding‑Window Patterns

### Fixed‑Size Window
Slide a window of size k to compute sums, max/min, averages.

e.g. “Max sum of any subarray of length k.”


### Non Monotonic Metric

**Exactly K : O(n)**

	- PrefixSum + HashMap
 
	- map.get(sum - k)
 
	- map.put(sum)
 
	

**AtMost K : nLogn**

	- PrefixSum + TreeMap
 
	- sum of map.tailMap(sum - k).values()
 

**At least K : nLogn**

	- PrefixSum + TreeMap
 
	- sum of map.headMap(sum-k).values()
 
	- or (totalSubarrays - atmost(k-1))
 
  

### Monotonic Metric

**Exactly K : O(n)**

	- atmost(k) - atmost(k-1) 
 
	
**AtMost K : O(n)**

	- sliding-window + 2P


**At least K : O(n)**

	- (totalSubarrays - atmost(k-1))
 


### Monotonic Deque

1. Sliding-window max/min
   
2. “Shortest ≥ K” or “Longest ≤ K” style prefix‐sum problems
3. 

Any time you need the best candidate at the window’s front, and adding/removing preserves a single-direction order


### Min‑Window Substring
Find the smallest window containing all required chars.

e.g. LC 76 “Minimum Window Substring.”

### All‑Permutations in String
Check sliding window for anagram match via freq‑arrays.

e.g. LC 438 “Find All Anagrams in a String.”

### Longest/Shortest Substring with Constraints
e.g. “Longest substring with at most K distinct,” “Longest without repeating.”

### Sum/Avg under Constraint
e.g. “Max average subarray of length ≥ k.”

### Window with Dynamic Condition
e.g. “Fruit into baskets” (at most 2 distinct), “Binary subarrays with sum = S.”


# Two‑Pointer Patterns

### Pair‑Sum in Sorted Array
Move l/r inward based on sum vs target.

e.g. LC 167 “Two Sum II.”

### Container With Most Water
l/r maximize area → move smaller inward.

e.g. LC 11.

### 3‑Sum / 4‑Sum
Fix one (or two) pointers, use two‑pointer for remainder.

e.g. LC 15 “3Sum.”

### Dutch National Flag / Partition
Three pointers to sort 0/1/2 or partition by pivot.

e.g. LC 75 “Sort Colors.”

### In‑Place Removal / Duplication
Overwrite unwanted values as you scan with two pointers.

e.g. LC 26 “Remove Duplicates from Sorted Array.”

### Merging Sorted Arrays
Tail‑to‑head merge with two pointers.

e.g. “Merge two sorted lists/arrays.”

### Linked List Fast & Slow
Find middle / detect cycle / remove nth from end.

e.g. LC 876 “Middle of the Linked List,” LC 142 “Cycle II.”

### Reversal
Reverse array/string in‑place by swapping l++, r––.

e.g. “Reverse Words in a String.”

# Kadane’s & Extensions

### Classic Kadane’s
Track currentMax = max(num, currentMax+num) → global max.

e.g. “Maximum Subarray” (LC 53).

### Max Product Subarray
Keep both min/max at each step (sign flips).

e.g. LC 152.

### Circular Subarray Sum
max( standardKadane, totalSum − minSubarray ).

e.g. LC 918 “Max Circular Subarray.”

### One Deletion Allowed
DP with two states: with/without deletion.

e.g. LC 1186 “Maximum Subarray Sum with One Deletion.”

### Max Average Subarray
Binary‑search on answer + sliding‑window check.

e.g. “Max average subarray of length ≥ k.”

### Max Sum with Constraints
Combine Kadane’s with deque for windowed constraints.

e.g. LC 1425 “Constrained Subsequence Sum.”


**Practise Problems**

[Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/)

[Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/description/)

[Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/description/)

[Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/description/)

[Permutation in String](https://leetcode.com/problems/permutation-in-string/description/)

[Smallest Range Covering Elements from K Lists](https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/description/)

[Minimum Window Subsequence](https://leetcode.com/problems/minimum-window-subsequence/description/)

[Sliding Window Tag](https://leetcode.com/tag/sliding-window/)



LongestSubstring - without repeating characters,

Longest Substring - at most 2 distinct characters,

Longest Substring - at most K distinct characters

LongestSubstring - without repeating characters

Day 1 Bitmask & State‑Compression
Trick: encode subsets in bits for 2^n DP/BFS

LC 465 – Optimal Account Balancing
LC 1239 – Maximum Length of a Concatenated String with Unique Characters
LC 1494 – Parallel Courses II



Day 2 Fenwick/Segment Tree & Order‑Statistic DS
Trick: range updates/queries in O(log n)

LC 307 – Range Sum Query Mutable
LC 315 – Count of Smaller Numbers After Self
LC 327 – Count of Range Sum



Day 3 Advanced Graph & DP on DAG
Trick: layered BFS, topo‐sort, DP over DAG, path reconstruction

LC 126 – Word Ladder II
LC 269 – Alien Dictionary
LC 210 – Course Schedule II



Day 4 Trie & Suffix Structures
Trick: multi‐pattern search via prefix trees (with compression/pruning)

LC 212 – Word Search II
LC 820 – Short Encoding of Words
LC 425 – Word Squares



Day 5 Greedy + Heaps (k‑th & Sliding Windows)
Trick: use priority queues for top‑k, medians, skyline, window extremes

LC 239 – Sliding Window Maximum
LC 218 – The Skyline Problem
LC 295 – Find Median from Data Stream

Monotonic Dequeue problems
1. Sliding-Window Extrema

  Pattern: Maintain a monotonic deque to get max or min in O(1) per step

  239. Sliding Window Maximum (classic max in fixed K window)

  1343. Number of Sub-arrays of Size K and Average ≥ Threshold (variant: count windows by checking their min via deque)

2. Shortest ≥ K & Longest ≤ K (Prefix-Sum + Deque)

  862. Shortest Subarray with Sum ≥ K (we’ve done this)

  962. Maximum Width Ramp (same two-pass deque trick to maximize j–i with A[i] ≤ A[j])

  1438. Longest Continuous Subarray With Absolute Diff ≤ Limit (use two deques: one increasing, one decreasing, to maintain max–min ≤ limit)

3. “At Most / At Least K” with Monotonic Two-Deque

Pattern: Two deques (or deques + count) to maintain both window max & min

  480. Sliding Window Median (hard: balance two heaps or two multisets; analogous monotonic-structure practice)

  713. Subarray Product Less Than K (two-pointer but monotonic insights help)

  992. Subarrays with K Different Integers (at-most K & at-most K−1 double-window trick)

4. Maximum-Value Equations & Prefix-Sum + Deque

  1499. Max Value of Equation (deque on transformed values y−x)

  1851. Minimum Interval to Include Each Query (sort by right end + min-heap/deque of left ends)

5. Advanced Prefix-Sum Counting (for contrast)

(Not deque, but good to compare)

  325. Maximum Size Subarray Sum Equals K (hash-map of prefix sums)

  930. Binary Subarrays With Sum = S (two-pointer / prefix count)

  1248. Count Number of Nice Subarrays (odd-even counting via prefix)

Suggested Practice Roadmap
  - Re-implement 862 & 962 from scratch until you can write them in 15 minutes.
  - Mix in the two-deque “max–min ≤ limit” (1438) to see how you juggle two monotonic structures.
  - Contrast with the hash-map counting problems (325, 930) to appreciate why deque only works when you only need one best window, not counts.
  - Finally, tackle 1499 (“value of equation”) to see the same deque idea applied on a transformed sequence.


Extra Arrays Practise Problem:
1. Two-Pointer on Sorted (or Nearly-Sorted) Arrays

  Pattern: move pointers inwards or outwards to find pairs/triples with a target
  Problems to try:
    11. Container With Most Water
    3Sum (and its k-Sum generalizations)
    3Sum Closest
    4Sum

2. Prefix-Sum + Hash‌‌Map for Equality/Count

  Pattern: store counts or first-occurrence of prefix sums in a map to get O(1) lookups
  Problems to try:
    560. Subarray Sum Equals K
    560 variant : Count of subarrays sum = K (just return counts)
    Continuous Subarray Sum (multiple of K)
    Count Number of Nice Subarrays (odd-even pattern)

3. Binary-Search in an Array Context

  Pattern: either search a rotated/sorted array or binary-search the answer (e.g. splitting)
  Problems to try:
    33. Search in Rotated Sorted Array
    Search in Rotated Sorted Array II (with duplicates)
    Find Minimum in Rotated Sorted Array II
    Split Array Largest Sum (binary-search the max-subarray threshold)

4. DP on 1D Arrays

  Pattern: build up solutions index by index for max/min/path-count
  Problems to try:
    198. House Robber (linear DP)
    Best Time to Buy and Sell Stock with Cooldown
    Coin Change (min-coins to make amount)
    Longest Increasing Subsequence (O(n log n) patience solution)


5. Selection & Top-K with Heaps / Quickselect

  Pattern: use a heap or in-place partition to find k-th element or top-k elements
  Problems to try:
    215. Kth Largest Element in an Array (quickselect)
    Top K Frequent Elements (heap or bucket sort)
    Kth Smallest Element in a Sorted Matrix (heap + pointers)

6. Bitwise-Xor & Masking Tricks

  Pattern: use XOR to cancel out pairs, or bit-DP to encode subsets
  Problems to try:
    136. Single Number
    Single Number III (two distinct singles)
    Single Number II (every other appears three times)
    Subarrays with Bitwise OR Equal K

7. Matrix & 2D-Prefix-Sum Extensions

  Pattern: extend prefix-sum logic into two dimensions
  Problems to try:
    363. Max Sum of Rectangle No Larger Than K
    Range Sum Query 2D – Immutable
    Maximal Square (DP on grid)

Day 6 Advanced DP (Interval, Digit, Tree/Matrix)
Trick: interval DP, binary‐search + DP, memoized DFS on grids/trees

LC 312 – Burst Balloons
LC 887 – Super Egg Drop
LC 329 – Longest Increasing Path in a Matrix



