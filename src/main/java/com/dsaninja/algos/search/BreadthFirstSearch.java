package com.dsaninja.algos.search;

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
