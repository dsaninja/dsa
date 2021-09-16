package com.dsaninja.algos.search;

import java.util.Stack;
import java.util.function.Consumer;

/**
 * Extra memory, as a Stack, is needed to keep track of the vertices
 * that were encountered but not yet explored.
 *
 * @author gaurs
 */
public class DepthFirstSearch{
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
        Stack<Integer> discoveredVertices = new Stack<>();

        // an array of visited vertices
        boolean[] visited = new boolean[graph.length];

        // add start to discovered vertices
        discoveredVertices.add(start);
        while(!discoveredVertices.isEmpty()){
            // poll the vertex from front of queue
            Integer visitedNode = discoveredVertices.pop();

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
                    discoveredVertices.push(neighbour);
                }
            }
        }

    }
}
