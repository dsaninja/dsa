package com.dsaninja.algos.search

import spock.lang.Specification

class LinearSearchSpec extends Specification {
    def linearSearch = new LinearSearch()

    def "test linear search for a key present in the array"(){
        given: "an instance of LinearSearch class"
        and: "an un-ordered input array to be searched"
        def input = new int[]{1,3,8,4,7,0}
        and: "key to be searched"
        def key = 8

        when: "array is searched for the presence of an existing element"
        def index = linearSearch.linearSearch(input, key)

        then: "it should return correct index"
        index == 2
    }

    def "test linear search for a key NOT present in the array"(){
        given: "an instance of LinearSearch class"
        and: "an un-ordered input array to be searched"
        def input = new int[]{1,3,8,4,7,0}
        and: "key to be searched"
        def key = 11

        when: "array is searched for the presence of an non-existing element"
        def index = linearSearch.linearSearch(input, key)

        then: "it should return correct index"
        index == -1
    }
}
