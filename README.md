# Smart City Infrastructure and Route Planning

## 1. Project Overview

The **Smart City Infrastructure and Route Planning** project demonstrates the use of graph algorithms to model and analyze a city's infrastructure network.

In this project, locations such as airports, universities, hospitals, and city centers are represented as vertices, while connections between locations are represented as weighted edges.

The project implements algorithms for finding minimum spanning trees, analyzing connectivity, ordering dependent tasks, and finding shortest paths.

## 2. Project Objectives

The main objectives are:

- Represent city infrastructure using graph data structures.
- Implement the Disjoint Set Union (DSU) data structure.
- Find minimum spanning trees using Kruskal's and Prim's algorithms.
- Perform topological sorting on directed graphs.
- Find strongly connected components using Kosaraju's algorithm.
- Calculate shortest paths using Dijkstra's algorithm.
- Compare algorithm results and verify their correctness.
- Evaluate algorithm performance in the next project stage.

## 3. Technologies Used

- **Programming Language:** Java
- **JDK:** Java 17 or Java 21
- **IDE:** IntelliJ IDEA
- **Build Tool:** Maven
- **Version Control:** Git and GitHub

## 4. Project Structure

```text
assignment-3-smart-city/
├── pom.xml
├── .gitignore
├── README.md
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
    │           └── shortestpath/
    │               └── Dijkstra.java
    └── test/
        └── java/
```

## 5. Graph Model

The project uses two graph representations.

### 5.1 Undirected Weighted Graph

The `Graph` class represents connections between city locations. Each edge has a source, destination, and weight.

The graph is used for minimum spanning tree algorithms.

Example:

```text
City Center -- University (4)
University -- Hospital (3)
University -- Airport (7)
```

### 5.2 Directed Weighted Graph

The `DirectedGraph` class represents one-way connections between locations or dependencies between tasks.

An edge from vertex A to vertex B does not automatically create an edge from B to A.

This representation is used by topological sorting, Kosaraju's algorithm, and Dijkstra's algorithm.

### 5.3 Edge Class

The `Edge` class stores:

- Source vertex
- Destination vertex
- Edge weight

The class validates vertex names and prevents negative edge weights.

## 6. Implemented Algorithms

### 6.1 Disjoint Set Union (DSU)

DSU efficiently manages groups of connected elements.

Implemented optimizations:

- Path compression
- Union by size

**Time complexity:** Nearly constant amortized time per operation, commonly expressed as \(O(\alpha(V))\), where \(\alpha\) is the inverse Ackermann function.

### 6.2 Kruskal's Algorithm

Kruskal's algorithm finds a minimum spanning tree by processing edges in ascending order of weight.

Steps:

1. Sort edges by weight.
2. Initialize the DSU structure.
3. Select an edge if it connects two different components.
4. Stop when a connected graph has \(V-1\) selected edges or all edges have been processed.

**Time complexity:** \(O(E \log E)\).

### 6.3 Prim's Algorithm

Prim's algorithm constructs a minimum spanning tree by repeatedly selecting the lowest-weight edge connecting the current tree to an unvisited vertex.

The implementation uses a priority queue and also handles disconnected graphs by producing a minimum spanning forest.

**Time complexity:** \(O(E \log E)\) with a priority queue and adjacency lists.

### 6.4 Topological Sorting

Topological sorting orders the vertices of a directed acyclic graph (DAG) so that every directed edge \(u \rightarrow v\) places \(u\) before \(v\).

The project uses **Kahn's algorithm**:

1. Calculate the in-degree of each vertex.
2. Add vertices with in-degree zero to a queue.
3. Remove vertices from the queue and update the in-degrees of their neighbors.
4. If not all vertices are processed, the graph contains a cycle and cannot have a topological ordering.

**Time complexity:** \(O(V+E)\).

### 6.5 Kosaraju's Algorithm

Kosaraju's algorithm identifies strongly connected components (SCCs) in a directed graph.

Two vertices belong to the same strongly connected component if each vertex is reachable from the other.

The algorithm performs these steps:

1. Run DFS on the original graph and record vertices in finishing order.
2. Reverse all graph edges.
3. Process vertices in reverse finishing order using DFS on the reversed graph.
4. Each DFS traversal identifies one strongly connected component.

**Time complexity:** \(O(V+E)\).

### 6.6 Dijkstra's Algorithm

Dijkstra's algorithm finds shortest paths from a source vertex to all reachable vertices in a graph with non-negative edge weights.

The implementation uses a priority queue to process the vertex with the smallest known distance.

The `Dijkstra.Result` class stores distances and previous vertices, allowing the program to reconstruct the shortest path to a destination.

**Time complexity:** \(O((V+E)\log V)\) with adjacency lists and a priority queue.

## 7. Demonstration Scenarios

### 7.1 Minimum Spanning Tree

The undirected city graph contains the following edges:

| Connection | Weight |
|---|---:|
| City Center — Airport | 10 |
| City Center — University | 4 |
| University — Airport | 7 |
| University — Hospital | 3 |
| Airport — Hospital | 12 |

The graph contains four vertices and five edges.

The expected minimum spanning tree contains:

