# Algorithm & Data Structure Cheat Sheet

## Table of Contents

1. [2D Array Coordinates ↔ Integer](#1-2d-array-coordinates-↔-integer)
2. [Integer ↔ Character Conversion](#2-integer-↔-character-conversion)
3. [Stack Usage](#3-stack-usage)
4. [Subarray Count with Exact Criteria](#4-subarray-count-with-exact-criteria)
5. [Shortest Path (BFS vs. DFS)](#5-shortest-path-bfs-vs-dfs)
6. [Cycle Detection (DFS)](#6-cycle-detection-dfs)
7. [Disjoint Set (Union Find) Overview](#7-disjoint-set-union-find-overview)
8. [Binary Tree ↔ Array Representation](#8-binary-tree-↔-array-representation)
9. [Sorting a 2D Array Ascending](#9-sorting-a-2d-array-ascending)
10. [Union Find Pattern Implementation](#10-union-find-pattern-implementation)
11. [Backtracking Pattern](#11-backtracking-pattern)
12. [Min-Max Heap](#12-min-max-heap)
13. [Divide & Conquer Template](#13-divide--conquer-template)
14. [Collection Copies](#14-collection-copies)
15. [Bellman–Ford Algorithm](#15-bellman–ford-algorithm)
16. [Dijkstra’s Algorithm](#16-dijkstra’s-algorithm)
17. [Topological Sort](#17-topological-sort)
18. [Minimum Spanning Tree (Kruskal’s)](#18-minimum-spanning-tree-kruskal’s)
19. [Kadane’s Algorithm Patterns](#19-kadane’s-algorithm-patterns)
20. [Monotonic Stack Patterns](#20-monotonic-stack-patterns)
21. [Fast & Slow Pointer Template](#21-fast--slow-pointer-template)
22. [Tree Maximum Path Sum Pattern](#22-tree-maximum-path-sum-pattern)

---

## 1. 2D Array Coordinates ↔ Integer

Convert between a pair of `(row, col)` and a single integer ID, and explore four directions:

```java
// (row, col) → id
int id = row * colCount + col;
// id → (row, col)
int row = id / colCount;
int col = id % colCount;

// Explore all four directions
int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
int row = currPos / matrix[0].length;
int col = currPos % matrix[0].length;

for (int i = 0; i < 4; i++) {
    int newD = (currPos + i) % 4;
    int newRow = row + directions[newD][0];
    int newCol = col + directions[newD][1];
    // …process (newRow, newCol)
}
```

## 2. Integer ↔ Character Conversion

```java
// char digit → int
int n = cArray[i] - '0';
// int → char digit
cArray[i] = (char) (n2 + '0');
```

## 3. Stack Usage

Use a deque as a stack when you need LIFO processing:

```java
Deque<Character> stack = new LinkedList<>();
```

## 4. Subarray Count with Exact Criteria

Count subarrays whose sum (or other metric) equals `k` using prefix-sum hashing:

```java
public int countExact(int[] arr, int k) {
    Map<Integer, Integer> counts = new HashMap<>();
    counts.put(0, 1);
    int ans = 0, curr = 0;

    for (int num : arr) {
        // update curr according to your metric
        ans += counts.getOrDefault(curr - k, 0);
        counts.put(curr, counts.getOrDefault(curr, 0) + 1);
    }
    return ans;
}
```

* If the metric is **monotonic** (only ↑ or only ↓), use the two-pointer “atMost/Exactly” trick.
* Otherwise, use **prefix sums + hashing**.

## 5. Shortest Path (BFS vs. DFS)

1. **BFS** explores level by level → guarantees shortest unweighted path.
2. **DFS** can find *a* path, but not necessarily the shortest (especially with weights).

## 6. Cycle Detection (DFS)

* DFS is ideal for topological sorting, cycle detection, or path-search when path length doesn’t matter.

## 7. Disjoint Set (Union Find) Overview

Used to track connectivity in networks (computer or social):

* **find(x)**: returns representative of x’s component
* **union(x, y)**: merges two components
* **connected(x, y)**: checks if they share a root

## 8. Binary Tree ↔ Array Representation

```java
// 1-based indexing:
int parent = i / 2;
int left   = i * 2;
int right  = i * 2 + 1;
// leaf node check:
boolean isLeaf = (i > n/2);
```

## 9. Sorting a 2D Array Ascending

```java
// With Comparator class
Arrays.sort(logs, new Comparator<int[]>() {
    @Override public int compare(int[] a, int[] b) {
        return Integer.compare(a[0], b[0]);
    }
});

// With lambda
Collections.sort(logs, (a, b) -> Integer.compare(a[0], b[0]));
```

## 10. Union Find Pattern Implementation

```java
class UnionFind {
    private int[] root;
    public UnionFind(int n) {
        root = new int[n];
        for (int i = 0; i < n; i++) root[i] = i;
    }
    public int find(int x) {
        return (x == root[x]) ? x : (root[x] = find(root[x]));
    }
    public void union(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx != ry) root[ry] = rx;
    }
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```

## 11. Backtracking Pattern

```python
def backtrack(candidate):
    if find_solution(candidate):
        output(candidate)
        return

    for next_candidate in list_of_candidates:
        if is_valid(next_candidate):
            place(next_candidate)
            backtrack(next_candidate)
            remove(next_candidate)
```

## 12. Min-Max Heap

```java
// Min-heap
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
// Max-heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

// On a pair (key, value)
Queue<Pair<Integer,Integer>> pq =
    new PriorityQueue<>(Comparator.comparing(Pair::getKey));

// On custom class
Queue<Edge> edgeQueue =
    new PriorityQueue<>((a,b) -> a.cost - b.cost);
```

## 13. Divide & Conquer Template

```java
public XYZ[] dc(XYZ[] arr) {
    if (arr.length <= 1) return arr;
    int mid = arr.length/2;
    XYZ[] left  = dc(Arrays.copyOfRange(arr, 0, mid));
    XYZ[] right = dc(Arrays.copyOfRange(arr, mid, arr.length));
    return merge(left, right);
}
```

## 14. Collection Copies

```java
// Repeat "0" n times and join
String strPwd = String.join("", Collections.nCopies(n, "0"));
```

## 15. Bellman–Ford Algorithm

* Edge-oriented DP → handles negative weights
* Iterates up to V–1 times relaxing all edges
* Detects negative cycles
* Time: O(V·E)

> **Use case**: e.g., “cheapest flight with at most k stops.”

## 16. Dijkstra’s Algorithm

* Greedy, vertex-based → non-negative weights only
* Time: O(E + V log V) with a min-heap

## 17. Topological Sort

* Order vertices in a DAG so that for every edge u→v, u comes before v.
* Can be done via DFS or Kahn’s algorithm (BFS + in-degree queue).

## 18. Minimum Spanning Tree (Kruskal’s)

* Greedy: sort edges by weight, union when they connect different components.
* Uses Union-Find for cycle detection.

## 19. Kadane’s Algorithm Patterns

```java
public class KadanePatterns {
    // 1. Classic: max subarray sum
    public int maxSubArray(int[] nums) { ... }

    // 2. Max prod subarray (track max & min)
    public int maxProduct(int[] nums) { ... }

    // 3. Circular sum: max of Kadane vs. total–minKadane
    public int maxSubarraySumCircular(int[] nums) { ... }

    // 4. One deletion allowed: two-pass fwd & bwd
    public int maxSubarrayWithOneDeletion(int[] arr) { ... }

    // 5. Constrained subsequence sum (LC 1425) using deque
    public int constrainedSubsetSum(int[] nums, int k) { ... }
}
```

## 20. Monotonic Stack Patterns

* **Next Smaller (increasing)**
* **Previous Smaller (increasing)**
* **Next Greater (decreasing)**
* **Previous Greater (decreasing)**

```java
// Example: Next Smaller to the right
for (int i = n-1; i >= 0; i--) {
    while (!stack.isEmpty() && stack.peek() >= heights[i]) {
        stack.pop();
    }
    nextSmaller[i] = stack.isEmpty() ? n : stack.peek();
    stack.push(heights[i]);
}
```

*(Similarly adapt for “prev smaller”, “next greater”, “prev greater” by adjusting loops & comparisons.)*

## 21. Fast & Slow Pointer Template

```java
class ListNode { int val; ListNode next; }
public ListNode fastSlowTemplate(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        // optional: if (slow == fast) break;  // cycle detection
    }
    return slow;  // mid-point or meeting point in cycle logic
}
```

## 22. Tree Maximum Path Sum Pattern

* **Global max** tracks the best “through-node” sum.
* **Helper returns** max “single-branch” gain (one child only).
* **Prune negatives**: treat negative child sums as 0.

```java
class Solution {
    private int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }
    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int left  = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));
        max = Math.max(max, node.val + left + right);
        return node.val + Math.max(left, right);
    }
}
```

## 23. House Robber III - Tree DP


**Problem:**
You’re given a binary tree of non-negative values. You want to maximize the sum of robbed node values, but you cannot rob two directly linked houses (parent & child).

---

**State Definition (per node):**

* **dp$0$** = max you can rob in this subtree **if you DON’T rob** this node
* **dp$1$** = max you can rob in this subtree **if you DO rob** this node

**Recurrence (post-order):**

```
dp[1] = node.val + left.dp[0] + right.dp[0]
dp[0] = max(left.dp[0], left.dp[1]) + max(right.dp[0], right.dp[1])
```

**Answer:**

```
result = max(root.dp[0], root.dp[1])
```

**Complexity:**

* **Time:** O(n) — each node visited once
* **Space:** O(h) recursion stack (h = tree height)

---

## 24. place minimum cameras so that every node is monitored

**Problem:** place minimum cameras so that every node is monitored (a camera covers its parent, itself, and its immediate children).

**States (per node):**

* `dp[0]` = cameras if **you place** one here
* `dp[1]` = cameras if **you’re covered** (by one child) but **don’t place** one here
* `dp[2]` = cameras if **you’re not covered** (parent must cover you)

**Recurrence (post-order):**

```
dp[0] = 1 + minAll(L) + minAll(R)
dp[2] = L[1] + R[1]
dp[1] = min(
           dp[0],
           L[0] + min(L-covered of R),
           R[0] + min(R-covered of L)
         )
```

**Answer:**

```
minCameraCover = dpRoot[1]
```

**Complexity:**

* **Time:** O(n) — one DFS
* **Space:** O(h) — recursion stack (h=tree height)

---


## 25. Lowest Common Ancestor (Binary Tree)

**Both sides non-null**

left found one target, right found the other → curr is the LCA.

**One side non-null**

Both targets lie in that one subtree (or it is itself p/q) → pass that non-null up.

**Both null**

Neither target here → return null.


---

> *Keep this cheat sheet handy when tackling common interview or contest problems!*
