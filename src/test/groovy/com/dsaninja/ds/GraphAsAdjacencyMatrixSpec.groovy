package com.dsaninja.ds

import spock.lang.Specification

class GraphAsAdjacencyMatrixSpec extends Specification {

    def "test creating a graph of n vertices"() {
        given: "number of vertices"
        def vertexCount = 5

        when: " a new graph is created"
        def graph = new GraphAsAdjacencyMatrix(vertexCount)

        then: "graph with specified node count should be created"
        graph != null
        graph.getNumberOfVertex() == vertexCount
    }

    def "test creating an edge between two nodes"() {
        given: "number of vertices"
        def vertexCount = 5
        and: " a new graph is created"
        def graph = new GraphAsAdjacencyMatrix(vertexCount)
        and: "start and end vertices"
        def start = 1
        def end = 2

        when: "a new edge is created between two nodes"
        graph.addEdge(start, end)

        then: "the vertices should be connected"
        graph.isConnected(start, end)
        and: "in reverse order as well"
        graph.isConnected(end, start)
    }

    def "test removing an edge between two nodes"() {
        given: "number of vertices"
        def vertexCount = 5
        and: " a new graph is created"
        def graph = new GraphAsAdjacencyMatrix(vertexCount)
        and: "start and end vertices"
        def start = 1
        def end = 2
        and: "a new edge is created between two nodes"
        graph.addEdge(start, end)

        when: "the edge is removed"
        graph.removeEdge(start, end)

        then: "the vertices should be disconnected"
        !graph.isConnected(start, end)
        and: "in reverse order as well"
        !graph.isConnected(end, start)
    }

    def "test adding an edge between invalid start vertex and invalid end vertex"() {
        given: "number of vertices"
        def vertexCount = 5
        and: " a new graph is created"
        def graph = new GraphAsAdjacencyMatrix(vertexCount)

        when: "a new edge is created between two nodes start: 6 and end: 4"
        graph.addEdge(6, 4)

        then: "exception should be thrown"
        thrown(IllegalArgumentException)

        when: "a new edge is created between two nodes start: -1 and end: 4"
        graph.addEdge(-1, 4)

        then: "exception should be thrown"
        thrown(IllegalArgumentException)

        when: "a new edge is created between two nodes start: 1 and end: 6"
        graph.addEdge(1, 6)

        then: "exception should be thrown"
        thrown(IllegalArgumentException)

        when: "a new edge is created between two nodes start: -1 and end: 6"
        graph.addEdge(-1, 6)

        then: "exception should be thrown"
        thrown(IllegalArgumentException)
    }

    def "test adding an edge between a valid start vertex and invalid end vertex"() {
        given: "number of vertices"
        def vertexCount = 5
        and: " a new graph is created"
        def graph = new GraphAsAdjacencyMatrix(vertexCount)
        and: "invalid start or end vertices"
        def start = 1
        def end = 6

        when: "a new edge is created between two nodes"
        graph.addEdge(start, end)

        then: "exception should be thrown"
        thrown(IllegalArgumentException)
    }

    def "test removing an edge between invalid start vertex and valid end vertex"() {
        given: "number of vertices"
        def vertexCount = 5
        and: " a new graph is created"
        def graph = new GraphAsAdjacencyMatrix(vertexCount)
        and: "invalid start vertex"

        when: "an edge is removed between two nodes start: 6 and end: 4"
        graph.removeEdge(6, 4)

        then: "exception should be thrown"
        thrown(IllegalArgumentException)

        when: "an edge is removed between two nodes start: -1 and end: 4"
        graph.removeEdge(-1, 4)

        then: "exception should be thrown"
        thrown(IllegalArgumentException)
    }

    def "test removing an edge between valid start and invalid end vertex"() {
        given: "number of vertices"
        def vertexCount = 5
        and: " a new graph is created"
        def graph = new GraphAsAdjacencyMatrix(vertexCount)
        and: "invalid end vertex"

        when: "an edge is removed between two nodes start: 1 and end: 6"
        graph.removeEdge(1, 6)

        then: "exception should be thrown"
        thrown(IllegalArgumentException)

        when: "an edge is removed between two nodes start: 1 and end: -6"
        graph.removeEdge(1, -6)

        then: "exception should be thrown"
        thrown(IllegalArgumentException)
    }

    def "test checking connection between invalid start and valid end vertices"() {
        given: "number of vertices"
        def vertexCount = 5
        and: " a new graph is created"
        def graph = new GraphAsAdjacencyMatrix(vertexCount)
        and: "invalid start vertex"

        when: "connection is checked for start: 6 and end: 2"
        graph.isConnected(6, 2)

        then: "exception should be thrown"
        thrown(IllegalArgumentException)

        when: "connection is checked for start: -1 and end: 2"
        graph.isConnected(-1, 2)

        then: "exception should be thrown"
        thrown(IllegalArgumentException)
    }

    def "test checking connection between valid start and invalid end vertices"() {
        given: "number of vertices"
        def vertexCount = 5
        and: " a new graph is created"
        def graph = new GraphAsAdjacencyMatrix(vertexCount)
        and: "invalid end vertex"

        when: "connection is checked for start: 1 and end: 6"
        graph.isConnected(1, 6)

        then: "exception should be thrown"
        thrown(IllegalArgumentException)

        when: "connection is checked for start: 1 and end: -6"
        graph.isConnected(1, -6)

        then: "exception should be thrown"
        thrown(IllegalArgumentException)
    }

    def "test degree of a node"() {
        given: "number of vertices"
        def vertexCount = 5
        and: " a new graph is created"
        def graph = new GraphAsAdjacencyMatrix(vertexCount)
        and: "start and end vertices"
        def start = 1

        when: "a few edges are created between two nodes"
        graph.addEdge(start, 2)
        graph.addEdge(start, 3)
        graph.addEdge(start, 4)
        and: "its degree is captured"
        def degree = graph.degree(start)

        then: "the degree should be correctly captured"
        degree == 3
    }

    def "test bfs traversal on the graph"() {
        given: "number of vertices"
        def vertexCount = 6
        and: " a new graph is created"
        def graph = new GraphAsAdjacencyMatrix(vertexCount)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(1, 3)
        graph.addEdge(2, 3)
        graph.addEdge(2, 4)
        graph.addEdge(3, 5)
        def stringJoiner = new StringJoiner(", ")

        when: "a few edges are created between two nodes"
        graph.bfs(element -> stringJoiner.add(element.toString()))

        then: "bfs traversal should be correctly done"
        stringJoiner.toString() == "0, 1, 2, 3, 4, 5"
    }

    def "test bfs traversal on empty tree"() {
        given: "number of vertices"
        def vertexCount = 0
        and: " a new graph is created"
        def graph = new GraphAsAdjacencyMatrix(vertexCount)
        def stringJoiner = new StringJoiner(", ")

        when: "a few edges are created between two nodes"
        graph.bfs(element -> stringJoiner.add(element.toString()))

        then: "bfs traversal should be correctly done"
        stringJoiner.toString() == ""
    }
}
