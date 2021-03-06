package com.dsaninja.ds

import spock.lang.Specification
import spock.lang.Unroll

class BinarySearchTreeSpec extends Specification {

    def binarySearchTree = new BinarySearchTree()

    def "creating a blank tree and checking its size"() {
        given: "BST is created"

        when: "its size is checked"
        def size = binarySearchTree.getSize()

        then: "0 should be the size"
        size == 0
    }

    def "test inserting a n null value"() {
        given: "BST is created"

        when: "its size is checked"
        binarySearchTree.insert(null)

        then: "exception is thrown"
        thrown(NullPointerException)
    }

    def "creating tree with some elements and checking its size"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("1")
        binarySearchTree.insert("2")
        binarySearchTree.insert("3")

        when: "its size is checked"
        def size = binarySearchTree.getSize()

        then: "0 should be the size"
        size == 3
    }

    def "test toString representation in following in-order traversal"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("1")
        binarySearchTree.insert("2")
        binarySearchTree.insert("3")

        when: "toString is invoked"
        def result = binarySearchTree.toString()

        then: "correct order should be returned"
        result == "1, 2, 3"
        and: "size should be 3"
        binarySearchTree.size == 3
    }

    def "test in-order traversal"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("2")
        binarySearchTree.insert("1")
        binarySearchTree.insert("3")
        and: "a string joiner is created"
        def stringJoiner = new StringJoiner(", ");

        when: "toString is invoked"
        binarySearchTree.inOrder(element -> stringJoiner.add(element.toString()))

        then: "correct order should be returned"
        stringJoiner.toString() == "1, 2, 3"
        and: "size should be 3"
        binarySearchTree.size == 3
    }

    def "test pre-order traversal"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("2")
        binarySearchTree.insert("1")
        binarySearchTree.insert("3")
        and: "a string joiner is created"
        def stringJoiner = new StringJoiner(", ");

        when: "toString is invoked"
        binarySearchTree.preOrder(element -> stringJoiner.add(element.toString()))

        then: "correct order should be returned"
        stringJoiner.toString() == "2, 1, 3"
        and: "size should be 3"
        binarySearchTree.size == 3
    }

    def "test post-order traversal"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("15")
        binarySearchTree.insert("10")
        binarySearchTree.insert("25")
        binarySearchTree.insert("20")
        and: "a string joiner is created"
        def stringJoiner = new StringJoiner(", ");

        when: "postOrder is invoked"
        binarySearchTree.postOrder(element -> stringJoiner.add(element.toString()))

        then: "correct order should be returned"
        stringJoiner.toString() == "10, 20, 25, 15"
        and: "size should be 4"
        binarySearchTree.size == 4
    }

    def "test traversing blank tree - preorder"() {
        given: "BST is created"
        def stringJoiner = new StringJoiner(", ");

        when: "preorder is invoked"
        binarySearchTree.preOrder(element -> stringJoiner.add(element.toString()))

        then: "string joiner should not have any value"
        stringJoiner.length() == 0
    }

    def "test traversing blank tree - post order"() {
        given: "BST is created"
        def stringJoiner = new StringJoiner(", ");

        when: "postOrder is invoked"
        binarySearchTree.postOrder(element -> stringJoiner.add(element.toString()))

        then: "string joiner should not have any value"
        stringJoiner.length() == 0
    }

    def "test contains operation - for existing element"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("15")
        binarySearchTree.insert("10")
        binarySearchTree.insert("25")
        binarySearchTree.insert("20")

        when: "contains operation is triggered for an existing element"
        def contains = binarySearchTree.contains("20")

        then: "contains operation should return true"
        contains
    }

    def "test contains operation - for missing element"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("15")
        binarySearchTree.insert("10")
        binarySearchTree.insert("25")
        binarySearchTree.insert("20")

        when: "contains operation is triggered for an missing element"
        def contains = binarySearchTree.contains("21")

        then: "contains operation should return false"
        !contains
    }

    def "test removing a null element"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("15")
        binarySearchTree.insert("10")
        binarySearchTree.insert("25")
        binarySearchTree.insert("20")

        when: "a null element is removed"
        binarySearchTree.remove(null)

        then: "exception is thrown"
        thrown(NullPointerException)
    }

    def "test removing an element from the tree which is NOT present"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("50")
        binarySearchTree.insert("30")
        binarySearchTree.insert("20")
        binarySearchTree.insert("40")
        binarySearchTree.insert("70")
        binarySearchTree.insert("60")
        binarySearchTree.insert("80")

        when: "element 25 is removed"
        def removed = binarySearchTree.remove("25")

        then: "size should be same"
        binarySearchTree.getSize() == 7
        and: "NO element should be removed"
        !removed
        and: "tree should contain the original elements"
        binarySearchTree.toString() == "20, 30, 40, 50, 60, 70, 80"
    }

    def "test removing an element from the tree which is present at leaf node"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("50")
        binarySearchTree.insert("30")
        binarySearchTree.insert("20")
        binarySearchTree.insert("40")
        binarySearchTree.insert("70")
        binarySearchTree.insert("60")
        binarySearchTree.insert("80")

        when: "element 20 is removed"
        def removed = binarySearchTree.remove("20")

        then: "size should be same"
        binarySearchTree.getSize() == 6
        and: "element should be removed"
        removed
        and: "tree should contain the original elements"
        binarySearchTree.toString() == "30, 40, 50, 60, 70, 80"
    }

    def "test removing an element from the tree which is present at root node"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("50")
        binarySearchTree.insert("30")
        binarySearchTree.insert("20")
        binarySearchTree.insert("40")
        binarySearchTree.insert("70")
        binarySearchTree.insert("60")
        binarySearchTree.insert("80")

        when: "element 50 is removed"
        def removed = binarySearchTree.remove("50")

        then: "size should be same"
        binarySearchTree.getSize() == 6
        and: "element should be removed"
        removed
        and: "tree should contain the original elements"
        binarySearchTree.toString() == "20, 30, 40, 60, 70, 80"
    }

    def "test removing an element from the tree"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("50")
        binarySearchTree.insert("30")
        binarySearchTree.insert("20")
        binarySearchTree.insert("40")
        binarySearchTree.insert("70")
        binarySearchTree.insert("60")
        binarySearchTree.insert("80")

        when: "element 70 is removed"
        def removed = binarySearchTree.remove("70")

        then: "size should be same"
        binarySearchTree.getSize() == 6
        and: "element should be removed"
        removed
        and: "tree should contain the original elements"
        binarySearchTree.toString() == "20, 30, 40, 50, 60, 80"
    }

    def "test removing an element from only right subtree"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("20")
        binarySearchTree.insert("30")
        binarySearchTree.insert("50")

        when: "elements are removed"
        def removed = binarySearchTree.remove("50")

        then: "element should be removed"
        removed
        and:
        binarySearchTree.getSize() == 2
    }

    def "test removing an element from only left subtree"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("50")
        binarySearchTree.insert("30")
        binarySearchTree.insert("20")

        when: "elements are removed"
        def removed = binarySearchTree.remove("50")

        then: "element should be removed"
        removed
        and:
        binarySearchTree.getSize() == 2
    }

    @Unroll("testing removal of #toBeRemoved element")
    def "test removing multiple elements"(String toBeRemoved) {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("50")
        binarySearchTree.insert("30")
        binarySearchTree.insert("20")
        binarySearchTree.insert("40")
        binarySearchTree.insert("70")
        binarySearchTree.insert("60")
        binarySearchTree.insert("80")

        when: "elements are removed"
        def removed = binarySearchTree.remove(toBeRemoved)

        then:
        removed == expectedRemove

        where:
        toBeRemoved | expectedRemove
        "90"        | false
        "50"        | true
        "30"        | true
        "20"        | true
        "40"        | true
        "70"        | true
        "60"        | true
        "80"        | true
    }

    def "test height of a empty tree"() {
        given: "BST is created"

        when: "its height is checked"
        def height = binarySearchTree.height()

        then: "it should be zero"
        height == 0
    }

    // https://stackoverflow.com/questions/4065439/height-of-a-tree-with-only-one-node
    def "test height of single node BST when root at 1 height"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("50")

        when: "its height is checked"
        def height = binarySearchTree.height()

        then: "it should be 1"
        height == 1
    }

    // https://stackoverflow.com/questions/4065439/height-of-a-tree-with-only-one-node
    def "test height of single unavailable node"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert("50")
        binarySearchTree.insert("30")
        binarySearchTree.insert("20")
        binarySearchTree.insert("40")
        binarySearchTree.insert("70")
        binarySearchTree.insert("60")
        binarySearchTree.insert("80")

        when: "height is checked of a missing node"
        def height = binarySearchTree.height("90")

        then: "it should be 0"
        height == 0
    }

    /**
     *         50
     *       /    \
     *      /      \
     *     /        \
     *    30         70
     *   /  \       /  \
     *  20  40     60  80
     *                   \
     *                    100
     *
     * @return
     */
    @Unroll("testing height of #node element")
    def "test height of various nodes when root at 1 height"(Integer node) {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert(50)
        binarySearchTree.insert(30)
        binarySearchTree.insert(20)
        binarySearchTree.insert(40)
        binarySearchTree.insert(70)
        binarySearchTree.insert(60)
        binarySearchTree.insert(80)
        binarySearchTree.insert(100)

        when: "its height is checked"
        def actualHeight = binarySearchTree.height(node)

        then: "its height should match"
        actualHeight == expectedHeight

        where:
        node | expectedHeight
        50   | 4
        30   | 2
        20   | 1
        40   | 1
        70   | 3
        60   | 1
        80   | 2
        100  | 1
    }

    @Unroll("testing depth of #node element")
    def "test depth of various nodes when root at 0 depth"(Integer node) {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert(50)
        binarySearchTree.insert(30)
        binarySearchTree.insert(20)
        binarySearchTree.insert(40)
        binarySearchTree.insert(70)
        binarySearchTree.insert(60)
        binarySearchTree.insert(80)
        binarySearchTree.insert(100)

        when: "its height is checked"
        def actualDepth = binarySearchTree.depth(node)

        then: "its depth should match"
        actualDepth == expectedDepth

        where:
        node | expectedDepth
        50   | 1
        30   | 2
        20   | 3
        40   | 3
        70   | 2
        60   | 3
        80   | 3
        100  | 4
    }

    def "check depth of a node when tree is empty"() {
        given: "BST is created"

        when: "depth is checked for some node"
        def depth = binarySearchTree.depth(50)

        then: "it should be -1"
        depth == -1
    }

    def "check depth of a non-existent node"() {
        given: "BST is created"
        and: "some elements are inserted"
        binarySearchTree.insert(50)
        binarySearchTree.insert(30)
        binarySearchTree.insert(20)
        binarySearchTree.insert(40)
        binarySearchTree.insert(70)
        binarySearchTree.insert(60)
        binarySearchTree.insert(80)
        binarySearchTree.insert(100)

        when: "depth is checked for some node"
        def depth = binarySearchTree.depth(110)

        then: "it should be 0"
        depth == 0
    }
}
