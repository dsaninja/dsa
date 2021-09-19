package com.dsaninja.algos.dp


import spock.lang.Specification

class BellmanFordSPSpec extends Specification {

    def bellmanFord = new BellmanFordSP()

    def "detect shortestPath with no -ve cycle"() {
        given: "graph with no cycles is provided"
        int[][] graph = [[0, -1, 4, 0, 0],
                         [0, 0, 3, 2, 2],
                         [0, 0, 0, 0, 0],
                         [0, 1, 5, 0, 0],
                         [0, 0, 0, -3, 0]]
        and: "start vertex is provided"
        def start = 0
        and: "destination vertex is provided"
        def destination = 3
        def action = new StringJoiner("->")

        when: "shortestPath is called"
        def noCycle = bellmanFord.shortestPath(graph, start, destination, value -> action.add(value.toString()))

        then: "noCycle should have been detected"
        noCycle
        and: "path should be shortest one"
        action.toString() == "3->4->1->0"
    }

    def "detect shortestPath with -ve cycle"() {
        given: "graph -ve weight cycle is provided"
        int[][] graph = [[0, 4, 0, 5],
                         [0, 0, 0, 5],
                         [0, -10, 0, 0],
                         [0, 0, 3, 0],
                         [0, 0, 0, -3]]
        and: "start vertex is provided"
        def start = 0
        and: "destination vertex is provided"
        def destination = 3
        def action = new StringJoiner("->")

        when: "shortestPath is called"
        def noCycle = bellmanFord.shortestPath(graph, start, destination, value -> action.add(value.toString()))

        then: "noCycle should have been set to false"
        !noCycle
    }

    def "detect shortestPath with no -ve cycle using queue"() {
        given: "graph with no cycles is provided"
        int[][] graph = [[0, -1, 4, 0, 0],
                         [0, 0, 3, 2, 2],
                         [0, 0, 0, 0, 0],
                         [0, 1, 5, 0, 0],
                         [0, 0, 0, -3, 0]]
        and: "start vertex is provided"
        def start = 0
        and: "destination vertex is provided"
        def destination = 3
        def action = new StringJoiner("->")

        when: "shortestPath is called"
        def noCycle = bellmanFord.shortestPathUsingQueue(graph, start, destination, value -> action.add(value.toString()))

        then: "noCycle should have been detected"
        noCycle
        and: "path should be shortest one"
        action.toString() == "3->4->1->0"
    }

    def "detect shortestPath with -ve cycle using queue"() {
        given: "graph with -ve weight cycle is provided"
        int[][] graph = [[0, 4, 0, 5],
                         [0, 0, 0, 5],
                         [0, -10, 0, 0],
                         [0, 0, 3, 0],
                         [0, 0, 0, -3]]
        and: "start vertex is provided"
        def start = 0
        and: "destination vertex is provided"
        def destination = 3
        def action = new StringJoiner("->")

        when: "shortestPath is called"
        def noCycle = bellmanFord.shortestPathUsingQueue(graph, start, destination, value -> action.add(value.toString()))

        then: "noCycle should have been set to false"
        !noCycle
    }
}
