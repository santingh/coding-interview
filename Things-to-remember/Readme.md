 1. **2-d Array Coordinates to Intger**

```
    id = (row * colCount + col); 
    row = id / colCount;
    col  = id % colCount;
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


6. **Shortest Path**

   1. BFS guarantees that you find the shortest path first because it explores nodes level by level
   2. While DFS can be used to find paths, it doesn't guarantee finding the shortest path, especially in graphs with weighted edges.


7. **Cycle Detection**
   
   DFS is more suitable for tasks like topological sorting, cycle detection, or searching for paths without concern for their length.

8. **Disjoint Set(Union Find)**
   
   The primary use of disjoint sets is to address the connectivity between the components of a network. The “network“ here can be a computer network or a social network. For instance, we can use a disjoint set to determine if two people share a common ancestor.

9. **Binary Tree To Array**

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
   

