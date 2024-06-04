**Dijiktra Algorithm**

```
private final List<List<Node>> adjacencyList = new ArrayList<>(vertices);

PriorityQueue<Node> priorityQueue = new PriorityQueue<>(vertices, new Node());

int[] distances = new int[vertices];
Arrays.fill(distances, Integer.MAX_VALUE);
distances[startVertex] = 0;
priorityQueue.add(new Node(startVertex, 0));

while (!priorityQueue.isEmpty()) {
  int currentVertex = priorityQueue.poll().node;

  for (Node neighbor : adjacencyList.get(currentVertex)) {
      int newDist = distances[currentVertex] + neighbor.cost;
      if (newDist < distances[neighbor.node]) {
          distances[neighbor.node] = newDist;
          priorityQueue.add(new Node(neighbor.node, newDist));
      }
  }
}

static class Node implements Comparator<Node> {
  public int node;
  public int cost;

  public Node() {}

  public Node(int node, int cost) {
      this.node = node;
      this.cost = cost;
  }

  @Override
  public int compare(Node node1, Node node2) {
      return Integer.compare(node1.cost, node2.cost);
  }
}

```
