package com.dsaninja.ds;

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
        if(start < numberOfVertex && end < numberOfVertex){
            vertices[start - 1][end - 1] = 1;
            vertices[end - 1][start - 1] = 1;
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
        if(start < numberOfVertex && end < numberOfVertex){
            vertices[start - 1][end - 1] = 0;
            vertices[end - 1][start - 1] = 0;
        } else{
            throw new IllegalArgumentException("invalid start or end index");
        }
    }

    /**
     * Check if the two vertices denoted by start and end params
     * are connected or not.
     *
     * @param start start vertex
     * @param end   end vertex
     * @return true of connected, false otherwise
     * @throws IllegalArgumentException if invalid vertex
     */
    public boolean isConnected(int start, int end){
        if(start < numberOfVertex && end < numberOfVertex){
            return vertices[start - 1][end - 1] == 1;
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
            if(vertices[vertex - 1][i] == 1){
                count++;
            }
        }

        return count;
    }

    public int getNumberOfVertex(){
        return numberOfVertex;
    }
}
