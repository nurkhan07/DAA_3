
package smartcity.mst;

import smartcity.dsu.DSU;
import smartcity.graph.Edge;
import smartcity.graph.Graph;

import java.util.*;

public class Kruskal {

    public static MSTResult findMST(Graph graph) {
        List<String> vertices =
                new ArrayList<>(graph.getVertices());

        Map<String, Integer> index = new HashMap<>();

        for (int i = 0; i < vertices.size(); i++) {
            index.put(vertices.get(i), i);
        }

        List<Edge> sortedEdges =
                new ArrayList<>(graph.getEdges());

        sortedEdges.sort(
                Comparator.comparingInt(Edge::getWeight)
        );

        DSU dsu = new DSU(vertices.size());
        List<Edge> mstEdges = new ArrayList<>();
        long totalWeight = 0;

        for (Edge edge : sortedEdges) {
            int u = index.get(edge.getSource());
            int v = index.get(edge.getDestination());

            if (dsu.union(u, v)) {
                mstEdges.add(edge);
                totalWeight += edge.getWeight();

                if (mstEdges.size() == vertices.size() - 1) {
                    break;
                }
            }
        }

        boolean connected = vertices.size() <= 1
                || mstEdges.size() == vertices.size() - 1;

        return new MSTResult(
                mstEdges, totalWeight, connected
        );
    }
}
