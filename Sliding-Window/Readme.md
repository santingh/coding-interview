
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



