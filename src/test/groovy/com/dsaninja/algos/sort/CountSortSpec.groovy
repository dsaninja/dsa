package com.dsaninja.algos.sort

import spock.lang.Specification

class CountSortSpec extends Specification {

    def countSort = new CountSort()

    def "test sorting an un-sorted input array with duplicate values"() {
        given: "an un-sorted input array"
        def input = new int[]{1,5,3,1,2,4}

        when: "count sort is triggered"
        countSort.sort(input)

        then: "array should be sorted in ascending order"
        input == new int[]{1,1,2,3,4,5}
    }

    def "test sorting an un-sorted input array with NO duplicate values"() {
        given: "an un-sorted input array"
        def input = new int[]{1,5,3,0,2,4}

        when: "count sort is triggered"
        countSort.sort(input)

        then: "array should be sorted in ascending order"
        input == new int[]{0,1,2,3,4,5}
    }

    def "test sorting a sorted input array"() {
        given: "an un-sorted input array"
        def input = new int[]{0,1,2,3,4,5}

        when: "count sort is triggered"
        countSort.sort(input)

        then: "array should be sorted in ascending order"
        input == new int[]{0,1,2,3,4,5}
    }

    def "test sorting a single element input array"() {
        given: "an un-sorted input array"
        def input = new int[]{0}

        when: "count sort is triggered"
        countSort.sort(input)

        then: "array should be unchanged"
        input == new int[]{0}
    }

    def "test sorting an empty input array"() {
        given: "an un-sorted input array"
        def input = new int[]{}

        when: "count sort is triggered"
        countSort.sort(input)

        then: "array should remain unchanged"
        input == new int[]{}
    }
}
