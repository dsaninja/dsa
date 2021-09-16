package com.dsaninja.algos.search

import spock.lang.Specification

class DepthFirstSearchSpec extends Specification {

    def dfs = new DepthFirstSearch()

    def "test bfs traversal on the graph"() {
        given: "BFS is available and a new graph is created"
        int[][] twoDim = [[0, 1, 1, 0, 0, 0], [1, 0, 0, 1, 0, 0], [1, 0, 0, 1, 1, 0], [0, 1, 1, 0, 0, 1], [0, 0, 1, 0, 0, 1], [0, 0, 0, 1, 1, 0]]
        def stringJoiner = new StringJoiner(", ")

        when: "a few edges are created between two nodes"
        dfs.traverse(twoDim, 0, element -> stringJoiner.add(element.toString()))

        then: "bfs traversal should be correctly done"
        stringJoiner.toString() == "0, 2, 4, 5, 3, 1"
    }
}
