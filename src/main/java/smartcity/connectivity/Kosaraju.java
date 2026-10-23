
package smartcity.connectivity;

import smartcity.graph.DirectedGraph;
import smartcity.graph.Edge;

import java.util.*;

public class Kosaraju {

    public static List<List<String>> findSCCs(
            DirectedGraph graph
    ) {
        Set<String> visited = new HashSet<>();
        List<String> finishOrder = new ArrayList<>();

        for (String vertex : graph.getVertices()) {
            if (!visited.contains(vertex)) {
                dfsOriginal(
                        graph, vertex, visited, finishOrder
                );
            }
        }

        Map<String, List<String>> reversed = new LinkedHashMap<>();

        for (String vertex : graph.getVertices()) {
            reversed.put(vertex, new ArrayList<>());
        }

        for (Edge edge : graph.getEdges()) {
            reversed.get(edge.getDestination())
                    .add(edge.getSource());
        }

        Collections.reverse(finishOrder);
        visited.clear();

        List<List<String>> components = new ArrayList<>();

        for (String vertex : finishOrder) {
            if (!visited.contains(vertex)) {
                List<String> component = new ArrayList<>();

                dfsReversed(
                        vertex, reversed, visited, component
                );

                components.add(component);
            }
        }

        return components;
    }

    private static void dfsOriginal(
            DirectedGraph graph,
            String vertex,
            Set<String> visited,
            List<String> finishOrder
    ) {
        visited.add(vertex);

        for (Edge edge : graph.getAdjacentEdges(vertex)) {
            String next = edge.getDestination();

            if (!visited.contains(next)) {
                dfsOriginal(
                        graph, next, visited, finishOrder
                );
            }
        }

        finishOrder.add(vertex);
    }

    private static void dfsReversed(
            String vertex,
            Map<String, List<String>> reversed,
            Set<String> visited,
            List<String> component
    ) {
        visited.add(vertex);
        component.add(vertex);

        for (String next : reversed.get(vertex)) {
            if (!visited.contains(next)) {
                dfsReversed(
                        next, reversed, visited, component
                );
            }
        }
    }
}
