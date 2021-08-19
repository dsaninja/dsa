package com.dsaninja.algos.sort

import spock.lang.Specification

class InsertionSortSpec extends Specification {

    def insertionSort = new InsertionSort()

    def "test sorting an un-sorted input array"() {
        given: "an un-sorted input array"
        def input = new int[]{1,5,3,9,2,4}

        when: "insertion sort is triggered"
        insertionSort.sort(input)

        then: "array should be sorted in ascending order"
        input == new int[]{1,2,3,4,5,9}
    }

    def "test sorting an sorted input array"() {
        given: "an un-sorted input array"
        def input = new int[]{1,2,3,4,5,9}

        when: "insertion sort is triggered"
        insertionSort.sort(input)

        then: "array should be sorted in ascending order"
        input == new int[]{1,2,3,4,5,9}
    }

    def "test sorting a reversed input array"() {
        given: "an un-sorted input array"
        def input = new int[]{9,8,7,6,5,4,3,2,1}

        when: "insertion sort is triggered"
        insertionSort.sort(input)

        then: "array should remain unchanged"
        input == new int[]{1,2,3,4,5,6,7,8,9}
    }

    def "test sorting an empty input array"() {
        given: "an un-sorted input array"
        def input = new int[]{}

        when: "insertion sort is triggered"
        insertionSort.sort(input)

        then: "array should remain unchanged"
        input == new int[]{}
    }
}
