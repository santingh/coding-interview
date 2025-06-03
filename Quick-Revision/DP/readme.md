# Dynamic Programming Problem List

Below is a curated set of DP problems—grouped by common patterns

---

## 1. 1D DP (Linear‐sequence problems)

* **Climbing Stairs** (`LC 70`)
  *Pattern*: Simple Fibonacci‐style recurrence. Builds intuition for `dp[i] = dp[i-1] + dp[i-2]`.

* **House Robber** (`LC 198`)
  *Pattern*: “Take‐skip” linear decision;
  `dp[i] = max(dp[i-1], dp[i-2] + nums[i])`.

* **House Robber II** (`LC 213`)
  *Pattern*: Same as 198 but on a circular array—practices “break into two runs” technique.

* **House Robber III** (`LC 337`)
  *Pattern*: Tree‐shaped DP (postorder): decide for each node whether to rob it or not.

* **Maximum Subarray** (`LC 53`)
  *Pattern*: Kadane’s algorithm;
  `dp[i] = max(nums[i], dp[i-1] + nums[i])`—foundation for many “best‐sum” variants.

* **Best Time to Buy and Sell Stock**

  * `LC 121` (one transaction)
  * `LC 122` (multiple transactions)
  * `LC 123` (at most two transactions)
    *Pattern*: 1D DP with “state compression” (hold vs. cash).

* **Coin Change** (`LC 322`)
  *Pattern*: Unbounded‐knapsack style:
  `dp[i] = min(dp[i], dp[i – coin] + 1)`.

* **Partition Equal Subset Sum** (`LC 416`)
  *Pattern*: Subset‐sum Boolean DP → reduces to knapsack (0/1) to check whether a subset sums to `total/2`.

---

## 2. 2D DP on Strings

* **Longest Common Subsequence** (`LC 1143`)
  *Pattern*: Classic 2D DP on prefixes:

  ```
  dp[i][j] = (text1[i-1] == text2[j-1])
             ? dp[i-1][j-1] + 1
             : max(dp[i-1][j], dp[i][j-1])
  ```

* **Longest Common Substring** (`LC 718`)
  *Pattern*: Like LCS but requires contiguous match;
  `dp[i][j] = (s1[i-1] == s2[j-1]) ? dp[i-1][j-1] + 1 : 0`.

* **Edit Distance** (`LC 72`)
  *Pattern*: 2D DP with three options per cell (insert, delete, replace):

  ```
  dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
  ```

* **Wildcard Matching** (`LC 44`)
  *Pattern*: 2D DP over string vs. pattern (with `?` and `*`). Covers “DP with jumps” when `*` can match zero or many.

* **Regular Expression Matching** (`LC 10`)
  *Pattern*: 2D DP over string and regex (with `.` and `*`). Similar to wildcard but more nuanced transitions.

* **Distinct Subsequences** (`LC 115`)
  *Pattern*: Count how many ways to form one string from another via subsequences:

  ```
  dp[i][j] = (s1[i-1] == s2[j-1])
             ? dp[i-1][j-1] + dp[i-1][j]
             : dp[i-1][j]
  ```

---

## 3. 2D Grid DP (Matrix traversal)

* **Unique Paths** (`LC 62`)
  *Pattern*: Count paths in an `m × n` grid:
  `dp[i][j] = dp[i-1][j] + dp[i][j-1]`.

* **Unique Paths II** (`LC 63`)
  *Pattern*: Same as 62 but with obstacles (`1` means blocked); practice “initialize first row/column with care.”

* **Minimum Path Sum** (`LC 64`)
  *Pattern*: Weighted grid:
  `dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]`.

* **Dungeon Game** (`LC 174`)
  *Pattern*: Reverse DP on grid (bottom‐up from goal to start) to track minimum health needed. Shows “DP with negative costs.”

* **Edit Distance on a Matrix (“word wrap” style)**

  * Example: `LC 1326` (“Minimum Number of Taps to Open to Water a Garden”)
  * `LC 1094` (“Car Pooling”)
    *Pattern*: Any‐to‐any “interval” / “stocking intervals” can be framed as 1D or 2D DP in one dimension with events—good practice for event DP.

---

## 4. Interval DP

* **Burst Balloons** (`LC 312`)
  *Pattern*: Interval DP over subarray intervals:

  ```
  dp[i][j] = max over k in (i,j) of
             dp[i][k] + dp[k][j] + score(i, k, j).
  ```

  Teaches “think in terms of last balloon you pop in that interval.”

* **Stone Game II** (`LC 1140`) or **Stone Game** variants (`LC 877`, `LC 1140`, `LC 1279`)
  *Pattern*: Two-player (minimax) + DP over intervals.

* **Palindromic Partitioning II** (`LC 132`)
  *Pattern*: Combine palindrome‐check precomputation (`isPal[i][j]`) with 1D DP to compute min cuts:

  ```
  f[i] = min for j < i { f[j] + 1 if isPal[j+1][i] }
  ```

