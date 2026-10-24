
package smartcity;

import org.junit.jupiter.api.Test;
import smartcity.graph.Graph;
import smartcity.graph.DirectedGraph;
import smartcity.mst.Kruskal;
import smartcity.mst.Prim;
import smartcity.connectivity.TopologicalSort;
import smartcity.connectivity.Kosaraju;
import smartcity.shortestpath.Dijkstra;
import smartcity.dsu.DSU;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmTests {

    @Test
    void testDSU() {
        DSU dsu = new DSU(5);

        assertTrue(dsu.union(0, 1));
        assertTrue(dsu.union(1, 2));
        assertTrue(dsu.connected(0, 2));
        assertFalse(dsu.connected(0, 3));
        assertEquals(3, dsu.getComponentCount());
    }

    @Test
    void testKruskalAndPrim() {
        Graph graph = new Graph();

        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 2);
        graph.addEdge("C", "D", 5);

        var kruskal = Kruskal.findMST(graph);
        var prim = Prim.findMST(graph);

        assertTrue(kruskal.isConnected());
        assertTrue(prim.isConnected());
        assertEquals(6, kruskal.getTotalWeight());
        assertEquals(kruskal.getTotalWeight(),
                prim.getTotalWeight());
        assertEquals(3, kruskal.getEdges().size());
        assertEquals(3, prim.getEdges().size());
    }

    @Test
    void testTopologicalSort() {
        DirectedGraph graph = new DirectedGraph();

        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 1);
        graph.addEdge("A", "C", 1);

        List<String> order = TopologicalSort.sort(graph);

        assertEquals(3, order.size());
        assertTrue(order.indexOf("A") < order.indexOf("B"));
        assertTrue(order.indexOf("B") < order.indexOf("C"));
    }

    @Test
    void testTopologicalSortDetectsCycle() {
        DirectedGraph graph = new DirectedGraph();

        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "A", 1);

        assertThrows(
                IllegalArgumentException.class,
                () -> TopologicalSort.sort(graph)
        );
    }

    @Test
    void testKosaraju() {
        DirectedGraph graph = new DirectedGraph();

        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "A", 1);
        graph.addEdge("B", "C", 1);
        graph.addEdge("C", "D", 1);
        graph.addEdge("D", "C", 1);

        List<List<String>> components = Kosaraju.findSCCs(graph);

        assertEquals(2, components.size());
        assertTrue(components.stream()
                .anyMatch(c -> c.containsAll(List.of("A", "B"))
                        && c.size() == 2));
        assertTrue(components.stream()
                .anyMatch(c -> c.containsAll(List.of("C", "D"))
                        && c.size() == 2));
    }

    @Test
    void testDijkstra() {
        DirectedGraph graph = new DirectedGraph();

        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 10);
        graph.addEdge("B", "C", 2);
        graph.addEdge("C", "D", 1);
        graph.addEdge("B", "D", 8);

        Dijkstra.Result result = Dijkstra.shortestPaths(graph, "A");

        assertEquals(0, result.getDistanceTo("A"));
        assertEquals(4, result.getDistanceTo("B"));
        assertEquals(6, result.getDistanceTo("C"));
        assertEquals(7, result.getDistanceTo("D"));

        assertEquals(
                List.of("A", "B", "C", "D"),
                result.getPathTo("D")
        );
    }

    @Test
    void testDijkstraUnreachableVertex() {
        DirectedGraph graph = new DirectedGraph();

        graph.addEdge("A", "B", 2);
        graph.addVertex("C");

        Dijkstra.Result result = Dijkstra.shortestPaths(graph, "A");

        assertEquals(Long.MAX_VALUE, result.getDistanceTo("C"));
        assertTrue(result.getPathTo("C").isEmpty());
    }
}
