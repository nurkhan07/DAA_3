
package smartcity;

import smartcity.graph.Edge;
import smartcity.graph.Graph;
import smartcity.mst.Kruskal;
import smartcity.mst.MSTResult;
import smartcity.mst.Prim;

public class Main {

    private static void printResult(
            String algorithm,
            MSTResult result
    ) {
        System.out.println("\n=== " + algorithm + " ===");

        System.out.println("Connected: " + result.isConnected());

        for (Edge edge : result.getEdges()) {
            System.out.println(edge);
        }

        System.out.println(
                "Total MST weight: " + result.getTotalWeight()
        );

        System.out.println(
                "Selected edges: " + result.getEdges().size()
        );
    }

    public static void main(String[] args) {
        Graph city = new Graph();

        city.addEdge("City Center", "Airport", 10);
        city.addEdge("City Center", "University", 4);
        city.addEdge("University", "Airport", 7);
        city.addEdge("University", "Hospital", 3);
        city.addEdge("Airport", "Hospital", 12);

        System.out.println("=== Smart City MST Analysis ===");
        System.out.println("Vertices: " + city.getVertexCount());
        System.out.println("Edges: " + city.getEdgeCount());

        MSTResult kruskalResult = Kruskal.findMST(city);
        MSTResult primResult = Prim.findMST(city);

        printResult("Kruskal's Algorithm", kruskalResult);
        printResult("Prim's Algorithm", primResult);

        System.out.println("\n=== Comparison ===");
        System.out.println(
                "Same MST weight: "
                        + (kruskalResult.getTotalWeight()
                        == primResult.getTotalWeight())
        );
    }
}
