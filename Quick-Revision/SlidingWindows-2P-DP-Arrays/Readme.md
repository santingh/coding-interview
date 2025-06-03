# Sliding-Window Patterns

### Fixed-Size Window
- Slide a window of size k to compute sums, max/min, averages.  
  _e.g._ “Max sum of any subarray of length k.” or "Subarrays of Size k and Average ≥ Threshold (LeetCode 1343)"
  - Sliding Window Median (LeetCode 480)
  - Sliding Window Maximum (LeetCode 239)


Use below techniques for variable length window. 

### Non-Monotonic Metric

#### Exactly K – O(n)
- Prefix-Sum + HashMap  
- `map.get(sum - k)`  
- `map.put(sum)`

#### At Most K – O(n log n)
- Prefix-Sum + TreeMap  
- `sum(map.tailMap(sum - k).values())`
- Usecase: count subarrays

#### At Least K – O(n log n)
- Prefix-Sum + TreeMap  
- `sum(map.headMap(sum - k).values())`  
- or `(totalSubarrays - atMost(K-1))`

### Monotonic Metric

#### Exactly K – O(n)
- `atMost(K) - atMost(K-1)`

#### At Most K – O(n)
- Sliding-window + two pointers

#### At Least K – O(n)
- `(totalSubarrays - atMost(K-1))`

### Monotonic Deque
1. Sliding-window max/min  
2. “Shortest ≥ K” or “Longest ≤ K” style prefix-sum problems  
3. Any time you need the best candidate at the window’s front, and adding/removing preserves a single-direction order

### Min-Window Substring
- Find the smallest window containing all required chars.  
  _e.g._ LC 76 “Minimum Window Substring.”

### All-Permutations in String
- Check a sliding window for anagram match via freq-arrays.  
  _e.g._ LC 438 “Find All Anagrams in a String.”

### Longest/Shortest Substring with Constraints
- _e.g._ “Longest substring with at most K distinct,” “Longest without repeating.”

### Sum/Avg under Constraint
- _e.g._ “Max average subarray of length ≥ k.”

### Window with Dynamic Condition
- _e.g._ “Fruit into baskets” (at most 2 distinct), “Binary subarrays with sum = S.”

---

# Two-Pointer Patterns

### Pair-Sum in Sorted Array
- Move left/right inward based on sum vs. target.  
  _e.g._ LC 167 “Two Sum II.”

### Container With Most Water
- l/r maximize area → move smaller inward.  
  _e.g._ LC 11

### 3-Sum / 4-Sum
- Fix one (or two) pointers, use two-pointer for the remainder.  
  _e.g._ LC 15 “3Sum.”

### Dutch National Flag / Partition
- Three pointers to sort 0/1/2 or partition by pivot.  
  _e.g._ LC 75 “Sort Colors.”

### In-Place Removal / Duplication
- Overwrite unwanted values as you scan with two pointers.  
  _e.g._ LC 26 “Remove Duplicates from Sorted Array.”

### Merging Sorted Arrays
- Tail-to-head merge with two pointers.  
  _e.g._ “Merge two sorted lists/arrays.”

### Linked List Fast & Slow
- Find middle / detect cycle / remove nth from end.  
  _e.g._ LC 876 “Middle of the Linked List,” LC 142 “Cycle II.”

### Reversal
- Reverse array/string in-place by swapping `l++`, `r--`.  
  _e.g._ “Reverse Words in a String.”

---

# Kadane’s & Extensions

### Classic Kadane’s
- `currentMax = max(num, currentMax + num)` → global max.  
  _e.g._ LC 53 “Maximum Subarray.”

### Max Product Subarray
- Keep both min/max at each step (sign flips).  
  _e.g._ LC 152

### Circular Subarray Sum
- `max(standardKadane, totalSum - minSubarray)`.  
  _e.g._ LC 918 “Max Circular Subarray.”

### One Deletion Allowed
- DP with two states: with/without deletion.  
  _e.g._ LC 1186 “Maximum Subarray Sum with One Deletion.”