* **Matrix Chain Multiplication** (classic—not on LeetCode)
  *Pattern*: Interval DP:

  ```
  dp[i][j] = min over i ≤ k < j of
             dp[i][k] + dp[k+1][j] + cost(i, k, j).
  ```

* **Optimal BST / Optimal Matrix Chain** (classical interview questions).

---

## 5. Knapsack‐Style DP

* **0/1 Knapsack Variants**

  * **Target Sum** (`LC 494`) – can be reduced to subset‐sum.
    *Pattern*: Count ways to assign `+/-` to reach target; equivalent to subset DP.

* **Partition Equal Subset Sum** (`LC 416`)
  *Pattern*: Already covered, but also fits knapsack: find subset summing to `total/2`.

* **Ones and Zeroes** (`LC 474`)
  *Pattern*: 2D knapsack on counts of ‘0’ and ‘1’ in strings:

  ```
  dp[i][j] = max number of strings using at most i zeros and j ones.
  ```

* **Coin Change 2** (`LC 518`)
  *Pattern*: Count ways to make amount with unlimited coins (order doesn’t matter).
  Typical recurrence:

  ```
  dp[i] = sum over coins { dp[i – coin] }.
  ```

---

## 6. Bitmask / Subset DP

* **Word Break** (`LC 139`) / **Word Break II** (`LC 140`)
  *Pattern*: DP over positions in string, optionally combined with backtracking.

  ```
  f[i] = OR over any valid word ending at j (< i) of f[j].
  ```

* **Partition to K Equal Sum Subsets** (`LC 698`)
  *Pattern*: Backtracking + bitmask + memo. Use a bitmask to track which elements are used, and recursively form k subsets.

* **Decode Ways** (`LC 91`)
  *Pattern*: 1D DP + small finite “state machine” for two‐digit decoding.

* **Domino and Tromino Tiling** (`LC 790`)
  *Pattern*: DP on state + position; uses bitmask representing how previous column is filled.

---

## 7. Tree or Graph DP

* **House Robber III** (`LC 337`)
  *Pattern*: Tree‐DP with two states per node: “rob this node” vs. “don’t rob.”

* **Binary Tree Maximum Path Sum** (`LC 124`)
  *Pattern*: DFS + compute “max path ending at node” while tracking global max; analogous to DP on trees.

* **Longest Univalue Path** (`LC 687`)
  *Pattern*: Postorder DFS; track same‐value paths through children.

* **Custom Tree/Graph DP Problems** (e.g., path counting on DAGs, tree‐shaped knapsacks).

---

## 8. Miscellaneous / Combination DP

* **Triangle** (`LC 120`)
  *Pattern*: DP on triangle levels:

  ```
  f[i][j] = triangle[i][j] + min(f[i-1][j-1], f[i-1][j]).
  ```

* **Minimum Falling Path Sum** (`LC 931`)
  *Pattern*: 2D DP with neighbors above:

  ```
  f[i][j] = grid[i][j] + min(f[i-1][j-1], f[i-1][j], f[i-1][j+1]).
  ```

* **Paint House / Paint Fence / Paint House II**

  * `LC 256` (“Paint House”)
  * `LC 887` (“Super Egg Drop” is related)
  * `LC 265` (“Paint House II”)
    *Pattern*: State DP on coloring with constraints between adjacent items.

* **Decode Ways II** (`LC 639`)
  *Pattern*: Extension of LC 91 with `*` wildcard.

* **Cherry Pickup** (`LC 741`)
  *Pattern*: 3D DP (two walkers on grid simultaneously). Good for “DP with multiple pointers” practice.

* **Dungeon Game** (`LC 174`)
  *Pattern*: Covered under 2D Grid, but complex enough to revisit in this section.

---

### Tips on Using This List

1. **Begin with 1D/2D basics**: Solve `LC 70`, `LC 198`, `LC 53`, `LC 1143`, `LC 72` to get comfortable with filling an array/table.
2. **Move to string DP**: Tackle `LC 1143` (LCS), `LC 72` (Edit Distance) once 1D/2D numeric DP is second nature.
3. **Practice interval DP**: Problems like `LC 312` (Burst Balloons) and `LC 132` (Palindromic Partitioning II) are harder—attempt these early and revisit if stuck.
4. **Cover knapsack/subset‐sum variants**: Solving `LC 416` (Partition Equal Subset Sum) and `LC 494` (Target Sum) demonstrates how subtle changes (existence vs. count) alter the DP.
5. **Explore tree/graph DP**: `LC 337` (House Robber III) and `LC 124` (Binary Tree Maximum Path Sum) cover common patterns.
6. **Finish with mixed/3D DP**: Tackle `LC 741` (Cherry Pickup) or advanced “state‐compression” problems for a complete skill set.

By solving at least one or two problems from each category, you’ll cover virtually every core DP pattern that appears in coding interviews. Good luck!
