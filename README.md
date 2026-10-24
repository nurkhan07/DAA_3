# Smart City Infrastructure and Route Planning

## 1. Project Overview

The **Smart City Infrastructure and Route Planning** project demonstrates the application of graph data structures and algorithms to smart city infrastructure planning.

A city is represented as a graph in which vertices represent locations, such as airports, universities, hospitals, and city centers. Edges represent connections between locations, and edge weights represent distances, costs, or other numerical measures.

The project implements algorithms for minimum spanning trees, connectivity analysis, dependency ordering, and shortest-path calculation. It also includes automated tests and a benchmarking framework for evaluating algorithm performance.

## 2. Project Objectives

The main objectives of this project are:

- Implement graph data structures for undirected and directed networks.
- Implement the Disjoint Set Union (DSU) data structure.
- Find minimum spanning trees using Kruskal's and Prim's algorithms.
- Perform topological sorting on directed acyclic graphs.
- Identify strongly connected components using Kosaraju's algorithm.
- Find shortest paths using Dijkstra's algorithm.
- Validate algorithm correctness using automated tests.
- Measure execution times for graphs of different sizes.
- Compare experimental results with theoretical time complexities.

## 3. Technologies Used

| Technology | Purpose |
|---|---|
| Java 17 | Programming language |
| Maven | Project configuration and dependency management |
| JUnit 5 | Automated testing |
| IntelliJ IDEA | Development environment |
| Git | Version control |
| GitHub | Source code hosting and collaboration |

## 4. Project Structure

```text
DAA_3/
├── .gitignore
├── pom.xml
├── README.md
├── results/
│   └── benchmark-day4.txt
└── src/
    ├── main/
    │   └── java/
    │       └── smartcity/
    │           ├── Main.java
    │           ├── graph/
    │           │   ├── Edge.java
    │           │   ├── Graph.java
    │           │   └── DirectedGraph.java
    │           ├── dsu/
    │           │   └── DSU.java
    │           ├── mst/
    │           │   ├── MSTResult.java
    │           │   ├── Kruskal.java
    │           │   └── Prim.java
    │           ├── connectivity/
    │           │   ├── TopologicalSort.java
    │           │   └── Kosaraju.java
    │           ├── shortestpath/
    │           │   └── Dijkstra.java
    │           └── benchmark/
    │               └── Benchmark.java
    └── test/
        └── java/
            └── smartcity/
                └── AlgorithmTests.java
```

The `results/benchmark-day4.txt` file should be created after running the benchmark and recording the actual console output.

## 5. Graph Data Structures

### 5.1 Edge

The `Edge` class represents a connection between two vertices. It stores:

- Source vertex
- Destination vertex
- Edge weight

The class validates vertex names and prevents negative edge weights.

### 5.2 Undirected Graph

The `Graph` class represents bidirectional connections between city locations.

Its adjacency list stores neighboring vertices, while a separate edge list stores the original edges for algorithm processing.

The graph supports adding vertices and edges, retrieving graph information, and counting vertices and edges.

### 5.3 Directed Graph

The `DirectedGraph` class represents one-way connections between vertices.

An edge from A to B does not automatically create an edge from B to A. This representation is used by topological sorting, Kosaraju's algorithm, and Dijkstra's algorithm.

## 6. Implemented Algorithms

### 6.1 Disjoint Set Union (DSU)

DSU maintains a collection of disjoint sets and supports efficient connectivity operations.

The implementation uses:

- **Path compression:** reduces the depth of the tree during `find`.
- **Union by size:** attaches the smaller component to the larger component.

The amortized time complexity of each operation is \(O(\alpha(V))\), where \(\alpha\) is the inverse Ackermann function.

### 6.2 Kruskal's Algorithm

Kruskal's algorithm finds a minimum spanning tree (MST) by selecting edges in ascending order of weight.

Algorithm steps:

1. Sort all edges by weight.
2. Initialize DSU components for all vertices.
3. Process the sorted edges.
4. Add an edge if its endpoints belong to different components.
5. Stop after selecting \(V-1\) edges for a connected graph or after processing all edges.

**Time complexity:** \(O(E\log E)\).

The implementation can also return a minimum spanning forest when the graph is disconnected.

### 6.3 Prim's Algorithm

Prim's algorithm constructs a minimum spanning tree by repeatedly selecting the lowest-weight edge connecting the current tree to an unvisited vertex.

The implementation uses an adjacency list and a priority queue.

