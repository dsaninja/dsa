package com.dsaninja.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * A graph implementation using adjacency matrix representation.
 *
 * @author gaurs
 */
public class GraphAsAdjacencyList{

    private final List<List<Integer>> vertices;
    private int numberOfVertex;

    /**
     * Create a graph with specified number of vertices.
     *
     * @param numberOfVertex number of vertices
     */
    public GraphAsAdjacencyList(int numberOfVertex){
        this.numberOfVertex = numberOfVertex;
        vertices = new ArrayList<>(numberOfVertex);
        IntStream.range(0, numberOfVertex).forEach(index -> {
            List<Integer> connections = new ArrayList<>(Collections.nCopies(numberOfVertex, 0));
            vertices.add(index, connections);
        });
    }

    /**
     * Add a new vertx in the graph. The new vertex should be a valid index
     * i.e. current count +1 only.
     *
     * @param vertex vertex index
     * @throws IllegalArgumentException in case of illegal vertex
     */
    public void addVertex(int vertex){
        if(vertex == numberOfVertex + 1){
            numberOfVertex = numberOfVertex + 1;
            List<Integer> connections = new ArrayList<>(Collections.nCopies(numberOfVertex, 0));
            vertices.add(connections);
        } else{
            throw new IllegalArgumentException("invalid index");
        }
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
        if(start >= 0 && end <= numberOfVertex){
            vertices.get(start).set(end, 1);
            vertices.get(end).set(start, 1);
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
        if(start >= 0 && end <= numberOfVertex){
            vertices.get(start).set(end, 0);
            vertices.get(end).set(start, 0);
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
        if(start >= 0 && start < numberOfVertex && end > 0 && end <= numberOfVertex){
            return vertices.get(start).get(end) == 1;
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
            if(vertices.get(vertex).get(i) == 1){
                count++;
            }
        }

        return count;
    }

    public int getNumberOfVertex(){
        return numberOfVertex;
    }

    public void bfs(Consumer<Integer> consumer){
        if(numberOfVertex > 0){
            Queue<Integer> neighbours = new LinkedList<>();
            Set<Integer> visited = new HashSet<>();

            // add start node
            neighbours.add(0);

            while(!neighbours.isEmpty()){
                int discovered = neighbours.poll();
                consumer.accept(discovered);
                visited.add(discovered);

                for(int neighbour = 0; neighbour < numberOfVertex; neighbour++){
                    if(vertices.get(discovered).get(neighbour) == 1){
                        if(!visited.contains(neighbour) && !neighbours.contains(neighbour)){
                            neighbours.add(neighbour);
                        }
                    }
                }
            }
        }
    }

    public void dfs(Consumer<Integer> consumer){
        if(numberOfVertex > 0){
            Stack<Integer> neighbours = new Stack<>();
            Set<Integer> visited = new HashSet<>();

            // add start node
            neighbours.push(0);

            while(!neighbours.isEmpty()){
                int discovered = neighbours.pop();
                consumer.accept(discovered);
                visited.add(discovered);

                for(int neighbour = 0; neighbour < numberOfVertex; neighbour++){
                    if(vertices.get(discovered).get(neighbour) == 1){
                        if(!visited.contains(neighbour) && !neighbours.contains(neighbour)){
                            neighbours.push(neighbour);
                        }
                    }
                }
            }
        }
    }
}
