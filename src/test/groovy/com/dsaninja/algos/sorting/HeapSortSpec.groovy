package com.dsaninja.algos.sorting

import spock.lang.Specification

class HeapSortSpec extends Specification {

    def heapSort = new HeapSort()

    def "test sorting a single element input array"() {
        given: "an single element input array"
        def input = new int[]{0}

        when: "heap sort is triggered"
        heapSort.sort(input)

        then: "array should be unchanged"
        input == new int[]{0}
    }

    def "test sorting two element unsorted input array"() {
        given: "an un-sorted input array"
        def input = new int[]{3,2}

        when: "heap sort is triggered"
        heapSort.sort(input)

        then: "array should be sorted"
        input == new int[]{2,3}
    }

    def "test sorting two element sorted input array"() {
        given: "an sorted input array"
        def input = new int[]{2,3}

        when: "heap sort is triggered"
        heapSort.sort(input)

        then: "array should be sorted"
        input == new int[]{2,3}
    }

    def "test sorting two element input array with duplicates"() {
        given: "an sorted input array"
        def input = new int[]{2,2}

        when: "heap sort is triggered"
        heapSort.sort(input)

        then: "array should be sorted"
        input == new int[]{2,2}
    }

    def "test sorting an un-sorted input array"() {
        given: "an un-sorted input array"
        def input = new int[]{1,5,3,9,2,4}

        when: "heap sort is triggered"
        heapSort.sort(input)

        then: "array should be sorted in ascending order"
        input == new int[]{1,2,3,4,5,9}
    }

    def "test sorting a sorted input array"() {
        given: "an un-sorted input array"
        def input = new int[]{1,2,3,4,5,9}

        when: "heap sort is triggered"
        heapSort.sort(input)

        then: "array should be sorted in ascending order"
        input == new int[]{1,2,3,4,5,9}
    }

    def "test sorting an empty input array"() {
        given: "an un-sorted input array"
        def input = new int[]{}

        when: "heap sort is triggered"
        heapSort.sort(input)

        then: "array should remain unchanged"
        input == new int[]{}
    }
}
