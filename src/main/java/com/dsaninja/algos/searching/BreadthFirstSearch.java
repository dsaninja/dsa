package com.dsaninja.algos.searching;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Breadth First Traversal (or Search) for a graph is similar to Breadth First
 * Traversal of a tree. The only catch here is, unlike trees, graphs may contain
 * cycles, so we may come to the same node again. To avoid processing a node more
 * than once, we use a boolean visited array.
 * <p>
 * Extra memory, as a queue, is needed to keep track of the vertices
 * that were encountered but not yet explored.
 *
 * <h1>Complexity</h1>
 * <p>
 * The time complexity of algorithm actually depends on the data structure being used to represent the graph:
 * <ol>
 * If the graph is represented as an adjacency matrix (a V x V array):
 *  <li>For each node, we will have to traverse an entire row of length V in the matrix to discover all its outgoing edges.</li>
 *  <li> Note that each row in an adjacency matrix corresponds to a node in the graph, and that row stores information about edges emerging from
 *  the node. Hence, the time complexity of BFS in this case is O(V * V) = O(V^2)</li>
 * </ol>
 *
 * <ol>
 * If the graph is represented as adjacency list:
 * <li>Here, each node maintains a list of all its adjacent edges. Let’s assume that there are V number of nodes and E number of edges in the graph
 * .</li>
 * <li>For each node, we discover all its neighbors by traversing its adjacency list just once in linear time.</li>
 * <li>For a directed graph, the sum of the sizes of the adjacency lists of all the nodes is E. So, the time complexity in this case is O(V) + O(E)
 * = O(V + E).</li>
 * <li>For an undirected graph, each edge appears twice. Once in the adjacency list of either end of the edge. The time complexity for this case
 * will be O(V) + O (2E) ~ O(V + E).</li>
 *
 * </ol>
 *
 * @author gaurs
 */
public class BreadthFirstSearch{
    /**
     * Traverse the graph starting from <code>start</code> vertex
     * and performing the action <code>consumer</code> on every vertex.
     *
     * @param graph    the graph to be traversed
     * @param start    the start vertex
     * @param consumer action to be performed
     */
    public void traverse(int[][] graph, int start, Consumer<Integer> consumer){
        // a queue of discovered but NOT yet visited vertices
        Queue<Integer> discoveredVertices = new LinkedList<>();

        // an array of visited vertices
        boolean[] visited = new boolean[graph.length];

        // add start to discovered vertices
        discoveredVertices.add(start);
        while(!discoveredVertices.isEmpty()){
            // poll the vertex from front of queue
            Integer visitedNode = discoveredVertices.poll();

            // mark it visited and perform the action
            visited[visitedNode] = true;
            consumer.accept(visitedNode);

            // for each neighbour check if the two vertices are connected
            // if yes and the neighbour is not visited already
            // add it to the discovered list
            for(int neighbour = 0; neighbour < graph.length; neighbour++){
                // there is a possibility that we have discovered a node but not visited it yet
                // so to avoid adding it again to discoveredVertices queue
                // !discoveredVertices.contains(neighbour) check is required
                if(graph[visitedNode][neighbour] != 0 && !visited[neighbour] && !discoveredVertices.contains(neighbour)){
                    discoveredVertices.add(neighbour);
                }
            }
        }

    }
}