For disconnected graphs, the implementation processes each unvisited component and constructs a minimum spanning forest.

**Time complexity:** \(O(E\log V)\) for the usual sparse-graph priority-queue implementation.

### 6.4 Topological Sorting

Topological sorting orders vertices in a directed acyclic graph (DAG) so that every vertex appears before its outgoing neighbors.

The project uses Kahn's algorithm:

1. Calculate the in-degree of each vertex.
2. Add all vertices with in-degree zero to a queue.
3. Remove a vertex from the queue and add it to the result.
4. Decrease the in-degree of its outgoing neighbors.
5. Add newly zero-in-degree vertices to the queue.
6. If not all vertices are processed, report a cycle.

**Time complexity:** \(O(V+E)\).

### 6.5 Kosaraju's Algorithm

Kosaraju's algorithm finds strongly connected components (SCCs) in a directed graph.

A strongly connected component is a maximal group of vertices in which every vertex can reach every other vertex.

The algorithm:

1. Performs DFS on the original graph and records finishing order.
2. Reverses all directed edges.
3. Processes vertices in reverse finishing order.
4. Uses DFS on the reversed graph to identify components.

**Time complexity:** \(O(V+E)\).

### 6.6 Dijkstra's Algorithm

Dijkstra's algorithm calculates shortest-path distances from a source vertex to all reachable vertices in a graph with non-negative edge weights.

The implementation uses a priority queue to process vertices with the smallest known distances.

The result stores distances and predecessor information so that the shortest path to a destination can be reconstructed.

**Time complexity:** \(O((V+E)\log V)\) using adjacency lists and a priority queue.

## 7. Demonstration Scenarios

### 7.1 Minimum Spanning Tree

The example city network contains four vertices and five edges.

| Connection | Weight |
|---|---:|
| City Center — Airport | 10 |
| City Center — University | 4 |
| University — Airport | 7 |
| University — Hospital | 3 |
| Airport — Hospital | 12 |

The expected minimum spanning tree contains:

| Selected edge | Weight |
|---|---:|
| University — Hospital | 3 |
| City Center — University | 4 |
| University — Airport | 7 |
| **Total weight** | **14** |

Both Kruskal's and Prim's algorithms should return a minimum spanning tree with three edges and total weight **14**.

### 7.2 Topological Sorting

The example dependency graph contains:

- Prepare Site → Build Road
- Build Road → Open Route
- Install Lights → Open Route

A valid topological ordering must place `Prepare Site` before `Build Road`, and both `Build Road` and `Install Lights` before `Open Route`.

Multiple valid orderings are possible.

### 7.3 Strongly Connected Components

The example graph contains:

- A → B
- B → A
- B → C
- C → D
- D → C

The expected strongly connected components are:

```text
[A, B]
[C, D]
```

The order of components in the printed result may differ.

### 7.4 Shortest Path

The example directed graph contains:

| Connection | Weight |
|---|---:|
| City Center → University | 4 |
| City Center → Airport | 10 |
| University → Airport | 2 |
| University → Hospital | 5 |
| Airport → Hospital | 1 |

Expected shortest distances from City Center:

| Destination | Shortest distance |
|---|---:|
| City Center | 0 |
| University | 4 |
| Airport | 6 |
| Hospital | 7 |

The expected shortest route to Hospital is:

`City Center → University → Airport → Hospital`

The total weight of this route is **7**.

## 8. Automated Testing

The project uses JUnit 5 to verify algorithm correctness.

The `AlgorithmTests` class includes tests for:

- DSU union and connectivity operations.
- Kruskal's and Prim's minimum spanning trees.
- Topological ordering.
- Cycle detection in a directed graph.
- Strongly connected components.
- Dijkstra's shortest-path distances.
- Unreachable vertices in Dijkstra's algorithm.

### Running Tests

In IntelliJ IDEA:

1. Open `AlgorithmTests.java`.
2. Right-click the file.
3. Select **Run 'AlgorithmTests'**.

Alternatively, if Maven is available in the terminal, execute:

```bash
mvn clean test
```

The expected result is a successful build with all seven tests passing.

Automated testing helps identify incorrect results and regression errors when the implementation changes.

## 9. Performance Benchmarking

The `Benchmark` class measures the execution time of five algorithms:

- Kruskal
- Prim
- Topological Sort
- Kosaraju
- Dijkstra

### 9.1 Experimental Setup

The benchmark is configured to test graphs with:

