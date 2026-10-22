
package smartcity.mst;

import smartcity.graph.Edge;
import smartcity.graph.Graph;

import java.util.*;

public class Prim {

    public static MSTResult findMST(Graph graph) {
        Set<String> vertices = graph.getVertices();
        Set<String> visited = new HashSet<>();

        PriorityQueue<Edge> queue = new PriorityQueue<>(
                Comparator.comparingInt(Edge::getWeight)
        );

        List<Edge> mstEdges = new ArrayList<>();
        long totalWeight = 0;

        // Start each unvisited component.
        for (String start : vertices) {
            if (visited.contains(start)) {
                continue;
            }

            visited.add(start);
            queue.addAll(graph.getAdjacentEdges(start));

            while (!queue.isEmpty()) {
                Edge edge = queue.poll();
                String destination = edge.getDestination();

                if (visited.contains(destination)) {
                    continue;
                }

                visited.add(destination);
                mstEdges.add(edge);
                totalWeight += edge.getWeight();

                for (Edge next :
                        graph.getAdjacentEdges(destination)) {
                    if (!visited.contains(next.getDestination())) {
                        queue.offer(next);
                    }
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
