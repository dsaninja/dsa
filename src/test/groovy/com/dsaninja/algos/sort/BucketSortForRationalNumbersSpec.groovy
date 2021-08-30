package com.dsaninja.algos.sort

import spock.lang.Specification

class BucketSortForRationalNumbersSpec extends Specification {
    def bucketSort = new BucketSortForRationalNumbers()

    def "test sorting empty array"(){
        given: "BucketSort class is instantiated"
        and: "an empty array"
        def emptyArray = new double[]{}

        when: "bucket sort is triggered"
        bucketSort.sort(emptyArray)

        then: "array length should be unchanged"
        emptyArray.length == 0
    }

    def "test sorting single element array"(){
        given: "BucketSort class in instantiated"
        and: "an single element array"
        def ip = new double[]{0.1}

        when: "bucket sort is triggered"
        bucketSort.sort(ip)

        then: "array should be unchanged"
        new double[]{0.1} == ip
    }

    def "test sorting two element array"(){
        given: "BucketSort class in instantiated"
        and: "two element array"
        def ip = new double[]{0.21,0.11}

        when: "bucket sort is triggered"
        bucketSort.sort(ip)

        then: "array should be sorted"
        new double[]{0.11,0.21} == ip
    }

    def "test sorting sorted array"(){
        given: "BucketSort class in instantiated"
        and: "sorted elements array"
        def ip = new double[]{0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9}

        when: "bucket sort is triggered"
        bucketSort.sort(ip)

        then: "array should be unchanged"
        new double[]{0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9} == ip
    }

    def "test sorting un-sorted array"(){
        given: "BucketSort class in instantiated"
        and: "un-sorted elements array"
        def ip = new double[]{0.21,0.12,0.43,0.14,0.55,0.66,0.17,0.28,0.19}

        when: "bucket sort is triggered"
        bucketSort.sort(ip)

        then: "array should be sorted"
        new double[]{0.12,0.14,0.17,0.19,0.21,0.28,0.43,0.55,0.66} == ip
    }

    def "test sorting un-sorted duplicate elements array"(){
        given: "BucketSort class in instantiated"
        and: "un-sorted duplicate elements array"
        def ip = new double[]{0.21,0.12,0.43,0.32,0.12,0.55,0.17,0.24,0.39}

        when: "bucket sort is triggered"
        bucketSort.sort(ip)

        then: "array should be sorted"
        new double[]{0.12,0.12,0.17,0.21,0.24,0.32,0.39,0.43,0.55} == ip
    }
}
