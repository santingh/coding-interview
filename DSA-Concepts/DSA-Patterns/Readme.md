### Array / String Inputs

1. Is the array sorted? 
→ Use Binary Search, Two Pointers, or Prefix Sums

2. Optimization problems (Max/Min/Subarray)? 
→ Think Sliding Window, Dynamic Programming, or Greedy

3. Looking for duplicates / counts / frequencies? 
→ Use HashMap, HashSet, or Counting Array

4. Need substrings or fixed-size subarrays? 
→ Apply Sliding Window with Two Pointers

5. Frequent min/max in window? 
→ Use Monotonic Queue, Deque, or Heap

6. Generating subsets, permutations, combinations? 
→ Use Backtracking

7. Matching / parsing characters? 
→ Use Stack, especially for Balanced Parentheses, Infix/Postfix

### Graph Inputs

1. Shortest path in unweighted graph? 
→ Use Breadth-First Search (BFS)

2. Weighted shortest path? 
→ Use Dijkstra, Bellman-Ford, or A\

3. Connected components / cycle detection? 
→ Use DFS, Union-Find (DSU)

4. Topological ordering? 
→ Use Kahn’s Algorithm or DFS + visited set

5. Optimization like MST? 
→ Use Kruskal or Prim’s Algorithm

 ### Tree Inputs (Often Binary Trees)

1. Traversals? 
→ Use Inorder, Preorder, Postorder, or Level-order (BFS)

2. Balanced checks or diameter calculations? 
→ Use Postorder + height calculations

3. Lowest Common Ancestor? 
→ Use Recursive DFS or Parent Map + Ancestor Set

 ### Linked List Inputs

1. Detecting cycles? 
→ Use Slow and Fast Pointers (Floyd’s Algorithm)

2. Reversals / partial changes? 
→ Use pointer juggling: prev, curr, next

3. Intersection or middle node? 
→ Use Two Pointers

 ### Dynamic Programming Use-Cases

1. Optimal choices / Overlapping subproblems? 
→ Use DP with Memoization (Top Down) or Tabulation (Bottom Up)

2. Subset or knapsack problems? 
→ Use 1D/2D DP Arrays

3. String matching or edits? 
→ Use DP Matrix (e.g., Edit Distance, LCS)

### Range Queries / Updates

1. Many sum queries, no updates? 
→ Use Prefix Sums

2. Many sum queries + updates? 
→ Use Segment Tree or Fenwick Tree (Binary Indexed Tree)

 ### Bit Manipulation

1. Set-based subsets or XOR logic? 
→ Use Bit Masks or XOR

2. Need to check even/odd, set/unset bits? 
→ Use `&`, `|`, `^`, `>>`, `<<` operators

 ### When Recursion is Banned or Stack Overflow Risk

→ Convert to Iterative using Stack

 ### Top K / Least K Elements

→ Use Heap 
→ For exact K-th value, use Quick Select

 ### Special Techniques

Sliding Window → For subarray with fixed or dynamic size 
Monotonic Stack → Next Greater / Smaller Element 
Greedy → Only when local optimum leads to global optimum 
Trie → For prefix-based string problems

![Alt text](1744697472288.jpeg?raw=true "Title")

