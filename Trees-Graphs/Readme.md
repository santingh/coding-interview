<!-- README: Graph & Tree Problem List -->

# Graph & Tree Problem List

A curated set of **Graph** & **Tree** problems for practice.

<!-- Section: Graph Problems -->

## 🔗 Graph Problems

<!-- Subsection: Traversal & Connectivity -->

### Traversal & Connectivity

* **200. Number of Islands** (Medium)
  Count connected components in a grid via DFS/BFS.
* **133. Clone Graph** (Medium)
  Deep-copy a directed or undirected graph.
* **547. Number of Provinces** (Medium)
  Count connected components in an adjacency matrix.

<!-- Subsection: Shortest Paths & Ordering -->

### Shortest Paths & Ordering

* **743. Network Delay Time** (Medium)
  Single-source shortest paths on a directed, weighted graph (Dijkstra).
* **127. Word Ladder** (Hard)
  BFS on an implicit graph of word transformations.
* **332. Reconstruct Itinerary** (Medium)
  Eulerian path with lex order via Hierholzer’s algorithm.
* **207. Course Schedule** (Medium)
  Detect cycle in directed graph (topological sort viability).
* **210. Course Schedule II** (Medium)
  Return a valid topological ordering or detect impossibility.

<!-- Subsection: MST & Connectivity Checks -->

### Minimum Spanning Tree & Connectivity Checks

* **1584. Min Cost to Connect All Points** (Medium)
  Compute MST on a complete graph using Prim’s or Kruskal’s algorithm.
* **684. Redundant Connection** (Medium)
  Find the extra edge creating a cycle using Union-Find.

<!-- Subsection: Bridges, Articulations & SCC -->

### Bridges, Articulations & SCC

* **1192. Critical Connections in a Network** (Medium)
  Find all bridges via Tarjan’s low-link DFS.
* **1245. Tree Diameter** (Medium)
  Two-pass DFS to compute the longest path in a tree.
* **2101. Detonate the Maximum Bombs** (Medium)
  Build directed graph on range triggers and test reachability.

<!-- Section: Tree Problems -->

## 🌳 Tree Problems

<!-- Subsection: Basic Traversals & Checks -->

### Basic Traversals & Checks

* **94. Binary Tree Inorder Traversal** (Easy)
  Inorder traversal using recursion or a stack.
* **144. Binary Tree Preorder Traversal** (Easy)
  Preorder traversal using recursion or a stack.
* **145. Binary Tree Postorder Traversal** (Easy)
  Postorder traversal using recursion or a stack.
* **100. Same Tree** (Easy)
  Check structural equality via DFS.

<!-- Subsection: Binary Search Tree (BST) -->

### Binary Search Tree (BST)

* **98. Validate Binary Search Tree** (Medium)
  Verify BST property with in-order scan or bound propagation.
* **230. Kth Smallest Element in a BST** (Medium)
  In-order traversal with a counter.
* **108. Convert Sorted Array to BST** (Easy)
  Build a height-balanced BST via divide-and-conquer.

<!-- Subsection: Tree DP & Greedy -->

### Tree DP & Greedy

* **124. Binary Tree Maximum Path Sum** (Hard)
  Post-order DFS with max-sum tracking.
* **337. House Robber III** (Medium)
  Tree DP with include/exclude states.
* **968. Binary Tree Cameras** (Hard)
  Greedy post-order DFS for optimal camera placement.

<!-- Subsection: Serialization, LCA & Advanced -->

### Serialization, LCA & Advanced

* **297. Serialize and Deserialize Binary Tree** (Hard)
  Encode/decode a binary tree using pre-order with null markers.
* **236. Lowest Common Ancestor of a Binary Tree** (Medium)
  Single-pass DFS to find LCA.
* **865. Smallest Subtree with all the Deepest Nodes** (Medium)
  DFS tracking depth and computing LCA.

<!-- Section: Practice Tips -->

<!-- Section: Google Interview Problems -->

## 💼 Google Interview Practice Problems

These problems have appeared in Google interviews and test advanced Graph & Tree techniques:

* **Word Ladder II** (Hard)
  Find all shortest transformation sequences from `beginWord` to `endWord` using BFS layering + backtracking.
* **Alien Dictionary** (Hard)
  Derive a valid character order from a sorted word list via topological sort and cycle detection.
* **Critical Connections in a Network** (LC #1192, Medium)
  Identify all bridge edges in an undirected graph with Tarjan’s low-link algorithm.
* **Minimum Height Trees** (LC #310, Medium)
  Find all roots that minimize tree height by peeling leaves layer by layer.
* **Course Schedule II** (LC #210, Medium)
  Return a topological ordering of courses or detect impossibility using Kahn’s algorithm.
* **Network Delay Time** (LC #743, Medium)
  Compute signal propagation time with single-source shortest paths (Dijkstra).
* **Minimum Cost to Connect All Points** (LC #1584, Medium)
  Compute the MST cost on a complete graph using Prim’s or Kruskal’s algorithm.
* **Redundant Connection II** (LC #685, Hard)
  Remove the extra directed edge that creates a cycle or multiple parents using Union-Find logic.
* **Binary Tree Cameras** (LC #968, Hard)
  Place the fewest cameras to monitor all nodes using a greedy post-order DFS with state tracking.
* **Serialize and Deserialize Binary Tree** (LC #297, Hard)
  Encode and reconstruct a binary tree using pre-order traversal with null markers.

## 📝 Practice Tips

* **Mix & Match**: Alternate between Graph and Tree problems to keep skills sharp.
* **Implement from Scratch**: Write your own DFS/BFS, Union-Find, Tarjan’s algorithm, etc.
* **Edge-Case Rigor**: Test empty inputs, single-node cases, skewed trees, duplicate edges, and disconnected graphs.
* **Explore Advanced Topics**: Flow algorithms, segment trees, graph decompositions, etc.
