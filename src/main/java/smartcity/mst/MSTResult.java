
package smartcity.mst;

import smartcity.graph.Edge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MSTResult {
    private final List<Edge> edges;
    private final long totalWeight;
    private final boolean connected;

    public MSTResult(
            List<Edge> edges,
            long totalWeight,
            boolean connected
    ) {
        this.edges = new ArrayList<>(edges);
        this.totalWeight = totalWeight;
        this.connected = connected;
    }

    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    public long getTotalWeight() {
        return totalWeight;
    }

    public boolean isConnected() {
        return connected;
    }
}
