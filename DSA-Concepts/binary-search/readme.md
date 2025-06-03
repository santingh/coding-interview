
# Binary Search Cheat Sheet

Binary‐search variations mainly differ by what you’re looking for: an exact match, the first element ≥ target (lower bound), or the last element ≤ target (upper bound). Below are common templates, when to use `low ≤ high` vs. `low < high`, and how to pick your `mid` and update steps.

---

## 1. “Find if target exists” (Exact Match)

- **Loop condition:** `while (low <= high)`
- **Mid calculation:**  
  ```java
  int mid = low + (high - low) / 2;
  ````

* **Updates:**

  * If `nums[mid] == target`, return or break.
  * If `nums[mid] < target`, set `low = mid + 1`.
  * Else (`nums[mid] > target`), set `high = mid - 1`.
* **Why `≤`:** This allows checking when `low == high` (the last index). When `low` passes `high` (`low = high + 1`), all candidates are exhausted.

```java
// Example: check if target exists
int low = 0, high = n - 1;
while (low <= high) {
    int mid = low + (high - low) / 2;
    if (nums[mid] == target) {
        return mid;         // found
    } else if (nums[mid] < target) {
        low = mid + 1;
    } else {
        high = mid - 1;
    }
}
// If we exit, target isn’t in the array
return -1;
```

---

## 2. “Find first element ≥ target” (Lower Bound)

* **Loop condition:** `while (low < high)`
* **Mid calculation:**

  ```java
  int mid = low + (high - low) / 2;  // rounds down
  ```
* **Updates:**

  * If `nums[mid] < target`, then `low = mid + 1` (everything at and left of `mid` is too small).
  * Else (`nums[mid] ≥ target`), set `high = mid` (still a candidate).
* **Why `<`:** You maintain the invariant that the answer is always in `[low..high]`. When `low == high`, that index is the lower bound.

```java
// Example: find first index i such that nums[i] >= target.
// Precondition: nums is sorted in ascending order.
int low = 0, high = n;
while (low < high) {
    int mid = low + (high - low) / 2;
    if (nums[mid] < target) {
        // mid is too small—answer must be to the right
        low = mid + 1;
    } else {
        // nums[mid] >= target: mid might be the answer
        high = mid;
    }
}
// Now low == high.
// If low == n, no element >= target; else low is the first index ≥ target.
return low;
```

> **Note:** Setting `high = n` (one past the last index) ensures that if all elements < target, `low` becomes `n`. If you prefer `high = n - 1`, handle the “not found” case separately.

---

## 3. “Find last element ≤ target” (Upper Bound)

* **Loop condition:** `while (low < high)`
* **Mid calculation:**

  ```java
  int mid = low + (high - low + 1) / 2;  // rounds up
  ```
* **Updates:**

  * If `nums[mid] > target`, then `high = mid - 1` (everything at and right of `mid` is too big).
  * Else (`nums[mid] ≤ target`), set `low = mid` (still a candidate).
* **Why this form?** When searching for the last ≤ target, biasing `mid` upward prevents infinite loops when `low` and `high` are adjacent.

```java
// Example: find the last index i such that nums[i] <= target.
// Precondition: nums is sorted ascending.
int low = 0, high = n - 1;
while (low < high) {
    // Bias mid upward so that low moves when low+1 == high
    int mid = low + (high - low + 1) / 2;
    if (nums[mid] > target) {
        high = mid - 1;
    } else {
        low = mid;  // mid is valid, but maybe there’s a bigger one
    }
}
// Now low == high → last index ≤ target (or -1 if none).
return low;
```

---

## 4. When to Use Each Pattern

1. **Exact Match (“Find if exists”)**

   * Use `while (low <= high)`
   * `mid = (low + high) / 2`
   * Update `low = mid + 1` or `high = mid - 1`
   * Exit when `low > high`.

2. **Lower Bound (First element ≥ target)**

   * Predicate shape: false for i < answer, true for i ≥ answer (e.g., `nums[i] ≥ target`).
   * Use `while (low < high)`
   * `mid = (low + high) / 2` (floor)
   * If `P(mid)` is false → `low = mid + 1`
   * Else → `high = mid`
   * Exit when `low == high`; that index is the first “true” position.

3. **Upper Bound (Last element ≤ target)**

   * Predicate shape: true for i ≤ answer, false for i > answer (e.g., `nums[i] ≤ target`).
   * Use `while (low < high)`
   * `mid = (low + high + 1) / 2` (ceil)
   * If `P(mid)` is true → `low = mid`
   * Else → `high = mid - 1`
   * Exit when `low == high`; that index is the last “true” position.

---

## 5. Why Different `mid` and Loop Conditions Avoid Infinite Loops

* In `while (low <= high)`, you shrink the search range by moving either `low = mid + 1` or `high = mid - 1`. You stop when `low` passes `high`. Ideal for exact-match searches.

* In `while (low < high)` for boundaries, each iteration reduces the range:

  * **Lower Bound:** `mid = floor((low + high)/2)` ensures either `high = mid` or `low = mid + 1`.

    ```
    low=3, high=4 → mid=3
      if P(3) is true → high=3 → low==high → stop
      if P(3) is false → low=4 → low==high → stop
    ```
  * **Upper Bound:** `mid = ceil((low + high)/2)` ensures either `low = mid` or `high = mid - 1`.

    ```
    low=3, high=4 → mid=(3+4+1)/2=4
      if P(4) is true → low=4 → low==high → stop
      if P(4) is false → high=3 → low==high → stop
    ```

---

## 6. Quick Checklist When Writing Binary Search

1. **Decide what you want to find**

   * Exact match?
   * First index where a condition holds?
   * Last index where a condition holds?

2. **Define `low` and `high`**

   * Usually `low = 0`, `high = n−1` (or `high = n` if “not found” maps to `n`).
   * If searching for “first ≥ target,” consider `high = n`.

3. **Choose loop condition**

   * Exact match → `while (low <= high)`
   * First/last boundary → `while (low < high)`

4. **Compute `mid`**

   * Exact or lower‐bound:

     ```java
     mid = low + (high - low) / 2;  // floor
     ```
   * Upper‐bound (last true):

     ```java
     mid = low + (high - low + 1) / 2;  // ceil
     ```

5. **Write predicate checks and pointer moves**

   * **Exact match:**

     ```java
     if (nums[mid] == target) ...
     else if (nums[mid] < target) low = mid + 1;
     else high = mid - 1;
     ```
   * **Lower bound (first true):**

     ```java
     if (P(mid) == false) low = mid + 1;
     else high = mid;
     ```
   * **Upper bound (last true):**

     ```java
     if (P(mid) == true) low = mid;
     else high = mid - 1;
     ```

6. **Exit / Return**

   * **Exact match:** Return mid if found; otherwise, return “not found.”
   * **Lower bound:** Return `low` (then check if `low < n` and `nums[low]` satisfies).
   * **Upper bound:** Return `low` (then check if `low ≥ 0` and `nums[low]` satisfies).

---

### Example Scenarios

1. **Exact Match**

   * Searching for a specific value in a sorted array.
   * Use `while (low <= high)` and shrink by `low = mid + 1` or `high = mid - 1`.

2. **Lower Bound (First ≥ x)**

   * Predicate: `nums[i] ≥ x`.
   * Use `while (low < high)`, `mid = floor((low + high)/2)`.

     * If `nums[mid] < x` → `low = mid + 1`
     * Else → `high = mid`
   * Result: `low` is the first index ≥ x (or `n` if none).

3. **Upper Bound (Last ≤ x)**

   * Predicate: `nums[i] ≤ x`.
   * Use `while (low < high)`, `mid = ceil((low + high)/2)`.

     * If `nums[mid] > x` → `high = mid - 1`
     * Else → `low = mid`
   * Result: `low` is the last index ≤ x (or `-1` if none).

---

## Summary

* **Use `low <= high`** (with `mid = (low + high)/2`) when checking for exact presence and shrinking by `± 1`.
* **Use `low < high`** for boundary searches (first/last index satisfying a condition):

  * **First true:** `mid = floor((low + high)/2)`; update `low = mid + 1` or `high = mid`.
  * **Last true:**  `mid = ceil((low + high)/2)`; update `low = mid` or `high = mid - 1`.

Once you identify whether you need an exact‐match search or a boundary search, pick the corresponding loop condition, `mid` formula, and update rules. This resolves most confusion around `≤` vs. `<` and when to use `low = mid` vs. `mid + 1`.

```
```
