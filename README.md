# Smart City Infrastructure and Route Planning

## 1. Project Overview

The **Smart City Infrastructure and Route Planning** project focuses on implementing and analyzing graph algorithms that can be used to model and optimize urban infrastructure networks.

In this project, a city is represented as a weighted graph. Vertices represent locations such as a city center, airport, university, and hospital. Edges represent roads connecting these locations, while edge weights represent non-negative distances or infrastructure costs.

The project aims to implement graph data structures and algorithms for minimum spanning trees, connectivity analysis, topological ordering, and shortest-path calculation. Their correctness and performance will be evaluated using test cases and experimental measurements.

## 2. Project Objectives

The main objectives are:

- Implement a reusable weighted graph data structure.
- Implement Disjoint Set Union (DSU) with path compression and union by size.
- Find minimum spanning trees using Kruskal's and Prim's algorithms.
- Analyze connectivity and strongly connected components.
- Implement topological sorting for directed acyclic graphs.
- Calculate shortest paths using Dijkstra's algorithm.
- Test algorithm correctness on different graph configurations.
- Measure algorithm execution time on graphs of different sizes.
- Compare experimental results with theoretical time and space complexity.

## 3. Technology Stack

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Maven | Project configuration and build management |
| IntelliJ IDEA | Development environment |
| Git | Version control |
| GitHub | Source code hosting and collaboration |
| JUnit | Planned automated testing |
| CSV | Planned storage of benchmark results |

The Java version is determined by the JDK configured in the project and the compiler release specified in `pom.xml`.

## 4. Project Structure

```text
assignment-3-smart-city/
├── .gitignore
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   │       └── smartcity/
│   │           ├── Main.java
│   │           ├── graph/
│   │           │   ├── Edge.java
│   │           │   └── Graph.java
│   │           ├── dsu/
│   │           │   └── DSU.java
│   │           └── mst/
│   │               ├── MSTResult.java
│   │               ├── Kruskal.java
│   │               └── Prim.java
│   └── test/
│       └── java/
└── results/
    ├── tables/
    └── plots/
```

The `test` and `results` directories will be populated as testing and benchmarking are implemented. The structure above describes the intended project organization; files not yet created are planned components.

## 5. Graph Model

### 5.1 Vertices

Each vertex represents a city location. In the current implementation, vertices are identified by strings, for example:

- City Center
- Airport
- University
- Hospital

### 5.2 Edges

An edge represents a connection between two locations and contains:

- Source vertex
- Destination vertex
- Weight

The `Edge` class validates vertex names and rejects negative weights. Its fields are immutable after construction.

### 5.3 Graph Representation

The `Graph` class uses two data structures:

1. **Adjacency list** — stores the edges adjacent to each vertex.
2. **Edge list** — stores each inserted edge once.

The current road network is undirected. Therefore, adding a road between two different locations makes it available from both endpoints in the adjacency list, while the edge list stores the road once.

The graph supports adding vertices and edges, retrieving vertices and edges, retrieving adjacent edges, counting vertices and edges, and printing the adjacency list.

## 6. Implemented Algorithms

### 6.1 Disjoint Set Union (DSU)

The `DSU` class maintains a collection of disjoint sets.

It implements:

- `find(x)` — finds the representative of the set containing an element.
- `union(a, b)` — merges two sets if they are different.
- `connected(a, b)` — checks whether two elements belong to the same set.
- `getComponentCount()` — returns the number of remaining components.

Two optimizations are used:

- **Path compression:** shortens parent paths during `find`.
- **Union by size:** attaches the smaller component to the larger component.

The amortized time complexity of DSU operations with these optimizations is nearly constant, conventionally expressed as \(O(\alpha(V))\), where \(\alpha\) is the inverse Ackermann function.

### 6.2 Kruskal's Algorithm

Kruskal's algorithm constructs a minimum spanning tree by processing edges in non-decreasing order of weight.

Procedure:

1. Sort all edges by weight.
2. Initialize a DSU containing all vertices.
3. Process the sorted edges.
4. Add an edge when its endpoints belong to different components.
5. Stop after selecting \(V-1\) edges, or after processing all edges if the graph is disconnected.