### Max Average Subarray
- Binary-search on answer + sliding-window check.  
  _e.g._ “Max average subarray of length ≥ k.”

### Max Sum with Constraints
- Combine Kadane’s with deque for windowed constraints.  
  _e.g._ LC 1425 “Constrained Subsequence Sum.”

---

#### Monotonic Deque Problems
1. **Sliding-Window Extrema**  
   - LC 239, LC 1343  
2. **Shortest ≥ K & Longest ≤ K**  
   - LC 862, LC 962, LC 1438  
3. **At Most / At Least K**  
   - LC 480, LC 713, LC 992  
4. **Maximum-Value Equations & Prefix-Sum + Deque**  
   - LC 1499, LC 1851  
5. **Advanced Prefix-Sum Counting**  
   - LC 325, LC 930, LC 1248

## Practice Problems

- [Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words/)  
- [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)  
- [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/)  
- [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/)  
- [Permutation in String](https://leetcode.com/problems/permutation-in-string/)  
- [Smallest Range Covering Elements from K Lists](https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/)  
- [Minimum Window Subsequence](https://leetcode.com/problems/minimum-window-subsequence/)  
- [Sliding Window Tag](https://leetcode.com/tag/sliding-window/)

### Longest Substring Variants

- Without repeating characters  
- At most 2 distinct characters  
- At most K distinct characters

---

## Extra Arrays Practice Problems

1. **Two-Pointer on Sorted (or Nearly-Sorted) Arrays**  
   - Pattern: move pointers to find pairs/triples with a target  
   - LC 11, LC 15, 3Sum Closest, 4Sum

2. **Prefix-Sum + HashMap for Equality/Count**  
   - Pattern: store counts or first-occurrence of prefix sums  
   - LC 560, Continuous Subarray Sum (multiple of K), Count Number of Nice Subarrays

3. **Binary-Search in an Array Context**  
   - Pattern: search rotated/sorted array or binary-search the answer  
   - LC 33, LC 81, LC 154, Split Array Largest Sum

4. **DP on 1D Arrays**  
   - Pattern: build solutions index by index for max/min/path-count  
   - LC 198, Best Time to Buy and Sell Stock with Cooldown, LC 322, LC 300

5. **Selection & Top-K with Heaps / Quickselect**  
   - Pattern: use a heap or in-place partition for top-k  
   - LC 215, LC 347, LC 378

6. **Bitwise-Xor & Masking Tricks**  
   - Pattern: XOR to cancel pairs or bit-DP subsets  
   - LC 136, LC 260, LC 137, LC 898

7. **Matrix & 2D-Prefix-Sum Extensions**  
   - Pattern: extend prefix-sum logic into 2D  
   - LC 363, LC 304, LC 221



# 📚 LeetCode String & Substring Problems

A curated list of classic LeetCode problems focused on string- and substring-based algorithms, organized by core technique.

---

## 🧩 Backtracking

| Problem                                 | #   | Description                                           |
| --------------------------------------- | --- | ----------------------------------------------------- |
| **[Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/)**             | 131 | Partition a string so that every substring is a palindrome. |
| **[Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses/)**                   | 93  | Generate all valid IP address combinations.           |
| **[Letter Case Permutation](https://leetcode.com/problems/letter-case-permutation/)**             | 784 | Return all strings by toggling letter-case.           |
| **[Generate Parentheses](https://leetcode.com/problems/generate-parentheses/)**                   | 22  | Generate all combinations of well-formed parentheses.  |
| **[Expression Add Operators](https://leetcode.com/problems/expression-add-operators/)**           | 282 | Insert `+`, `-`, `*` to form target expressions.       |
| **[Word Search](https://leetcode.com/problems/word-search/)**                                     | 79  | Find if a word exists in a 2D character grid.         |
| **[Word Search II](https://leetcode.com/problems/word-search-ii/)**                               | 212 | Find all words from a list in a 2D grid.              |
| **[Word Squares](https://leetcode.com/problems/word-squares/)**                                   | 425 | Build square arrays of words that read the same horizontally and vertically. |

---

