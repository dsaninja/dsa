package com.dsaninja.algos.searching

import spock.lang.Specification

class BreadthFirstSearchSpec extends Specification {

    def bfs = new BreadthFirstSearch()

    def "test bfs traversal on the graph"() {
        given: "BFS is available and a new graph is created"
        int[][] twoDim = [[0, 1, 1, 0, 0, 0], [1, 0, 0, 1, 0, 0], [1, 0, 0, 1, 1, 0], [0, 1, 1, 0, 0, 1], [0, 0, 1, 0, 0, 1], [0, 0, 0, 1, 1, 0]]
        def stringJoiner = new StringJoiner(", ")

        when: "a few edges are created between two nodes"
        bfs.traverse(twoDim, 0, element -> stringJoiner.add(element.toString()))

        then: "bfs traversal should be correctly done"
        stringJoiner.toString() == "0, 1, 2, 3, 4, 5"
    }
}
