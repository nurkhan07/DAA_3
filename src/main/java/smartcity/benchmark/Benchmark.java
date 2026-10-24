
package smartcity.benchmark;

import smartcity.graph.Graph;
import smartcity.graph.DirectedGraph;
import smartcity.mst.Kruskal;
import smartcity.mst.Prim;
import smartcity.connectivity.TopologicalSort;
import smartcity.connectivity.Kosaraju;
import smartcity.shortestpath.Dijkstra;

import java.util.Random;

public class Benchmark {

    private static final int RUNS = 5;

    private static long measure(Runnable algorithm) {
        long totalTime = 0;

        // Warm-up run to reduce some JVM startup effects
        algorithm.run();

        for (int i = 0; i < RUNS; i++) {
            long start = System.nanoTime();
            algorithm.run();
            long end = System.nanoTime();

            totalTime += end - start;
        }

        return totalTime / RUNS;
    }

    private static Graph createUndirectedGraph(int n, long seed) {
        Graph graph = new Graph();
        Random random = new Random(seed);

        for (int i = 0; i < n; i++) {
            graph.addVertex("V" + i);
        }

        // Ensure the graph is connected
        for (int i = 1; i < n; i++) {
            graph.addEdge("V" + (i - 1), "V" + i,
                    1 + random.nextInt(100));
        }

        // Add additional edges
        int extraEdges = 2 * n;
        for (int i = 0; i < extraEdges; i++) {
            int a = random.nextInt(n);
            int b = random.nextInt(n);

            if (a != b) {
                graph.addEdge("V" + a, "V" + b,
                        1 + random.nextInt(100));
            }
        }

        return graph;
    }

    private static DirectedGraph createDAG(int n, long seed) {
        DirectedGraph graph = new DirectedGraph();
        Random random = new Random(seed);

        for (int i = 0; i < n; i++) {
            graph.addVertex("V" + i);
        }

        // Edges go from a lower index to a higher index,
        // so the graph cannot contain a directed cycle.
        for (int i = 0; i < n - 1; i++) {
            graph.addEdge("V" + i, "V" + (i + 1),
                    1 + random.nextInt(100));
        }

        for (int i = 0; i < 2 * n; i++) {
            int from = random.nextInt(n);
            int to = random.nextInt(n);

            if (from < to) {
                graph.addEdge("V" + from, "V" + to,
                        1 + random.nextInt(100));
            }
        }

        return graph;
    }

    private static void benchmarkSize(int n) {
        Graph undirected = createUndirectedGraph(n, 42L + n);
        DirectedGraph directed = createDAG(n, 84L + n);
        String source = "V0";

        System.out.println("\nGraph size: " + n);
        System.out.println("Undirected edges: "
                + undirected.getEdgeCount());
        System.out.println("Directed edges: "
                + directed.getEdgeCount());

        System.out.println("Algorithm,Average time (ms)");

        System.out.printf("Kruskal,%.3f%n",
                measure(() -> Kruskal.findMST(undirected))
                        / 1_000_000.0);

        System.out.printf("Prim,%.3f%n",
                measure(() -> Prim.findMST(undirected))
                        / 1_000_000.0);

        System.out.printf("Topological Sort,%.3f%n",
                measure(() -> TopologicalSort.sort(directed))
                        / 1_000_000.0);

        System.out.printf("Kosaraju,%.3f%n",
                measure(() -> Kosaraju.findSCCs(directed))
                        / 1_000_000.0);

        System.out.printf("Dijkstra,%.3f%n",
                measure(() -> Dijkstra.shortestPaths(
                        directed, source))
                        / 1_000_000.0);
    }

    public static void main(String[] args) {
        System.out.println("SMART CITY ALGORITHM BENCHMARK");
        System.out.println("Runs per algorithm: " + RUNS);

        int[] sizes = {10, 100, 1000};

        for (int size : sizes) {
            benchmarkSize(size);
        }

        System.out.println("\nBenchmark completed.");
    }
}
