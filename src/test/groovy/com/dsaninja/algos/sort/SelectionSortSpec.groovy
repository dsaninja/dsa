package com.dsaninja.algos.sort

import spock.lang.Specification

class SelectionSortSpec extends Specification {

    def selectionSort = new SelectionSort()

    def "test sorting an un-sorted input array"() {
        given: "an un-sorted input array"
        def input = new int[]{1,5,3,9,2,4}

        when: "selection sort is triggered"
        selectionSort.sort(input)

        then: "array should be sorted in ascending order"
        input == new int[]{1,2,3,4,5,9}
    }

    def "test sorting a sorted input array"() {
        given: "an un-sorted input array"
        def input = new int[]{1,2,3,4,5,9}

        when: "selection sort is triggered"
        selectionSort.sort(input)

        then: "array should be sorted in ascending order"
        input == new int[]{1,2,3,4,5,9}
    }

    def "test sorting an empty input array"() {
        given: "an un-sorted input array"
        def input = new int[]{}

        when: "selection sort is triggered"
        selectionSort.sort(input)

        then: "array should remain unchanged"
        input == new int[]{}
    }
}
