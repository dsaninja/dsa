package com.dsaninja.algos.searching

import spock.lang.Specification

class BinarySearchSpec extends Specification {
    def binarySearch = new BinarySearch()

    def "test binary search for a key present in the array - right half"() {
        given: "an instance of LinearSearch class"
        and: "an ordered input array to be searched"
        def input = new int[]{0, 1, 3, 4, 7, 8}
        and: "key to be searched"
        def key = 8

        when: "array is searched for the presence of an existing element"
        def index = binarySearch.search(input, key)

        then: "it should return correct index"
        index == 5
    }

    def "test binary search for a key present in the array - left half"() {
        given: "an instance of LinearSearch class"
        and: "an ordered input array to be searched"
        def input = new int[]{0, 1, 3, 4, 7, 8}
        and: "key to be searched"
        def key = 0

        when: "array is searched for the presence of an existing element"
        def index = binarySearch.search(input, key)

        then: "it should return correct index"
        index == 0
    }

    def "test binary search for a key NOT present in the array"() {
        given: "an instance of LinearSearch class"
        and: "an ordered input array to be searched"
        def input = new int[]{0, 1, 3, 4, 7, 8}
        and: "key to be searched"
        def key = 11

        when: "array is searched for the presence of an non-existing element"
        def index = binarySearch.search(input, key)

        then: "it should return correct index"
        index == -1
    }

    def "test binary search - recursive for a key present in the array - right half"() {
        given: "an instance of LinearSearch class"
        and: "an ordered input array to be searched"
        def input = new int[]{0, 1, 3, 4, 7, 8}
        and: "start and end index"
        def start = 0, end = input.length -1
        and: "key to be searched"
        def key = 8

        when: "array is searched for the presence of an existing element"
        def index = binarySearch.search(input, key, start, end)

        then: "it should return correct index"
        index == 5
    }

    def "test binary search - recursive for a key present in the array - left half"() {
        given: "an instance of LinearSearch class"
        and: "an ordered input array to be searched"
        def input = new int[]{0, 1, 3, 4, 7, 8}
        and: "start and end index"
        def start = 0, end = input.length -1
        and: "key to be searched"
        def key = 0

        when: "array is searched for the presence of an existing element"
        def index = binarySearch.search(input, key, start, end)

        then: "it should return correct index"
        index == 0
    }

    def "test binary search - recursive for a key NOT present in the array"() {
        given: "an instance of LinearSearch class"
        and: "an ordered input array to be searched"
        def input = new int[]{0, 1, 3, 4, 7, 8}
        and: "start and end index"
        def start = 0, end = input.length -1
        and: "key to be searched"
        def key = 11

        when: "array is searched for the presence of an non-existing element"
        def index = binarySearch.search(input, key, start, end)

        then: "it should return correct index"
        index == -1
    }
}
