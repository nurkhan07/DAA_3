package smartcity.graph;

public class Edge {
    private final String source;
    private final String destination;
    private final int weight;

    public Edge(String source, String destination, int weight) {
        if (source == null || destination == null
                || source.isBlank() || destination.isBlank()) {
            throw new IllegalArgumentException(
                    "Vertex names cannot be empty"
            );
        }

        if (weight < 0) {
            throw new IllegalArgumentException(
                    "Weight cannot be negative"
            );
        }

        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " -- " + destination
                + " (weight: " + weight + ")";
    }
}