For a graph with \(V\) vertices and \(E\) edges, the time complexity is:

\[
O(E\log E)
\]

The algorithm returns the selected edges, their total weight, and a connectivity indicator.

For disconnected graphs, it returns a minimum spanning forest instead of incorrectly reporting a spanning tree.

### 6.3 Prim's Algorithm

Prim's algorithm grows a spanning tree by repeatedly choosing the minimum-weight edge connecting the current tree to an unvisited vertex.

The implementation uses a priority queue to select candidate edges.

Procedure:

1. Choose an unvisited vertex as the starting point.
2. Add its adjacent edges to a priority queue.
3. Select the lowest-weight edge leading to an unvisited vertex.
4. Add that edge and vertex to the result.
5. Repeat until no eligible edges remain.
6. Start again from another unvisited vertex if the graph is disconnected.

With an adjacency list and a binary heap priority queue, the typical time complexity is:

\[
O((V+E)\log E)
\]

The algorithm returns a minimum spanning forest for disconnected graphs and reports whether the original graph is connected.

### 6.4 Common MST Result

The `MSTResult` class provides a common result format for Kruskal's and Prim's algorithms.

It stores:

- Selected edges
- Total weight
- Whether the graph is connected

Using the same result structure makes it easier to compare both algorithms and test their correctness.

## 7. Current Example Network

The initial demonstration uses four city locations and five roads.

| Source | Destination | Weight |
|---|---|---:|
| City Center | Airport | 10 |
| City Center | University | 4 |
| University | Airport | 7 |
| University | Hospital | 3 |
| Airport | Hospital | 12 |

The graph contains:

- Number of vertices: 4
- Number of edges: 5
- Graph type: Undirected and weighted
- Edge weights: Non-negative integers

### Expected Minimum Spanning Tree

For this example, the minimum spanning tree contains the following roads:

| Road | Weight |
|---|---:|
| University — Hospital | 3 |
| City Center — University | 4 |
| University — Airport | 7 |
| **Total** | **14** |

A spanning tree of a connected graph with four vertices must contain exactly three edges.

Both Kruskal's and Prim's algorithms should return a connected result with three edges and a total weight of 14 for this example.

This value is an expected result derived from the example input. It should be verified by running the application.

## 8. How to Run the Project

### Requirements

- A compatible Java Development Kit (JDK)
- IntelliJ IDEA or another Java IDE
- Maven, either installed separately or provided through the IDE
- Git for version control

### Run in IntelliJ IDEA

1. Clone the GitHub repository.
2. Open the project in IntelliJ IDEA.
3. Allow IntelliJ IDEA to import the Maven project.
4. Confirm that the configured JDK matches `pom.xml`.
5. Open `src/main/java/smartcity/Main.java`.
6. Run the `main` method.

### Run with Maven

Compile the project:

```bash
mvn clean compile
```

Run the application from IntelliJ IDEA, or use Maven with an appropriate execution plugin if one is configured.

The initial Maven configuration does not necessarily include a plugin for launching the main class directly.

## 9. Correctness Validation

The current demonstration provides an initial correctness check for the MST algorithms.

The expected conditions are:

1. The graph contains four vertices.
2. The graph contains five edges.
3. Both algorithms return three edges.
4. Both algorithms report that the graph is connected.
5. Both algorithms return total weight 14.

Additional tests should cover:

- Empty graphs and graphs with one vertex.
- Disconnected graphs.
- Graphs containing cycles.
- Graphs with equal edge weights.
- Self-loops and parallel edges.
- Invalid vertex names and invalid DSU indices.
- Vertices that cannot be reached from a given source.

Automated JUnit tests will be added as part of the testing phase.

## 10. Planned Algorithms

The following components are planned for subsequent development.

### Topological Sorting

Topological sorting will order the vertices of a directed acyclic graph so that every directed edge goes from an earlier vertex to a later vertex.

Kahn's algorithm will be used, with cycle detection based on whether all vertices can be processed.

Expected time complexity:

\[
O(V+E)
\]

### Strongly Connected Components

Kosaraju's algorithm will identify strongly connected components in a directed graph.

The algorithm uses graph traversal and a reversed graph.

Expected time complexity:

\[
O(V+E)
\]