| Number of vertices | Purpose |
|---:|---|
| 10 | Small graph |
| 100 | Medium graph |
| 1,000 | Large graph |

The generator creates connected undirected graphs for MST algorithms and directed acyclic graphs for topological sorting, Kosaraju, and Dijkstra.

Fixed random seeds make graph generation reproducible for the same configuration.

Each algorithm is executed once for warm-up, followed by five timed runs. The reported measurement is the average execution time of the timed runs.

Execution time is measured using `System.nanoTime()` and converted to milliseconds.

### 9.2 Benchmark Results

Actual execution times should be recorded after running the program.

| Algorithm | 10 vertices (ms) | 100 vertices (ms) | 1,000 vertices (ms) |
|---|---:|---:|---:|
| Kruskal | To measure | To measure | To measure |
| Prim | To measure | To measure | To measure |
| Topological Sort | To measure | To measure | To measure |
| Kosaraju | To measure | To measure | To measure |
| Dijkstra | To measure | To measure | To measure |

The table is a template. Replace `To measure` with actual benchmark output before submitting the final report.

### 9.3 Limitations

The benchmark is intended for educational comparison rather than precise microbenchmarking.

Results may vary depending on:

- CPU and available memory.
- Java Virtual Machine optimizations.
- Background system activity.
- Graph structure and density.
- Number of repetitions.

For a more rigorous evaluation, additional repetitions, multiple graph instances, and a dedicated benchmarking framework such as JMH could be used.

## 10. Theoretical Complexity Comparison

| Algorithm | Time Complexity | Main Purpose |
|---|---|---|
| DSU | \(O(\alpha(V))\) amortized per operation | Maintain disjoint components |
| Kruskal | \(O(E\log E)\) | Minimum spanning tree |
| Prim | \(O(E\log V)\) | Minimum spanning tree |
| Topological Sort | \(O(V+E)\) | Order dependent tasks |
| Kosaraju | \(O(V+E)\) | Find strongly connected components |
| Dijkstra | \(O((V+E)\log V)\) | Find shortest paths |

Here, \(V\) is the number of vertices and \(E\) is the number of edges.

Theoretical complexity describes how algorithmic work grows with graph size. Experimental measurements help illustrate this behavior but may differ due to implementation and hardware factors.

## 11. Development Progress

### Day 1 — Project Setup and Graph Model

- Created the Java Maven project.
- Implemented the `Edge` class.
- Implemented the undirected `Graph` class.
- Created the initial city network.
- Configured Git and GitHub.

### Day 2 — Minimum Spanning Tree Algorithms

- Implemented DSU with path compression and union by size.
- Implemented Kruskal's algorithm.
- Implemented Prim's algorithm.
- Compared the MST results using the demonstration graph.

### Day 3 — Connectivity and Shortest Paths

- Implemented the `DirectedGraph` class.
- Implemented topological sorting using Kahn's algorithm.
- Implemented Kosaraju's algorithm.
- Implemented Dijkstra's algorithm.
- Added demonstrations to `Main.java`.

### Day 4 — Testing and Benchmarking

- Added JUnit 5 configuration to Maven.
- Created automated algorithm tests.
- Added a benchmark for graphs of different sizes.
- Prepared a results file for measured execution times.

### Day 5 — Final Analysis (Planned)

- Analyze measured execution times.
- Complete performance comparison tables.
- Generate graphs from actual measurements.
- Compare experimental results with theoretical complexity.
- Finalize the project documentation.

## 12. Version Control

Git and GitHub are used to maintain the development history.

Suggested commit messages:

```text
Set up project and implement graph model
Implement DSU Kruskal and Prim MST algorithms
Implement connectivity and shortest path algorithms
Add unit tests and performance benchmarks
Add experimental analysis and finalize documentation
```

Each commit should correspond to work actually completed. The Git history documents the implementation process and changes made throughout the project.

## 13. Conclusion

The project demonstrates how graph algorithms can support smart city infrastructure planning.

Kruskal's and Prim's algorithms identify minimum-cost infrastructure networks. Topological sorting orders dependent tasks, Kosaraju's algorithm identifies strongly connected components, and Dijkstra's algorithm calculates shortest paths.

The project also includes automated tests and a framework for measuring algorithm performance. The final experimental analysis will use actual benchmark results to compare measured execution times with theoretical complexity.

This combination of implementation, validation, and performance evaluation provides a practical demonstration of graph algorithms and their applications.
