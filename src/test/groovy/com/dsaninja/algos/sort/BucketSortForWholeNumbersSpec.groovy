package com.dsaninja.algos.sort

import spock.lang.Specification

class BucketSortForWholeNumbersSpec extends Specification{
    def bucketSort = new BucketSortForWholeNumbers()

    def "test sorting a single element input array"() {
        given: "an single element input array"
        def input = new int[]{0}

        when: "bucket sort is triggered"
        bucketSort.sort(input)

        then: "array should be unchanged"
        input == new int[]{0}
    }

    def "test sorting two element unsorted input array"() {
        given: "an un-sorted input array"
        def input = new int[]{3,2}

        when: "bucket sort is triggered"
        bucketSort.sort(input)

        then: "array should be sorted"
        input == new int[]{2,3}
    }

    def "test sorting two element sorted input array"() {
        given: "an sorted input array"
        def input = new int[]{2,3}

        when: "bucket sort is triggered"
        bucketSort.sort(input)

        then: "array should be sorted"
        input == new int[]{2,3}
    }

    def "test sorting two element input array with duplicates"() {
        given: "an sorted input array"
        def input = new int[]{2,2}

        when: "bucket sort is triggered"
        bucketSort.sort(input)

        then: "array should be sorted"
        input == new int[]{2,2}
    }

    def "test sorting an un-sorted input array"() {
        given: "an un-sorted input array"
        def input = new int[]{1,5,3,9,2,4}

        when: "bucket sort is triggered"
        bucketSort.sort(input)

        then: "array should be sorted in ascending order"
        input == new int[]{1,2,3,4,5,9}
    }

    def "test sorting a sorted input array"() {
        given: "an un-sorted input array"
        def input = new int[]{1,2,3,4,5,9}

        when: "bucket sort is triggered"
        bucketSort.sort(input)

        then: "array should be sorted in ascending order"
        input == new int[]{1,2,3,4,5,9}
    }

    def "test sorting an empty input array"() {
        given: "an un-sorted input array"
        def input = new int[]{}

        when: "bucket sort is triggered"
        bucketSort.sort(input)

        then: "array should remain unchanged"
        input == new int[]{}
    }

    def "test sorting an un-sorted input array with maxValue > n"() {
        given: "an un-sorted input array"
        def input = new int[]{634, 717, 438, 90, 5}

        when: "bucket sort is triggered"
        bucketSort.sort(input)

        then: "array should be sorted"
        input == new int[]{5, 90, 438, 634, 717}
    }

    def "test sorting an sorted input array with maxValue > n"() {
        given: "an un-sorted input array"
        def input = new int[]{5, 90, 438, 634, 717}

        when: "bucket sort is triggered"
        bucketSort.sort(input)

        then: "array should be un-changed"
        input == new int[]{5, 90, 438, 634, 717}
    }

    def "test sorting an input array with maxValue > n having duplicates"() {
        given: "an un-sorted input array"
        def input = new int[]{523, 90, 826, 169, 513, 80, 523}

        when: "bucket sort is triggered"
        bucketSort.sort(input)

        then: "array should be sorted"
        input == new int[]{80, 90, 169, 513, 523, 523, 826}
    }
}
