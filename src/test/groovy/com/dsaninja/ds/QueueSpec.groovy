package com.dsaninja.ds

import spock.lang.Specification

class QueueSpec extends Specification {

    def "test the size of a blank queue"() {
        given: "a new queue is created"
        def queue = new Queue()

        when: "its size is checked"
        def size = queue.size()

        then: "0 should be returned"
        size == 0
    }

    def "test the size of the queue after adding a single element"() {
        given: "a new queue is created"
        def queue = new Queue()

        when: "an element is inserted"
        queue.add("hello")
        and: "its size is checked"
        def size = queue.size()

        then: "1 should be returned"
        size == 1
    }

    def "test inserting a null value"() {
        given: "a new queue is created"
        def queue = new Queue()

        when: "a null element is inserted"
        queue.add(null)

        then: "NPE should have been thrown"
        thrown(NullPointerException)
        and: "size should still be 0"
        queue.size() == 0
    }

    def "test toString method"() {
        given: "a new queue is created"
        def queue = new Queue()

        when: "an element is inserted"
        queue.add("1")
        and: "another element is added"
        queue.add("2")
        and: "toString is called"
        def string = queue.toString()

        then: "comma seperated result should be returned"
        string == "1, 2"
    }

    def "test removing an element"() {
        given: "a new queue is created"
        def queue = new Queue()

        and: "elements are inserted"
        queue.add("1")
        queue.add("2")
        queue.add("3")
        queue.add("4")
        queue.add("5")


        when: "first element is deleted"
        def remove = queue.remove()

        then: "head node should be removed"
        remove == "1"
        and:
        queue.toString() == "2, 3, 4, 5"
        and: "size should be decreased"
        queue.size() == 4
    }


    def "test removing all the elements"() {
        given: "a new queue is created"
        def queue = new Queue()

        and: "elements are inserted"
        queue.add("1")
        queue.add("2")
        queue.add("3")
        queue.add("4")
        queue.add("5")


        when: "all elements are deleted"
        def one = queue.remove()
        def two = queue.remove()
        def three = queue.remove()
        def four = queue.remove()
        def five = queue.remove()

        then: "elements should be correctly removed in FIFO order"
        one == "1"
        and:
        two == "2"
        and:
        three == "3"
        and:
        four == "4"
        and:
        five == "5"
        and: "size should be decreased"
        queue.size() == 0
    }

    def "test removing unavailable node"() {
        given: "a new queue is created"
        def queue = new Queue()

        when: "last element is deleted"
        queue.remove()

        then: "exception is thrown"
        thrown(RuntimeException)
    }

    def "test peeking an element in empty queue"() {
        given: "a new queue is created"
        def queue = new Queue()

        when: "last element is deleted"
        def result = queue.peek()

        then: "result is null"
        result == null
    }

    def "test peeking an element"() {
        given: "a new queue is created"
        def queue = new Queue()

        and: "elements are inserted"
        queue.add("1")
        queue.add("2")
        queue.add("3")
        queue.add("4")
        queue.add("5")

        when: "queue is peeked"
        def peek = queue.peek()

        then: "front element is returned"
        peek == "1"
        and: "size is same"
        queue.size() == 5

    }
}