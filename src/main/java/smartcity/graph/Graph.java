package smartcity.graph;

import java.util.*;

public class Graph {
    private final Map<String, List<Edge>> adjacencyList;
    private final List<Edge> edges;

    public Graph() {
        adjacencyList = new LinkedHashMap<>();
        edges = new ArrayList<>();
    }

    public void addVertex(String vertex) {
        if (vertex == null || vertex.isBlank()) {
            throw new IllegalArgumentException(
                    "Vertex name cannot be empty"
            );
        }

        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(String source, String destination, int weight) {
        Edge edge = new Edge(source, destination, weight);

        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).add(edge);

        if (!source.equals(destination)) {
            adjacencyList.get(destination).add(
                    new Edge(destination, source, weight)
            );
        }

        edges.add(edge);
    }

    public Set<String> getVertices() {
        return Collections.unmodifiableSet(
                adjacencyList.keySet()
        );
    }

    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    public List<Edge> getAdjacentEdges(String vertex) {
        List<Edge> adjacent = adjacencyList.get(vertex);

        if (adjacent == null) {
            throw new IllegalArgumentException(
                    "Unknown vertex: " + vertex
            );
        }

        return Collections.unmodifiableList(adjacent);
    }

    public int getVertexCount() {
        return adjacencyList.size();
    }

    public int getEdgeCount() {
        return edges.size();
    }

    public void printGraph() {
        for (String vertex : adjacencyList.keySet()) {
            System.out.println(
                    vertex + " -> " + adjacencyList.get(vertex)
            );
        }
    }
}