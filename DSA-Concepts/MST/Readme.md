**Krushals Algorithm**


<img width="815" alt="Screenshot 2024-06-03 at 10 22 15 AM" src="https://github.com/santingh/coding-interview/assets/16878844/c0fe041d-ed94-4f22-8f97-fb0c550416ce">

**Prims Algorith**

<img width="818" alt="Screenshot 2024-06-03 at 10 23 49 AM" src="https://github.com/santingh/coding-interview/assets/16878844/53c748bb-f812-42d9-90be-05b788bcf0e4">


**The difference between the Kruskals algorithm and the Prims algorithm**

1. “Kruskal’s algorithm” expands the “minimum spanning tree” by adding edges. Whereas “Prim’s algorithm” expands the “minimum spanning tree” by adding vertices.

**Choose Between Krushals and Prims**

1. Graph Density
Sparse Graphs (Few edges):

Kruskal's Algorithm is often more efficient for sparse graphs. This is because Kruskal’s algorithm primarily relies on sorting the edges and performing union-find operations, which can be faster when the number of edges is much less than the square of the number of vertices.
Dense Graphs (Many edges):

Prim's Algorithm is usually more efficient for dense graphs. This is because Prim’s algorithm can be implemented using a priority queue (or min-heap), which allows it to handle graphs with many edges more efficiently. The adjacency matrix representation used in Prim’s can also be more space-efficient for dense graphs.

2. Data Structure Efficiency
Kruskal's Algorithm:

Uses a union-find data structure to manage the merging of sets. The efficiency of Kruskal's algorithm is highly dependent on the efficiency of the union-find operations.

Prim's Algorithm:

Typically implemented using a priority queue (min-heap). The efficiency depends on the implementation of the priority queue.

3. Practical Considerations
Edge List vs. Adjacency List/Matrix:
If the graph is given as an edge list, Kruskal's algorithm is more straightforward to implement.
If the graph is given as an adjacency list or matrix, Prim's algorithm can be more efficient, especially with dense graphs.
