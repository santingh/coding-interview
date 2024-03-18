 1. **2-d Array Coordinates to Intger**


    id = (row * colCount + col); 
    row = id / colCount;
    col  = id % colCount;


2. **int to char and char to int**

   int n = cArray[i] - '0';
   cArray[i] = (char) (n2 + '0');


3. **Stack Usage**

   process of storing elements and then walking back through them matches the behavior of a stack.


4. **Shortest Path**

   1. BFS guarantees that you find the shortest path first because it explores nodes level by level
   2. While DFS can be used to find paths, it doesn't guarantee finding the shortest path, especially in graphs with weighted edges.


5. **Cycle Detection**
   
   DFS is more suitable for tasks like topological sorting, cycle detection, or searching for paths without concern for their length.

6. **Disjoint Set(Union Find)**
   The primary use of disjoint sets is to address the connectivity between the components of a network. The “network“ here can be a computer network or a social network. For instance, we can use a disjoint set to determine if two people share a common ancestor.
   

