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


**BellmanFord**

```
import java.util.Arrays;

class Graph {
    private final int vertices;
    private final int edges;
    private final Edge[] edgeList;

    public Graph(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
        edgeList = new Edge[edges];
        for (int i = 0; i < edges; i++) {
            edgeList[i] = new Edge();
        }
    }

    public void addEdge(int edgeIndex, int source, int destination, int weight) {
        edgeList[edgeIndex].source = source;
        edgeList[edgeIndex].destination = destination;
        edgeList[edgeIndex].weight = weight;
    }

    public void bellmanFord(int startVertex) {
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;

        for (int i = 1; i < vertices; i++) {
            for (int j = 0; j < edges; j++) {
                int u = edgeList[j].source;
                int v = edgeList[j].destination;
                int weight = edgeList[j].weight;

                if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                }
            }
        }

        for (int j = 0; j < edges; j++) {
            int u = edgeList[j].source;
            int v = edgeList[j].destination;
            int weight = edgeList[j].weight;

            if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        printSolution(distances);
    }

    private void printSolution(int[] distances) {
        System.out.println("Vertex\t\t Distance from Source");
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + "\t\t\t " + distances[i]);
        }
    }

    static class Edge {
        int source;
        int destination;
        int weight;
    }

    public static void main(String[] args) {
        int vertices = 5; 
        int edges = 8; 

        Graph graph = new Graph(vertices, edges);

        graph.addEdge(0, 0, 1, -1);
        graph.addEdge(1, 0, 2, 4);
        graph.addEdge(2, 1, 2, 3);
        graph.addEdge(3, 1, 3, 2);
        graph.addEdge(4, 1, 4, 2);
        graph.addEdge(5, 3, 2, 5);
        graph.addEdge(6, 3, 1, 1);
        graph.addEdge(7, 4, 3, -3);

        int startVertex = 0;
        graph.bellmanFord(startVertex);
    }
}

```
