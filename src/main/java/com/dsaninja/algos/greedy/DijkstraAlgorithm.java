package com.dsaninja.algos.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * Given a graph and a source vertex in the graph, find the shortest
 * paths from the source to all vertices or a given one in the given graph.
 * <p>
 * Consider there are V number of vertices in a graph. Then by definition,
 * there would be |V-1| number of edges.
 * <p>
 * <h1>Complexity</h1>
 *
 * <h2>Without priority Queue:</h2>
 * <ol>
 *     <li>O(V) * (O(V) + O(NV))</li>
 *     <li>O(V) * ((N+1)V)</li>
 *     <li>O(V) * (NV) ~ O(V^2)</li>
 * </ol>
 *
 * <h2>With Priority Queue</h2>
 * <ol>
 *     <li>O(V) * (O(log V) + O(NlogV))</li>
 *     <li>O(V) * ((N+1)log V)</li>
 *     <li>O(V) * (N log V)</li>
 *     <li>O(NV log V) ; N is the maximum number of edges attached to a single node</li>
 * </ol>
 * Assuming, E = O(VN) - a tighter estimation, the overall complexity is O(E logV) with PQ
 *
 * @author gaurs
 */
public class DijkstraAlgorithm{

    public int traverse(int[][] graph, int start, int end, Consumer<Integer> consumer){
        record Node(int vertexId, int weight){
        }

        // create a priority queue that keeps vertices with minimum weight on front
        Queue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparing(Node::weight));

        // the distance array wil hold distance to ith node from start node
        int[] distance = IntStream.range(0, graph.length).toArray();
        // O(V)
        Arrays.fill(distance, Integer.MAX_VALUE);

        // visited array to track the visited vertices (avoid cycles)
        boolean[] visited = new boolean[graph.length];

        // to track the path from start to end
        int[] predecessor = new int[graph.length];

        priorityQueue.add(new Node(start, 0));
        distance[start] = 0;

        // O(V)
        while(!priorityQueue.isEmpty()){
            // fetch the vertex with minimum weight O(log V) to maintain PQ - heapify
            int discoveredNode = priorityQueue.poll().vertexId();

            // mark it visited
            visited[discoveredNode] = true;

            // for all of its neighbours O(E)
            for(int neighbour = 0; neighbour < graph.length; neighbour++){
                // if there is a path from discoveredNode to neighbour
                if(graph[discoveredNode][neighbour] != 0 &&
                        // and the neighbour is not visited
                        !visited[neighbour] &&
                        // and the current calculated distance is more than the new one
                        distance[neighbour] > graph[discoveredNode][neighbour] + distance[discoveredNode]){
                    // O(log V)
                    priorityQueue.add(new Node(neighbour, graph[discoveredNode][neighbour] + distance[discoveredNode]));
                    distance[neighbour] = graph[discoveredNode][neighbour] + distance[discoveredNode];
                    predecessor[neighbour] = discoveredNode;
                }
            }
        }

        // backtrack the path
        int tracker = end;
        consumer.accept(end);
        while(tracker != start){
            consumer.accept(predecessor[tracker]);
            tracker = predecessor[tracker];
        }

        return distance[end];
    }
}
