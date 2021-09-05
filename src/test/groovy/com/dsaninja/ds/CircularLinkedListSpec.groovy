package com.dsaninja.ds

import spock.lang.Specification

class CircularLinkedListSpec extends Specification {
    def "test the size of a blank list"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        when: "its size is checked"
        def size = circularLinkedList.size()

        then: "0 should be returned"
        size == 0
    }

    def "test the size of the list after adding a single element"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        when: "an element is inserted"
        circularLinkedList.add("hello")
        and: "its size is checked"
        def size = circularLinkedList.size()

        then: "1 should be returned"
        size == 1
    }

    def "test inserting a null value"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        when: "a null element is inserted"
        circularLinkedList.add(null)

        then: "NPE should have been thrown"
        thrown(NullPointerException)
        and: "size should still be 0"
        circularLinkedList.size() == 0
    }

    def "test toString method"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        when: "an element is inserted"
        circularLinkedList.add("1")
        and: "another element is added"
        circularLinkedList.add("2")
        and: "toString is called"
        def string = circularLinkedList.toString()

        then: "comma seperated result should be returned"
        string == "1, 2"
    }

    def "test element insertion at a given index"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        when: "an element is inserted"
        circularLinkedList.add("1")
        and: "another element is added"
        circularLinkedList.add("2")
        and: "an element is inserted at index 1"
        circularLinkedList.add(1, 5)

        then: "element should be inserted at correct location"
        circularLinkedList.toString() == "1, 5, 2"
    }

    def "test element insertion at a given index -V2"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        when: "an element is inserted"
        circularLinkedList.add("1")
        and: "another element is added"
        circularLinkedList.add("2")
        and: "an element is inserted at index 1"
        circularLinkedList.add(2, 5)

        then: "element should be inserted at correct location"
        circularLinkedList.toString() == "1, 2, 5"
    }

    def "test element insertion at a invalid index"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        when: "an element is inserted"
        circularLinkedList.add("1")
        and: "another element is added"
        circularLinkedList.add("2")
        and: "an element is inserted at index 1"
        circularLinkedList.add(3, 5)

        then: "exception should be thrown"
        thrown(IllegalArgumentException)
    }

    def "test searching an element when the element is present"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        when: "an element is inserted"
        circularLinkedList.add("1")
        and: "another element is added"
        circularLinkedList.add("2")
        and: "an element is inserted at index 1"
        circularLinkedList.add(1, 5)
        and: "list is searched for value 5"
        def search = circularLinkedList.search(5)

        then: "correct index should be returned"
        search == 1
    }

    def "test searching an element when the element is NOT present"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        when: "an element is inserted"
        circularLinkedList.add("1")
        and: "another element is added"
        circularLinkedList.add("2")
        and: "an element is inserted at index 1"
        circularLinkedList.add(1, 5)
        and: "list is searched for value 15"
        def search = circularLinkedList.search(15)

        then: "correct index should be returned"
        search == -1
    }

    def "test searching an element in blank list"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        when: "list is searched for value 15"
        def search = circularLinkedList.search(15)

        then: "correct index should be returned"
        search == -1
    }

    def "test removing head node"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        and: "elements are inserted"
        circularLinkedList.add("1")
        circularLinkedList.add("2")
        circularLinkedList.add("3")
        circularLinkedList.add("4")
        circularLinkedList.add("5")


        when: "first element is deleted"
        circularLinkedList.remove("1")

        then: "head node should be removed"
        circularLinkedList.toString() == "2, 3, 4, 5"
        and: "size should be decreased"
        circularLinkedList.size() == 4
    }

    def "test removing last node"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        and: "elements are inserted"
        circularLinkedList.add("1")
        circularLinkedList.add("2")
        circularLinkedList.add("3")
        circularLinkedList.add("4")
        circularLinkedList.add("5")


        when: "last element is deleted"
        circularLinkedList.remove("5")

        then: "head node should be removed"
        circularLinkedList.toString() == "1, 2, 3, 4"
        and: "size should be decreased"
        circularLinkedList.size() == 4
    }

    def "test removing ith node"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        and: "elements are inserted"
        circularLinkedList.add("1")
        circularLinkedList.add("2")
        circularLinkedList.add("3")
        circularLinkedList.add("4")
        circularLinkedList.add("5")


        when: "last element is deleted"
        circularLinkedList.remove("3")

        then: "head node should be removed"
        circularLinkedList.toString() == "1, 2, 4, 5"
        and: "size should be decreased"
        circularLinkedList.size() == 4
    }

    def "test removing unavailable node"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        and: "elements are inserted"
        circularLinkedList.add("1")
        circularLinkedList.add("2")
        circularLinkedList.add("3")
        circularLinkedList.add("4")
        circularLinkedList.add("5")


        when: "last element is deleted"
        circularLinkedList.remove("13")

        then: "head node should be removed"
        circularLinkedList.toString() == "1, 2, 3, 4, 5"
        and: "size should be decreased"
        circularLinkedList.size() == 5
    }

    def "test removing head node - using index"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        and: "elements are inserted"
        circularLinkedList.add("1")
        circularLinkedList.add("2")
        circularLinkedList.add("3")
        circularLinkedList.add("4")
        circularLinkedList.add("5")


        when: "first element is deleted"
        circularLinkedList.remove(0)

        then: "head node should be removed"
        circularLinkedList.toString() == "2, 3, 4, 5"
        and: "size should be decreased"
        circularLinkedList.size() == 4
    }

    def "test removing last node - using index"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        and: "elements are inserted"
        circularLinkedList.add("1")
        circularLinkedList.add("2")
        circularLinkedList.add("3")
        circularLinkedList.add("4")
        circularLinkedList.add("5")


        when: "last element is deleted"
        circularLinkedList.remove(4)

        then: "head node should be removed"
        circularLinkedList.toString() == "1, 2, 3, 4"
        and: "size should be decreased"
        circularLinkedList.size() == 4
    }

    def "test removing ith node - using index"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        and: "elements are inserted"
        circularLinkedList.add("1")
        circularLinkedList.add("2")
        circularLinkedList.add("3")
        circularLinkedList.add("4")
        circularLinkedList.add("5")


        when: "last element is deleted"
        circularLinkedList.remove(2)

        then: "head node should be removed"
        circularLinkedList.toString() == "1, 2, 4, 5"
        and: "size should be decreased"
        circularLinkedList.size() == 4
    }

    def "test removing invalid index"() {
        given: "a new circular list is created"
        def circularLinkedList = new CircularLinkedList()

        and: "elements are inserted"
        circularLinkedList.add("1")
        circularLinkedList.add("2")
        circularLinkedList.add("3")
        circularLinkedList.add("4")
        circularLinkedList.add("5")


        when: "last element is deleted"
        circularLinkedList.remove(8)

        then: "exception should be thrown"
        thrown(IllegalArgumentException)
    }
}
