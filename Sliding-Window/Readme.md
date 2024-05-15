**Code Template**

**Two pointers: one input, opposite ends**

```
public int fn(int[] arr) {
    int left = 0;
    int right = arr.length - 1;
    int ans = 0;

    while (left < right) {
        // do some logic here with left and right
        if (CONDITION) {
            left++;
        } else {
            right--;
        }
    }

    return ans;
}
```

**Two pointers: two inputs, exhaust both**
```
public int fn(int[] arr1, int[] arr2) {
    int i = 0, j = 0, ans = 0;

    while (i < arr1.length && j < arr2.length) {
        // do some logic here
        if (CONDITION) {
            i++;
        } else {
            j++;
        }
    }

    while (i < arr1.length) {
        // do logic
        i++;
    }

    while (j < arr2.length) {
        // do logic
        j++;
    }

    return ans;
}
```

**Sliding window**
```
public int fn(int[] arr) {
    int left = 0, ans = 0, curr = 0;

    for (int right = 0; right < arr.length; right++) {
        // do logic here to add arr[right] to curr

        while (WINDOW_CONDITION_BROKEN) {
            // remove arr[left] from curr
            left++;
        }

        // update ans
    }

    return ans;
}
```

**Practise Problems**

[Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/)

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



