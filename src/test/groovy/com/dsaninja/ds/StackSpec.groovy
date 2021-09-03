package com.dsaninja.ds

import spock.lang.Specification

class StackSpec extends Specification {

    def "check the size of empty stack"(){
        given: "a stack is created of size 5"
        def stack = new Stack(5)

        when: "its size is checked"
        def size = stack.size()

        then: "it should return 0"
        size == 0
    }

    def "check the size of non-empty stack"(){
        given: "a stack is created of size 5"
        def stack = new Stack(5)
        and: "some items are pushed to it"
        stack.push("1")
        stack.push("2")
        stack.push("3")

        when: "its size is checked"
        def size = stack.size()

        then: "it should return correct size"
        size == 3
    }

    def "test pushing elements to stack beyond its capacity"(){
        given: "a stack is created of size 4"
        def stack = new Stack(4)
        and: "some items are pushed to it"
        stack.push("1")
        stack.push("2")
        stack.push("3")
        stack.push("4")


        when: "items beyond its capacity are pushed"
        stack.push("5")

        then: "an exception is thrown"
        thrown(RuntimeException)
    }

    def "test peeking the top element"(){
        given: "a stack is created of size 5"
        def stack = new Stack(5)
        and: "some items are pushed to it"
        stack.push("1")
        stack.push("2")
        stack.push("3")
        stack.push("4")


        when: "peek the top element"
        def peek = stack.peek()

        then: "it should show the last inserted element"
        peek.isPresent()
        and:
        peek.get() == "4"
    }

    def "test peeking the top element from empty stack"(){
        given: "a stack is created of size 5"
        def stack = new Stack(5)

        when: "pop the top element"
        def peek = stack.peek()

        then: "the optional should not be present"
        peek.isPresent() == false
    }

    def "test popping the top element"(){
        given: "a stack is created of size 5"
        def stack = new Stack(5)
        and: "some items are pushed to it"
        stack.push("1")
        stack.push("2")
        stack.push("3")
        stack.push("4")


        when: "pop the top element"
        def popped = stack.pop()

        then: "it should be the last inserted element"
        popped == "4"
    }

    def "test popping the top element from empty stack"(){
        given: "a stack is created of size 5"
        def stack = new Stack(5)

        when: "pop the top element"
        stack.pop()

        then: "an exception should be thrown"
        thrown(RuntimeException)
    }
}
