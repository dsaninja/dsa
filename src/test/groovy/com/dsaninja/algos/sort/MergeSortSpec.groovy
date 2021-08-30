package com.dsaninja.algos.sort

import spock.lang.Specification

class MergeSortSpec extends Specification {

    def mergeSort = new MergeSort()

    def "test sorting a single element"() {
        given: "an un-sorted input array"
        def input = new int[]{1}

        when: "merge sort is triggered"
        mergeSort.sort(input)

        then: "array should be sorted in ascending order"
        input == new int[]{1}
    }

    def "test sorting two unsorted elements"() {
        given: "an un-sorted input array"
        def input = new int[]{2, 1}

        when: "merge sort is triggered"
        mergeSort.sort(input)

        then: "array should be sorted in ascending order"
        input == new int[]{1, 2}
    }

    def "test sorting two sorted elements"() {
        given: "an un-sorted input array"
        def input = new int[]{1, 2}

        when: "merge sort is triggered"
        mergeSort.sort(input)

        then: "array should be unchanged"
        input == new int[]{1, 2}
    }

    def "test sorting two duplicate elements"(){
        given: "an array of two duplicate elements"
        def input = new int[]{2,2}

        when: "merge sort is triggered"
        mergeSort.sort(input)

        then: "array should be unchanged"
        input == new int[]{2,2}
    }

    def "test sorting an un-sorted input array"() {
        given: "an un-sorted input array"
        def input = new int[]{1, 5, 3, 9, 2, 4}

        when: "merge sort is triggered"
        mergeSort.sort(input)

        then: "array should be sorted in ascending order"
        input == new int[]{1, 2, 3, 4, 5, 9}
    }

    def "test sorting a sorted input array"() {
        given: "an un-sorted input array"
        def input = new int[]{1, 2, 3, 4, 5, 9}

        when: "merge sort is triggered"
        mergeSort.sort(input)

        then: "array should be sorted in ascending order"
        input == new int[]{1, 2, 3, 4, 5, 9}
    }

    def "test sorting an empty input array"() {
        given: "an un-sorted input array"
        def input = new int[]{}

        when: "merge sort is triggered"
        mergeSort.sort(input)

        then: "array should remain unchanged"
        input == new int[]{}
    }
}
