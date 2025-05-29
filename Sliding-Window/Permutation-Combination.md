
# Combinations vs Permutations Cheat Sheet

## Combinations
**Order doesn't matter** (e.g., `[1, 2]` and `[2, 1]` are considered the same)

### Use When:
- You're picking subsets
- You're summing values to a target (unique sets)
- You may or may not reuse elements (depends on problem)

### Implementation Pattern:
```java
for (int i = start; i < nums.length; i++) {
    dfs(nums, i or i + 1, ...); // reuse or not
}
```

### Common Problems:
- [39. Combination Sum](https://leetcode.com/problems/combination-sum)
- [40. Combination Sum II](https://leetcode.com/problems/combination-sum-ii)
- [77. Combinations](https://leetcode.com/problems/combinations)
- [216. Combination Sum III](https://leetcode.com/problems/combination-sum-iii)
- [518. Coin Change II](https://leetcode.com/problems/coin-change-ii)

---

## Permutations
**Order matters** (e.g., `[1, 2]` and `[2, 1]` are different)

### Use When:
- You want all possible orderings
- Asked for all permutations or arrangements

### Implementation Pattern:
```java
boolean[] used;
for (int i = 0; i < nums.length; i++) {
    if (!used[i]) {
        used[i] = true;
        dfs(nums, ...);
        used[i] = false;
    }
}
```

### Common Problems:
- [46. Permutations](https://leetcode.com/problems/permutations)
- [47. Permutations II](https://leetcode.com/problems/permutations-ii)
- [784. Letter Case Permutation](https://leetcode.com/problems/letter-case-permutation)
- [17. Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number)
- [377. Combination Sum IV](https://leetcode.com/problems/combination-sum-iv) *(permutations of coins)*

---

## 🔁 Quick Decision Table

| Feature                      | Combinations       | Permutations     |
|-----------------------------|--------------------|------------------|
| Order matters?              | ❌ No              | ✅ Yes           |
| Use `start` index?          | ✅ Yes             | ❌ No            |
| Use `used[]` array?         | ❌ No              | ✅ Yes           |
| Coin change "ways" problem? | Coin Change II     | Coin Change IV   |
| All orderings needed?       | ❌ No              | ✅ Yes           |

---

## Bonus Tip:
If a problem asks for “how many” results, prefer **DP** (top-down or bottom-up) over DFS for better performance.
