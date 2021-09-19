package com.dsaninja.algos.dp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * Bellman Ford algorithm helps us find the shortest path from a
 * vertex to all other vertices of a weighted graph. It is similar
 * to {@link com.dsaninja.algos.greedy.DijkstraAlgorithm} but it
 * can work with graphs in which edges can have negative weights.
 *
 * @author gaurs
 */
public class BellmanFordSP{
    private record Edge(int start, int end, int weight){
    }

    /**
     * @param graph        the graph to traverse
     * @param start        start vertex
     * @param end          end vertex
     * @param pathConsumer the action to be performed on each vertex in the path
     * @return true if no cycle detected, false otherwise
     */
    public boolean shortestPath(int[][] graph, int start, int end, Consumer<Integer> pathConsumer){
        int vertexCount = graph.length;

        List<Edge> edges = prepareEdgeList(graph);      // list of all possible edges
        int[] distance = new int[vertexCount];          // an array with distance[i] denoting the distance of ith vertex from start
        int[] predecessor = new int[vertexCount];       // used to backtrack the path
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[start] = 0;

        // relax all edges |V-1| times
        for(int i = 1; i < vertexCount; ++i){
            edges.forEach(edge -> {
                if(distance[edge.start()] != Integer.MAX_VALUE &&
                        distance[edge.end()] > distance[edge.start()] + edge.weight()){
                    distance[edge.end()] = distance[edge.start()] + edge.weight();
                    // for an end() there could be multiple start()
                    // the predecessor is the one that has the shortest path
                    predecessor[edge.end()] = edge.start();
                }
            });
        }

        // check negative cycle
        // if value changes then we have a negative cycle in the graph
        AtomicBoolean cycle = new AtomicBoolean(false);
        edges.forEach(edge -> {
            if(distance[edge.start()] != Integer.MAX_VALUE &&
                    distance[edge.end()] > distance[edge.start()] + edge.weight()){
                cycle.set(true);
            }
        });

        return processPathIfNoCycle(start, end, pathConsumer, predecessor, cycle);
    }

    /**
     * Queue-based Bellman-Ford algorithm. The only edges that could lead to a change
     * in distance[] are those leaving a vertex whose distance[] value changed in the
     * previous pass. To keep track of such vertices, we use a FIFO queue.
     * shortestPathUsingQueue implements this approach by maintaining two additional
     * data structures:
     * <ol>
     *      <li>a queue of vertices to be relaxed</li>
     *      <li>A vertex-index boolean array inQueue[] that indicates which vertices are on the queue, to avoid duplicates</li>
     * </ol>
     *
     * @param graph        the graph to traverse
     * @param start        start vertex
     * @param end          end vertex
     * @param pathConsumer the action to be performed on each vertex in the path
     * @return true if no cycle detected, false otherwise
     */
    public boolean shortestPathUsingQueue(int[][] graph, int start, int end, Consumer<Integer> pathConsumer){
        int vertexCount = graph.length;

        List<Edge> edges = prepareEdgeList(graph);

        int[] distance = new int[vertexCount];                  // list of all possible edges
        int[] count = new int[vertexCount];                     // number of times count[i] is encountered
        boolean[] inQueue = new boolean[vertexCount];           // avoid duplicates in the queue

        int[] predecessor = new int[vertexCount];               // used to backtrack the path
        Arrays.fill(distance, Integer.MAX_VALUE);


        Queue<Integer> queue = new LinkedList<>();              // holds the vertices that actually participated in the relaxation process
        queue.add(start);
        distance[start] = 0;
        inQueue[start] = true;

        AtomicBoolean cycle = new AtomicBoolean(false);
        try{
            while(!queue.isEmpty()){
                int source = queue.poll();
                inQueue[source] = false;
                edges.forEach(edge -> {
                    if(distance[edge.start()] != Integer.MAX_VALUE &&
                            distance[edge.end()] > distance[edge.start()] + edge.weight()){
                        if(!inQueue[edge.end()]){
                            queue.add(edge.end());
                            inQueue[edge.end()] = true;
                            count[edge.end()]++;

                            if(count[edge.end()] > vertexCount){
                                throw new RuntimeException("oops!! there is a cycle");
                            }
                        }
                        distance[edge.end()] = distance[edge.start()] + edge.weight();
                        // for an end() there could be multiple start()
                        // the predecessor is the one that has the shortest path
                        predecessor[edge.end()] = edge.start();
                    }
                });
            }
        } catch(RuntimeException exception){
            cycle.set(true);
        }

        return processPathIfNoCycle(start, end, pathConsumer, predecessor, cycle);
    }

    private List<Edge> prepareEdgeList(int[][] graph){
        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph[0].length; j++){
                if(graph[i][j] != 0){
                    edges.add(new Edge(i, j, graph[i][j]));
                }
            }
        }
        return edges;
    }

    private boolean processPathIfNoCycle(int start, int end, Consumer<Integer> pathConsumer, int[] predecessor, AtomicBoolean cycle){
        if(cycle.get()){
            return false;
        } else{
            // backtrack the path
            int tracker = end;
            pathConsumer.accept(end);
            while(tracker != start){
                pathConsumer.accept(predecessor[tracker]);
                tracker = predecessor[tracker];
            }
            return true;
        }
    }
}
