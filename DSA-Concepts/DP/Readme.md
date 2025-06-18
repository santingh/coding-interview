### The “Grey-Zone” Master-Plan

**Goal:** make you *comfortable* spotting whether a hard optimisation task is DP, Greedy, or a hybrid—*before* you start coding.
**Duration:** 14 days, \~90 min/day (45 min solve ➜ 30 min post-mortem ➜ 15 min theory skim).
All problems are hand-picked where the “obvious” approach is wrong—or proving it’s right is the real challenge.

---

## 0  Daily ritual (15 min)

| Step                       | Question to jot in 2-3 bullets *(forces clarity before looking at solutions)*                                                                                                                                 |
| -------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **1 Classify**             | • *Is the search space exponential if I brute-force?*  <br>• *Do partial decisions lock me out of better global states?* (→ DP) <br>• *Can I prove a swap argument / matroid / monotone property?* (→ Greedy) |
| **2 State / Invariant**    | ✔ If DP: write `dp[?] = …` in English.  <br>✔ If Greedy: write the invariant that remains true after every step.                                                                                              |
| **3 Counter-example hunt** | Try to break the first solution you thought of with a 4–6 element toy case.                                                                                                                                   |

Keep the notes—Sunday re-solve *without* them.

---

## 1  Problem ladder (14 days)

Each day has **3 problems**:

* **W (Warm-up)** – <10 min once you spot the trick.
* **T (Twister)** – Greedy *looks* fine but fails; correct answer is DP or graph.
* **P (Proof-Greedy)** – Greedy is **optimal** but you must justify it (exchange / cut & paste).

> All LeetCode IDs are public; Codeforces (CF) tasks include round/letter.
> *Rating ≈* LC difficulty or CF rating.

| Day   | Focus                                           | W                                                            | T                                                         | P                                                  |                                       |                                           |
| ----- | ----------------------------------------------- | ------------------------------------------------------------ | --------------------------------------------------------- | -------------------------------------------------- | ------------------------------------- | ----------------------------------------- |
| **1** | Coins & change                                  | **LC 322** Coin Change I (DP)                                | **CF 1760F** Quests (                                     | N                                                  | ≤2e5) – Greedy fails w/ crafted costs | **LC 860** Lemonade Change (prove greedy) |
| **2** | Intervals vs weights                            | **LC 435** Non-overlap Intervals (Greedy)                    | **LC 1235** Weighted Job Scheduling (DP over sorted)      | **CF 1881C** Platforms (earliest-finish greedy)    |                                       |                                           |
| **3** | Scheduling & penalties                          | **LC 253** Meeting Rooms II (heap)                           | **CF 1329B** Present for Vitka (DP over prefix)           | **LC 630** Course Schedule III (prove heap-greedy) |                                       |                                           |
| **4** | Path jumps                                      | **LC 55** Jump Game I (greedy)                               | **LC 1696** Jump Game VI (DP + deque)                     | **LC 45** Jump Game II (linear greedy + proof)     |                                       |                                           |
| **5** | Partition & subsets                             | **LC 416** Equal Subset Sum (bitset DP)                      | **CF 1766D** Lucky Permutation (DP on bits, greedy fails) | **LC 763** Partition Labels (greedy cut positions) |                                       |                                           |
| **6** | Graph + resource                                | **LC 1514** Path with Max Probability (Dijkstra but “+”→“×”) | **LC 787** Cheapest Flight ≤ K stops (DP on step)         | **CF 1709C** Saving the City (greedy grouping)     |                                       |                                           |
| **7** | REVIEW + cold re-solve 3 hardest stuck problems |                                                              |                                                           |                                                    |                                       |                                           |

| Day    | Focus               | W                                                                                                                                         | T                                                         | P                                                                       |
| ------ | ------------------- | ----------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------- | ----------------------------------------------------------------------- |
| **8**  | String DP & edits   | **LC 1143** LCS                                                                                                                           | **LC 1044** Longest Dup Substring (suffix + DP vs greedy) | **CF 1715B** Beautiful Array (greedy lexicographic)                     |
| **9**  | Trees               | **LC 337** House Robber III (tree-DP)                                                                                                     | **LC 968** Binary Tree Cameras (prove DFS-greedy)         | **CF 1741E** Sending a Sequence Over the Network (DP/gap-greedy hybrid) |
| **10** | Stock / profit      | **LC 122** Best Time Buy/Sell II (greedy summation)                                                                                       | **LC 123** Best Time Buy/Sell III (DP 2 transactions)     | **LC 714** Stock w/ Fee (prove greedy DP-compression)                   |
| **11** | Geometry / covering | **LC 452** Burst Balloons Arrows (sort + greedy)                                                                                          | **CF 1108D** Diverse Strings (DP vs greedy)               | **LC 406** Queue Reconstruction by Height (counter-intuitive greedy)    |
| **12** | Bitmask DP          | **LC 847** Shortest Path Visit All Nodes                                                                                                  | **AtCoder ABC 286 F** Sugoroku (try greedy → fails)       | **LC 1404** Number of Steps to Reduce Binary (greedy arithmetic proof)  |
| **13** | Monotone & slopes   | **LC 84** Largest Histogram (stack)                                                                                                       | **CF Edu DP W** Intervals (divide-and-conquer DP)         | **CF 1638C** Inversion Graph (monotone greedy proof)                    |
| **14** | Capstone mock       | Random mix: 1 CF Div 2 C-D, 1 LC Hard DP, 1 LC Medium Greedy chosen from your weakest tags on LeetCode Stats. (Self-select, 90-min total) |                                                           |                                                                         |

---

## 2  How to use the ladder

1. **Blind attempt** under timer.
2. **Fail-fast check**

   * After 20 min stuck, try to construct a counter-example to your current idea.
   * If found → pivot method; if none → continue 10 min.
3. **Post-mortem**

   * Summarise *why* the greedy proof works or fails (exchange argument, matroid, monotone queue, etc.).
   * Rewrite state definition or invariant in one sentence.

---

## 3  Deep-dive readings (for evenings)

* **“Greedy ≠ DP: guide by counter-example”** – CF blog by Um\_nik ([codeforces.com][1])
* **LinkedIn cheat-sheet** – 6 key greedy proof patterns ([linkedin.com][2])
* **Reddit meta on identifying DP vs Greedy** – community heuristics ([leetcode.com][3])
* **Uva 10154 Weights & Measures** – full DP proof write-up (turtle tower) ([reponroy.wordpress.com][4])

---

### After these 14 days

You’ll have solved **42 intensely deceptive problems**, each one forcing an explicit *“DP or Greedy?”* decision and a proof. Keep rotating the toughest ones every few weeks—the intuition compounds quickly. Good luck & happy grinding!

[1]: https://codeforces.com/blog/entry/106346?utm_source=chatgpt.com "On \"is this greedy or DP\", forcing and rubber bands - Codeforces"
[2]: https://www.linkedin.com/posts/ishaan-agrawal_greedy-problems-are-the-hardest-category-activity-7223040255472381954-02JE?utm_source=chatgpt.com "How to solve greedy problems with Leetcode - LinkedIn"
[3]: https://leetcode.com/discuss/general-discussion/382154/identify-dynamic-programming-vs-greedy-solution?utm_source=chatgpt.com "Identify Dynamic Programming vs Greedy Solution? - LeetCode"
[4]: https://reponroy.wordpress.com/2016/06/07/uva-volume-101/?utm_source=chatgpt.com "Uva volume 10100-10199 - Code for Fun - WordPress.com"

