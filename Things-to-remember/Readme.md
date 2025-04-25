 1. **2-d Array Coordinates to Intger**

```
    id = (row * colCount + col); 
    row = id / colCount;
    col  = id % colCount;

   // explore all four directions in matrix
     int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
     int row = currPos / matrix[0].length;
     int col = currPos % matrix[0].length;

     for(int i = 0; i < 4; i++){
         int newD = (currPos + i) % 4;

         int newRow = row + directions[newD][0];
         int newCol = col + directions[newD][1];
     }
```


2. **int to char and char to int**

```
   int n = cArray[i] - '0';
   cArray[i] = (char) (n2 + '0');
```

4. **Stack Usage**

   process of storing elements and then walking back through them matches the behavior of a stack.
```
   Deque<Character> stack = new LinkedList<>();
```

5. **Find number of subarrays that fit an exact criteria**

   ```
      public int fn(int[] arr, int k) {
         Map<Integer, Integer> counts = new HashMap<>();
         counts.put(0, 1);
         int ans = 0, curr = 0;
     
         for (int num: arr) {
             // do logic to change curr
             ans += counts.getOrDefault(curr - k, 0);
             counts.put(curr, counts.getOrDefault(curr, 0) + 1);
         }
     
         return ans;
       }
   ```

   if not exact criteria then use sliding window

   If your “metric” only ever goes up (or only down) as you slide, it’s monotonic → use the **two‑pointer “atMost/Exactly”** trick.
   
 Otherwise, it’s non‑monotonic → reach for **prefix sums + hashing**.

7. **Shortest Path**

   1. BFS guarantees that you find the shortest path first because it explores nodes level by level
   2. While DFS can be used to find paths, it doesn't guarantee finding the shortest path, especially in graphs with weighted edges.


8. **Cycle Detection**
   
   DFS is more suitable for tasks like topological sorting, cycle detection, or searching for paths without concern for their length.

9. **Disjoint Set(Union Find)**
   
   The primary use of disjoint sets is to address the connectivity between the components of a network. The “network“ here can be a computer network or a social network. For instance, we can use a disjoint set to determine if two people share a common ancestor.

10. **Binary Tree To Array**

```
   parent = n/2
   
   left = n*2
   
   right = n*2 + 1

   leaf Node = index > n/2
```

10. **Sorting 2-D Array Ascending Order**
    
```    
   sort(int[][] logs){
     Arrays.sort(logs, new Comparator<int[]>(){
        @Override
        public int compare(int[] log1, int[] log2){
           Integer a1 = Integer.valueOf(log1[0]);
           Integer a2 = Integer.valueOf(log2[0]);
           return a1.compareTo(a2);
        }
        
      })
     }
   ```

```
Collections.sort(logs, (a, b) -> Integer.compare(a[0], b[0]));

```

11. **Union Find Pattern**

```
  class UnionFind {
    private int[] root;

    public UnionFind(int size) {
        root = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
        }
    }

    public int find(int x) {
        if (x == root[x]) {
            return x;
        }
        return root[x] = find(root[x]);
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            root[rootY] = rootX;
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```

12. **Backtracking Patter**

```
 def backtrack(candidate):
    if find_solution(candidate):
        output(candidate)
        return
    
    # iterate all possible candidates.
    for next_candidate in list_of_candidates:
        if is_valid(next_candidate):
            # try this partial candidate solution
            place(next_candidate)
            # given the candidate, explore further.
            backtrack(next_candidate)
            # backtrack
            remove(next_candidate)
```

13. **Min-Max Heap**

```
// Min Heap
PriorityQueue<> heap = new PriorityQueue<>();

//Max Heap
PriorityQueue<> heap = new PriorityQueue<>(Collections.reverseOrder());

// comparator on pair
Queue<Pair<Integer, Integer>> pq = new PriorityQueue<Pair<Integer,Integer>>
            (Comparator.comparing(Pair::getKey));

// compator on custom class
Queue<Edge> pq = new PriorityQueue<>
            ((a,b) -> a.cost - b.cost);

```   

