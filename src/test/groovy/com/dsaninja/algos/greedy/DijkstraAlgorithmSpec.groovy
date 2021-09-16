package com.dsaninja.algos.greedy

import spock.lang.Specification

class DijkstraAlgorithmSpec extends Specification {
    def dijkstra = new DijkstraAlgorithm()

    def "shortestPath input1"() {
        given: "graph is provided"
        int[][] graph = [[0, 2, 4, 0, 0, 0], [0, 0, 1, 7, 0, 0], [0, 0, 0, 0, 3, 0], [0, 0, 0, 0, 0, 1], [0, 0, 0, 2, 0, 5], [0, 0, 0, 0, 0, 0]]
        and: "start vertex is provided"
        def start = 0
        and: "destination vertex is provided"
        def destination = 5
        def action = new StringJoiner("->")

        when: "shortestPath is called"
        int distance = dijkstra.traverse(graph, start, destination, value -> action.add(value.toString()))

        then: "distance should be 9"
        distance == 9
        and: "path should be shortest one"
        action.toString() == "5->3->4->2->1->0"
    }

    def "shortestPath input2"() {
        given: "graph is provided"
        int[][] graph = [[0, 50, 45, 10, 0, 0], [0, 0, 10, 15, 0, 0], [0, 0, 0, 0, 30, 0], [10, 0, 0, 0, 15, 0], [0, 20, 35, 0, 0, 0], [0, 0, 0, 0, 3, 0]]
        and: "start vertex is provided"
        def start = 0
        and: "destination vertex is provided"
        def destination = 1
        def action = new StringJoiner("->")

        when: "shortestPath is called"
        int distance = dijkstra.traverse(graph, start, destination, value -> action.add(value.toString()))

        then: "distance should be 9"
        distance == 45
        and: "path should be shortest one"
        action.toString() == "1->4->3->0"
    }
}
