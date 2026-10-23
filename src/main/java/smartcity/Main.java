
package smartcity;

import smartcity.graph.Edge;
import smartcity.graph.Graph;
import smartcity.graph.DirectedGraph;

import smartcity.mst.Kruskal;
import smartcity.mst.Prim;
import smartcity.mst.MSTResult;

import smartcity.connectivity.TopologicalSort;
import smartcity.connectivity.Kosaraju;

import smartcity.shortestpath.Dijkstra;

import java.util.List;

public class Main {

    private static void printMSTResult(
            String algorithm,
            MSTResult result
    ) {
        System.out.println("\n=== " + algorithm + " ===");
        System.out.println("Connected: " + result.isConnected());

        for (Edge edge : result.getEdges()) {
            System.out.println(edge);
        }

        System.out.println(
                "Total weight: " + result.getTotalWeight()
        );
        System.out.println(
                "Selected edges: " + result.getEdges().size()
        );
    }

    private static Graph createCityGraph() {
        Graph city = new Graph();

        city.addEdge("City Center", "Airport", 10);
        city.addEdge("City Center", "University", 4);
        city.addEdge("University", "Airport", 7);
        city.addEdge("University", "Hospital", 3);
        city.addEdge("Airport", "Hospital", 12);

        return city;
    }

    private static void testMST() {
        Graph city = createCityGraph();

        System.out.println("=== Smart City Infrastructure ===");
        System.out.println("Vertices: " + city.getVertexCount());
        System.out.println("Edges: " + city.getEdgeCount());

        MSTResult kruskalResult = Kruskal.findMST(city);
        MSTResult primResult = Prim.findMST(city);

        printMSTResult("Kruskal's Algorithm", kruskalResult);
        printMSTResult("Prim's Algorithm", primResult);

        System.out.println("\n=== MST Comparison ===");
        System.out.println(
                "Same total weight: "
                        + (kruskalResult.getTotalWeight()
                        == primResult.getTotalWeight())
        );
    }

    private static void testTopologicalSort() {
        DirectedGraph graph = new DirectedGraph();

        graph.addEdge("Prepare Site", "Build Road", 1);
        graph.addEdge("Build Road", "Open Route", 1);
        graph.addEdge("Install Lights", "Open Route", 1);

        System.out.println("\n=== Topological Sort ===");
        List<String> order = TopologicalSort.sort(graph);
        System.out.println("Order: " + order);
    }

    private static void testKosaraju() {
        DirectedGraph graph = new DirectedGraph();

        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "A", 1);
        graph.addEdge("B", "C", 1);
        graph.addEdge("C", "D", 1);
        graph.addEdge("D", "C", 1);

        System.out.println("\n=== Strongly Connected Components ===");
        System.out.println(Kosaraju.findSCCs(graph));
    }

    private static void testDijkstra() {
        DirectedGraph graph = new DirectedGraph();

        graph.addEdge("City Center", "University", 4);
        graph.addEdge("City Center", "Airport", 10);
        graph.addEdge("University", "Airport", 2);
        graph.addEdge("University", "Hospital", 5);
        graph.addEdge("Airport", "Hospital", 1);

        Dijkstra.Result result =
                Dijkstra.shortestPaths(graph, "City Center");

        System.out.println("\n=== Dijkstra's Algorithm ===");

        for (String vertex : graph.getVertices()) {
            long distance = result.getDistanceTo(vertex);

            if (distance == Long.MAX_VALUE) {
                System.out.println(vertex + ": unreachable");
            } else {
                System.out.println(
                        vertex + ": distance = " + distance
                );
            }
        }

        System.out.println(
                "Shortest route to Hospital: "
                        + result.getPathTo("Hospital")
        );
    }

    public static void main(String[] args) {
        testMST();
        testTopologicalSort();
        testKosaraju();
        testDijkstra();

        System.out.println("\n=== All demonstrations completed ===");
    }
}