14. **Divide & Conquer**
```
public XYZ[] dc(XYZ[] xyz){
  if (xyz.length == 1){
    return xyz;
  }

  mid = xyz.length/2;
  XYZ[] s1 = dc(Arrays.copyOfRange(xyz, 0, mid))
  XYZ[] s2 = dc(Arrays.copyOfRange(xyz, mid, xyz.length));

  return merge(s1,s2);
}
```

15. **Collection Copies**
    ```
    String strPwd = String.join("", Collections.nCopies(n, "0"));
    ```

16. **Bellman Ford Algorithm**
    - Edge oriented | Dynamic Prog
    - Starts with 0 edges and iteratievely calculates shortest path upto N-1 edges. 
    - Works for Negative weight
    - Complexity - O(V*E)
    - Bellman Ford algorithm works by overestimating the length of the path from the starting vertex to all   other vertices. Then it iteratively relaxes those estimates by finding new paths that are shorter than the previously overestimated paths.
    - Can detect negative cycle.
    - Example: You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
   
17. **Dijkstra's Algorithm**
    - Works for non negative weights
    - Vertex based | Greedy Algo
    - O(E + VlogV)
   
18. **Topological Sort**

19. **Minimum Spanning Tree**
    Kruskal's Algorithm is a greedy algorithm used to find the Minimum Spanning Tree (MST) of a graph, ensuring all vertices are connected with the minimum total edge weight.



21. **Kadens Algorithm**

    ***Pattern Highlights***
      Classic Kadane’s keeps a running curr max and a global best.
    
      Max Product tracks both maxProd and minProd to handle sign flips.
    
      Circular Sum combines standard Kadane’s with an “invert‐and‐run” to find the minimum subarray.
    
      One Deletion uses two passes (forward/backward) and then tries removing each element by joining the two segments.
    
      Constrained Sum uses a deque to query the maximum dp[j] within the last k positions in O(1).
    

     ```
           public class KadanePatterns {
       
           /** 1. Classic Kadane’s: Maximum Subarray Sum */
           public int maxSubArray(int[] nums) {
               int best = nums[0];
               int curr = nums[0];
               for (int i = 1; i < nums.length; i++) {
                   // either extend previous or start anew at nums[i]
                   curr = Math.max(nums[i], curr + nums[i]);
                   best = Math.max(best, curr);
               }
               return best;
           }
       
           /** 2. Max Product Subarray: track both max and min */
           public int maxProduct(int[] nums) {
               int maxProd = nums[0], minProd = nums[0], ans = nums[0];
               for (int i = 1; i < nums.length; i++) {
                   int x = nums[i];
                   // if x is negative, swap maxProd & minProd before multiplying
                   if (x < 0) {
                       int tmp = maxProd;
                       maxProd = minProd;
                       minProd = tmp;
                   }
                   maxProd = Math.max(x, maxProd * x);
                   minProd = Math.min(x, minProd * x);
                   ans = Math.max(ans, maxProd);
               }
               return ans;
           }
       
           /** 3. Circular Subarray Sum */
           public int maxSubarraySumCircular(int[] nums) {
               int total = 0;
               int maxKadane = nums[0], minKadane = nums[0];
               int currMax = 0, currMin = 0;
               for (int x : nums) {
                   currMax = Math.max(x, currMax + x);
                   maxKadane = Math.max(maxKadane, currMax);
                   currMin = Math.min(x, currMin + x);
                   minKadane = Math.min(minKadane, currMin);
                   total += x;
               }
               // if all negative, maxKadane is the answer
               if (maxKadane < 0) return maxKadane;
               // otherwise, max of standard or wrap-around
               return Math.max(maxKadane, total - minKadane);
           }
       
           /** 4. Max Subarray Sum with One Deletion Allowed */
           public int maxSubarrayWithOneDeletion(int[] arr) {
               int n = arr.length;
               int[] fwd = new int[n];  // max ending at i
               int[] bwd = new int[n];  // max starting at i
               fwd[0] = arr[0];
               for (int i = 1; i < n; i++)
                   fwd[i] = Math.max(arr[i], fwd[i-1] + arr[i]);
               bwd[n-1] = arr[n-1];
               for (int i = n-2; i >= 0; i--)
                   bwd[i] = Math.max(arr[i], bwd[i+1] + arr[i]);
               int ans = fwd[0];
               for (int i = 1; i < n; i++)
                   ans = Math.max(ans, fwd[i]);  // no deletion
               for (int i = 1; i < n-1; i++)
                   // delete arr[i], combine fwd[i-1] + bwd[i+1]
                   ans = Math.max(ans, fwd[i-1] + bwd[i+1]);
               return ans;
           }
       
           /** 5. Constrained Subsequence Sum (LC 1425) */
           public int constrainedSubsetSum(int[] nums, int k) {
               int n = nums.length, ans = nums[0];
               int[] dp = new int[n];
               dp[0] = nums[0];
               // deque stores indices of dp in decreasing order of dp[]
               Deque<Integer> dq = new ArrayDeque<>();
               dq.offerLast(0);
       
               for (int i = 1; i < n; i++) {
                   // remove indices out of window [i-k, i-1]
                   while (!dq.isEmpty() && dq.peekFirst() < i - k) {
                       dq.pollFirst();
                   }
                   // best we can extend is dp[dq.peekFirst()]
                   dp[i] = Math.max(nums[i], nums[i] + dp[dq.peekFirst()]);
                   ans = Math.max(ans, dp[i]);
                   // maintain deque in decreasing dp[] order
                   while (!dq.isEmpty() && dp[i] >= dp[dq.peekLast()]) {
                       dq.pollLast();
                   }
                   dq.offerLast(i);
               }
               return ans;
           }
       }

     ```
     
