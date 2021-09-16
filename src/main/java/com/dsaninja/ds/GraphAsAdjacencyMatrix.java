package com.dsaninja.ds;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Consumer;
import java.util.Queue;

/**
 * A graph implementation using adjacency matrix representation.
 *
 * @author gaurs
 */
public class GraphAsAdjacencyMatrix{

    private final int[][] vertices;
    private final int numberOfVertex;

    /**
     * Create a graph with specified number of vertices.
     *
     * @param numberOfVertex number of vertices
     */
    public GraphAsAdjacencyMatrix(int numberOfVertex){
        this.numberOfVertex = numberOfVertex;
        vertices = new int[numberOfVertex][numberOfVertex];
    }

    /**
     * Add a new edge between start and end vertex. For un-directional graphs,
     * this will add an edge between end -> start as well.
     *
     * @param start start vertex
     * @param end   end vertex
     * @throws IllegalArgumentException if invalid vertex
     */
    public void addEdge(int start, int end){
        if(start >= 0 && end <= numberOfVertex && start < end){
            vertices[start][end] = 1;
            vertices[end][start] = 1;
        } else{
            throw new IllegalArgumentException("invalid start or end index");
        }
    }

    /**
     * Remove an edge between the start and end vertices.
     * For un-directional graphs, it will remove end -> start
     * as well.
     *
     * @param start start vertex
     * @param end   end vertex
     * @throws IllegalArgumentException if invalid vertex
     */
    public void removeEdge(int start, int end){
        if(start >= 0 && end <= numberOfVertex && start < end){
            vertices[start][end] = 0;
            vertices[end][start] = 0;
        } else{
            throw new IllegalArgumentException("invalid start or end index");
        }
    }

    /**
     * Check if the two vertices denoted by start and end params
     * are connected or not.
     * <p>
     * For checking reverse connection, end > start is fine
     *
     * @param start start vertex
     * @param end   end vertex
     * @return true of connected, false otherwise
     * @throws IllegalArgumentException if invalid vertex
     */
    public boolean isConnected(int start, int end){
        if(start >= 0 && start < numberOfVertex && end > 0 && end <= numberOfVertex){
            return vertices[start][end] == 1;
        } else{
            throw new IllegalArgumentException("invalid start or end index");
        }
    }

    /**
     * The method returns the count of vertices, this vertex is connected to
     *
     * @param vertex the vertex for which degree needs to be calculated
     * @return number of connected vertices
     */
    public int degree(int vertex){
        int count = 0;
        for(int i = 0; i < numberOfVertex; i++){
            if(vertices[vertex][i] == 1){
                count++;
            }
        }

        return count;
    }

    public void bfs(Consumer<Integer> consumer){
        if(numberOfVertex > 0){
            Queue<Integer> neighbours = new LinkedList<>();
            Set<Integer> visited = new HashSet<>();

            // add start node
            neighbours.add(0);

            while(!neighbours.isEmpty()){
                int node = neighbours.poll();

                consumer.accept(node);
                visited.add(node);

                for(int i = 0; i < numberOfVertex; i++){
                    if(vertices[node][i] == 1){
                        if(!visited.contains(i) && !neighbours.contains(i)){
                            neighbours.add(i);
                        }
                    }
                }
            }
        }
    }

    public int getNumberOfVertex(){
        return numberOfVertex;
    }
}