| Selected edge | Weight |
|---|---:|
| University — Hospital | 3 |
| City Center — University | 4 |
| University — Airport | 7 |
| **Total weight** | **14** |

Both Kruskal's and Prim's algorithms should find a minimum spanning tree with total weight **14** and three selected edges.

### 7.2 Topological Sorting

The directed graph contains these dependencies:

- Prepare Site → Build Road
- Build Road → Open Route
- Install Lights → Open Route

A valid topological order must place:

- `Prepare Site` before `Build Road`
- `Build Road` before `Open Route`
- `Install Lights` before `Open Route`

More than one valid ordering may exist.

### 7.3 Strongly Connected Components

The directed graph contains these connections:

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

The order of components and vertices in the printed output may differ.

### 7.4 Shortest Path

The directed graph contains these weighted edges:

| Connection | Weight |
|---|---:|
| City Center → University | 4 |
| City Center → Airport | 10 |
| University → Airport | 2 |
| University → Hospital | 5 |
| Airport → Hospital | 1 |

The expected shortest distances from City Center are:

| Destination | Shortest distance |
|---|---:|
| City Center | 0 |
| University | 4 |
| Airport | 6 |
| Hospital | 7 |

The expected shortest route to Hospital is:

```text
City Center → University → Airport → Hospital
```

The total route weight is **7**.

## 8. How to Run the Project

### Using IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Make sure the project SDK is configured for Java 17 or Java 21.
3. Wait for Maven dependencies and project configuration to finish loading.
4. Open `src/main/java/smartcity/Main.java`.
5. Run the `main()` method.

The program demonstrates the implemented algorithms and prints their results to the console.

### Using Maven

If Maven is configured in the project, compile the source code with:

```bash
mvn clean compile
```

The main class can be run directly from IntelliJ IDEA. Running it through Maven requires a suitable execution configuration or plugin.

## 9. Correctness Validation

The following expected results are used to check the implementation:

- Kruskal's algorithm returns a minimum spanning tree with total weight 14.
- Prim's algorithm returns a minimum spanning tree with total weight 14.
- Both MST algorithms select three edges for the connected four-vertex graph.
- Topological sorting respects all task dependencies.
- Kosaraju's algorithm identifies the components `{A, B}` and `{C, D}`.
- Dijkstra's algorithm calculates distances of 0, 4, 6, and 7 from City Center to the four destinations.

These are expected results based on the demonstration graphs. Automated unit tests and broader edge-case validation are planned for the next stage.

## 10. Algorithm Complexity Summary

| Algorithm | Time Complexity | Purpose |
|---|---|---|
| DSU | \(O(\alpha(V))\) amortized per operation | Manage connected components |
| Kruskal | \(O(E\log E)\) | Find a minimum spanning tree |
| Prim | \(O(E\log E)\) | Find a minimum spanning tree |
| Topological Sort | \(O(V+E)\) | Order dependent tasks |
| Kosaraju | \(O(V+E)\) | Find strongly connected components |
| Dijkstra | \(O((V+E)\log V)\) | Find shortest paths |

Here, \(V\) represents the number of vertices and \(E\) represents the number of edges.

## 11. Development Progress

### Day 1 — Project Setup and Graph Model

- Created the Maven project.
- Added the `Edge` class.
- Implemented the undirected `Graph` class.
- Created an initial city network.
- Configured Git and GitHub.

### Day 2 — Minimum Spanning Tree Algorithms

- Implemented the DSU data structure.
- Implemented Kruskal's algorithm.
- Implemented Prim's algorithm.
- Compared the MST results.

### Day 3 — Connectivity and Shortest Paths

- Implemented the `DirectedGraph` class.
- Implemented topological sorting using Kahn's algorithm.
- Implemented Kosaraju's algorithm for strongly connected components.
- Implemented Dijkstra's shortest-path algorithm.
- Added demonstrations for all algorithms to `Main.java`.

### Day 4 — Planned Testing and Benchmarking

- Create automated unit tests.
- Test normal cases and edge cases.
- Generate graphs of different sizes.
- Measure execution times and validate results.

### Day 5 — Planned Analysis and Final Report

- Collect benchmark results.
- Prepare comparison tables and plots.
- Analyze algorithm performance.
- Finalize the report and project documentation.

## 12. Version Control

Git is used to track the development process. Each development stage should be committed separately to document progress.

Suggested commits:

```text
Day 1: Set up project and implement graph model
Day 2: Implement DSU Kruskal and Prim MST algorithms
Day 3: Implement connectivity and shortest path algorithms
Day 4: Add unit tests and performance benchmarks
Day 5: Add experimental analysis and finalize documentation
```

These are suggested commit messages. The Git history should reflect the work actually completed at each stage.

## 13. Conclusion

By the end of Day 3, the project includes graph data structures and six core graph-related algorithm implementations: DSU, Kruskal, Prim, topological sorting, Kosaraju, and Dijkstra.

Together, these algorithms demonstrate how graph theory can support infrastructure planning, dependency management, connectivity analysis, and route optimization in a smart city.

Automated testing, performance measurements, and final experimental analysis remain part of the next development stages.
