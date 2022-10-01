package com.dsaninja.algos.searching

import spock.lang.Specification

class DepthFirstSearchSpec extends Specification {

    def dfs = new DepthFirstSearch()

    def "test bfs traversal on the graph"() {
        given: "BFS is available and a new graph is created"
        int[][] twoDim = [[0, 1, 1, 0, 0, 0], [1, 0, 0, 1, 0, 0], [1, 0, 0, 1, 1, 0], [0, 1, 1, 0, 0, 1], [0, 0, 1, 0, 0, 1], [0, 0, 0, 1, 1, 0]]
        def stringJoiner = new StringJoiner(", ")

        when: "a few edges are created between two nodes"
        dfs.traverse(twoDim, 0, element -> stringJoiner.add(element.toString()))

        then: "dfs traversal should be correctly done"
        stringJoiner.toString() == "0, 2, 4, 5, 3, 1"
    }

    def "test bfs traversal on the graph v2"() {
        given: "BFS is available and a new graph is created"
        int[][] twoDim = [[0, 1, 1, 0], [0, 0, 1, 0], [1, 0, 0, 1], [0, 0, 0, 1]]
        def stringJoiner = new StringJoiner(", ")

        when: "a few edges are created between two nodes"
        dfs.traverse(twoDim, 1, element -> stringJoiner.add(element.toString()))

        then: "dfs traversal should be correctly done"
        stringJoiner.toString() == "1, 2, 3, 0"
    }

    def "test bfs traversal on the graph v3"() {
        given: "BFS is available and a new graph is created"
        int[][] twoDim = [[0, 1, 1, 0], [0, 0, 1, 1], [1, 0, 0, 0], [0, 0, 0, 1]]
        def stringJoiner = new StringJoiner(", ")

        when: "a few edges are created between two nodes"
        dfs.traverse(twoDim, 2, element -> stringJoiner.add(element.toString()))

        then: "dfs traversal should be correctly done"
        stringJoiner.toString() == "2, 0, 1, 3"
    }

    def "test bfs traversal on the graph v4"() {
        given: "BFS is available and a new graph is created"
        int[][] twoDim = [[0, 1, 0, 1, 0], [0, 0, 1, 0, 1], [0, 0, 0, 0, 0], [0, 0, 0, 0, 1], [0, 0, 0, 0, 0]]
        def stringJoiner = new StringJoiner(", ")

        when: "a few edges are created between two nodes"
        dfs.traverse(twoDim, 0, element -> stringJoiner.add(element.toString()))

        then: "dfs traversal should be correctly done"
        stringJoiner.toString() == "0, 3, 4, 1, 2"
        // "1, 4 ,5, 2, 3"
    }
}