### Dijkstra's Shortest-Path Algorithm

Dijkstra's algorithm will compute shortest-path distances from a source vertex in a graph with non-negative edge weights.

The implementation is planned to use an adjacency list and a priority queue. It will also support route reconstruction and handling unreachable vertices.

Expected time complexity with a binary heap:

\[
O((V+E)\log V)
\]

## 11. Experimental Methodology

The performance evaluation will be conducted after the algorithm implementations and correctness tests are complete.

### Graph Sizes

The planned experiments will use graphs with different numbers of vertices, including:

- 10 vertices
- 100 vertices
- 1,000 vertices

Additional sizes may be used if needed to demonstrate performance trends.

### Reproducibility

To make the experiments reproducible:

1. Generate graphs using a fixed random seed.
2. Record the number of vertices and edges for each graph.
3. Execute each applicable algorithm five times per test configuration.
4. Measure execution time using `System.nanoTime()`.
5. Calculate the average execution time.
6. Record algorithm-specific operation counts where practical.
7. Save the raw measurements and summarized results.

Correctness checks should be performed separately from timing measurements where possible, to reduce measurement overhead.

### Planned Graphs and Tables

At least two plots are planned:

1. Execution time versus number of vertices.
2. Execution time versus number of edges.

The report will include tables of measured results and a comparison between empirical behavior and theoretical complexity.

No benchmark measurements are reported in this initial version because the experiments have not yet been completed.

## 12. Theoretical Complexity Summary

| Algorithm | Expected Time Complexity | Purpose |
|---|---|---|
| DSU operations | Nearly \(O(1)\) amortized | Manage connected components |
| Kruskal | \(O(E\log E)\) | Minimum spanning tree |
| Prim with priority queue | \(O((V+E)\log E)\) | Minimum spanning tree |
| Kahn's topological sort | \(O(V+E)\) | Order vertices in a DAG |
| Kosaraju | \(O(V+E)\) | Find strongly connected components |
| Dijkstra with binary heap | \(O((V+E)\log V)\) | Single-source shortest paths |

These are theoretical bounds under the stated data-structure assumptions. Actual performance will be assessed experimentally.

## 13. Development Progress

### Day 1 — Project Setup and Graph Model

- Created the Maven project.
- Implemented `Edge` and `Graph`.
- Created a sample city network.
- Added initial project documentation.

### Day 2 — DSU and Minimum Spanning Trees

- Implemented DSU with path compression and union by size.
- Implemented Kruskal's algorithm.
- Implemented Prim's algorithm.
- Added a shared `MSTResult` class.
- Prepared a comparison using the sample graph.

### Day 3 — Connectivity and Shortest Paths

Planned tasks:

- Implement topological sorting.
- Implement strongly connected components.
- Implement Dijkstra's algorithm.
- Validate the algorithms on representative graph inputs.

### Day 4 — Testing and Benchmarking

Planned tasks:

- Add automated correctness tests.
- Test edge cases and disconnected graphs.
- Generate reproducible benchmark graphs.
- Collect execution-time and operation-count measurements.

### Day 5 — Experimental Analysis and Final Report

Planned tasks:

- Summarize measured results.
- Produce tables and plots.
- Compare experimental results with theoretical complexity.
- Complete documentation and verify the repository.

## 14. Version Control Workflow

Git is used to track incremental development.

The project will be updated through small, meaningful commits as individual features are implemented and tested.

Example commit messages:

```text
Set up project and implement graph model
Implement DSU Kruskal and Prim MST algorithms
Implement connectivity and shortest path algorithms
Add algorithm tests and performance benchmarks
Complete experimental analysis and README report
```

These messages describe the planned milestones. The actual Git history records the changes that are committed and pushed to GitHub.

## 15. Conclusion

The project establishes a graph-based model of smart city infrastructure and implements two minimum spanning tree algorithms supported by a Disjoint Set Union data structure.

The sample network provides a small, verifiable example for comparing Kruskal's and Prim's algorithms. The remaining work will extend the project with connectivity algorithms, shortest-path calculation, automated tests, reproducible performance experiments, and a final analysis of theoretical and empirical complexity.

The final conclusions will be updated after the remaining algorithms and experiments have been implemented and verified.