23. **Monotonic Increasing Stack - Next Smaller**
    ```
    for(int i = l; i >= 0 ; i--){

            while(!stack.isEmpty() && stack.peek() >= heights[i]){
                stack.pop();
            }

            if(stack.isEmpty()){
                nextSmaller[i] = l+1;
            } else {
                nextSmaller[i] = stack.peek();
            }

            stack.push(heights[i]);
        }
    ```
24. **Monotonic Increasing Stack - Prev Smaller**
    ```
    for(int i = 0; i <= l ; i++){

            while(!stack.isEmpty() && stack.peek() >= heights[i]){
                stack.pop();
            }

            if(stack.isEmpty()){
                prevSmaller[i] = 0;
            } else {
                prevSmaller[i] = stack.peek();
            }

            stack.push( heights[i]);
        }
    ```
25. **Monotonic Decreasing Stack - Next Bigger**
    ```
    for(int i = l; i >= 0 ; i--){

            while(!stack.isEmpty() && stack.peek() < heights[i]){
                stack.pop();
            }

            if(stack.isEmpty()){
                nextSmaller[i] = l+1;
            } else {
                nextSmaller[i] = stack.peek();
            }

            stack.push(heights[i]);
        }
    ```
26. **Monotonic Decreasing Stack - Prev Bigger**
    ```
    for(int i = 0; i <= l ; i++){

            while(!stack.isEmpty() && stack.peek() < heights[i]){
                stack.pop();
            }

            if(stack.isEmpty()){
                nextSmaller[i] = l+1;
            } else {
                nextSmaller[i] = stack.peek();
            }

            stack.push(heights[i]);
        }
    ```

27. **Fast and Slow Pointer**
    ```
            
        class ListNode {
            int val;
            ListNode next;
            ListNode(int x) { val = x; }
        }
        
        public class Solution {
            public ListNode fastSlowTemplate(ListNode head) {
                // 1) Initialize both pointers at the start
                ListNode slow = head;
                ListNode fast = head;
        
                // 2) Advance fast by two and slow by one
                //    until fast hits the end (or condition triggered)
                while (fast != null && fast.next != null) {
                    slow = slow.next;          // move slow by 1
                    fast = fast.next.next;     // move fast by 2
        
                    // —— Optional cycle check ——
                    // if (slow == fast) {
                    //     // cycle detected
                    //     break;
                    // }
                }
        
                // 3) After the loop:
                //    • For finding the middle: 'slow' is at mid.
                //    • For cycle detection: check slow == fast.
                //    • For cycle entry: reset fast=head, then
                //      while(slow != fast){ slow=slow.next; fast=fast.next; }
        
                return slow;
            }
        }
 ```
