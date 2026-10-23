
package smartcity.connectivity;

import smartcity.graph.DirectedGraph;
import smartcity.graph.Edge;

import java.util.*;

public class TopologicalSort {

    public static List<String> sort(DirectedGraph graph) {
        Map<String, Integer> indegree = new LinkedHashMap<>();

        for (String vertex : graph.getVertices()) {
            indegree.put(vertex, 0);
        }

        for (Edge edge : graph.getEdges()) {
            String destination = edge.getDestination();

            indegree.put(
                    destination,
                    indegree.get(destination) + 1
            );
        }

        Queue<String> queue = new ArrayDeque<>();

        for (String vertex : graph.getVertices()) {
            if (indegree.get(vertex) == 0) {
                queue.offer(vertex);
            }
        }

        List<String> order = new ArrayList<>();

        while (!queue.isEmpty()) {
            String current = queue.poll();
            order.add(current);

            for (Edge edge : graph.getAdjacentEdges(current)) {
                String destination = edge.getDestination();

                int newDegree = indegree.get(destination) - 1;
                indegree.put(destination, newDegree);

                if (newDegree == 0) {
                    queue.offer(destination);
                }
            }
        }

        if (order.size() != graph.getVertexCount()) {
            throw new IllegalArgumentException(
                    "Graph contains a cycle; topological order is impossible"
            );
        }

        return order;
    }
}
