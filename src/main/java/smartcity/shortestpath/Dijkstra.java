
package smartcity.shortestpath;

import smartcity.graph.DirectedGraph;
import smartcity.graph.Edge;

import java.util.*;

public class Dijkstra {

    private static class State {
        private final String vertex;
        private final long distance;

        private State(String vertex, long distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    public static class Result {
        private final String source;
        private final Map<String, Long> distances;
        private final Map<String, String> previous;

        private Result(
                String source,
                Map<String, Long> distances,
                Map<String, String> previous
        ) {
            this.source = source;
            this.distances = new LinkedHashMap<>(distances);
            this.previous = new HashMap<>(previous);
        }

        public long getDistanceTo(String destination) {
            Long distance = distances.get(destination);

            if (distance == null) {
                throw new IllegalArgumentException(
                        "Unknown vertex: " + destination
                );
            }

            return distance;
        }

        public List<String> getPathTo(String destination) {
            if (!distances.containsKey(destination)) {
                throw new IllegalArgumentException(
                        "Unknown vertex: " + destination
                );
            }

            if (getDistanceTo(destination) == Long.MAX_VALUE) {
                return Collections.emptyList();
            }

            LinkedList<String> path = new LinkedList<>();
            String current = destination;

            while (current != null) {
                path.addFirst(current);

                if (current.equals(source)) {
                    return path;
                }

                current = previous.get(current);
            }

            return Collections.emptyList();
        }

        public Map<String, Long> getDistances() {
            return Collections.unmodifiableMap(distances);
        }
    }

    public static Result shortestPaths(
            DirectedGraph graph,
            String source
    ) {
        if (!graph.getVertices().contains(source)) {
            throw new IllegalArgumentException(
                    "Unknown source: " + source
            );
        }

        Map<String, Long> distances = new LinkedHashMap<>();
        Map<String, String> previous = new HashMap<>();

        for (String vertex : graph.getVertices()) {
            distances.put(vertex, Long.MAX_VALUE);
        }

        distances.put(source, 0L);

        PriorityQueue<State> queue = new PriorityQueue<>(
                Comparator.comparingLong(state -> state.distance)
        );

        queue.offer(new State(source, 0));

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.distance
                    != distances.get(current.vertex)) {
                continue;
            }

            for (Edge edge :
                    graph.getAdjacentEdges(current.vertex)) {

                String next = edge.getDestination();

                long candidate =
                        current.distance + edge.getWeight();

                if (candidate < distances.get(next)) {
                    distances.put(next, candidate);
                    previous.put(next, current.vertex);

                    queue.offer(new State(next, candidate));
                }
            }
        }

        return new Result(source, distances, previous);
    }
}
