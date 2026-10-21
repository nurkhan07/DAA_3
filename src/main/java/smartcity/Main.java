package smartcity;

import smartcity.graph.Edge;
import smartcity.graph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph city = new Graph();

        city.addVertex("City Center");
        city.addVertex("Airport");

        city.addEdge("City Center", "Airport", 10);
        city.addEdge("City Center", "University", 4);
        city.addEdge("University", "Airport", 7);
        city.addEdge("University", "Hospital", 3);
        city.addEdge("Airport", "Hospital", 12);

        System.out.println("=== Smart City Infrastructure ===");
        System.out.println("Vertices: " + city.getVertexCount());
        System.out.println("Edges: " + city.getEdgeCount());

        System.out.println("\nRoad connections:");

        for (Edge edge : city.getEdges()) {
            System.out.println(edge);
        }

        System.out.println("\nAdjacency list:");
        city.printGraph();
    }
}