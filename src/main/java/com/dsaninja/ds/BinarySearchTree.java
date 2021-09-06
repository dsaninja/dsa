package com.dsaninja.ds;

import java.util.StringJoiner;
import java.util.function.Consumer;

/**
 * A binary search tree implementation in java. The code supports the following operations:
 * <ol>
 *     <li><strong>Insert</strong>: insert a new element in the tree</li>
 *     <li><strong>Remove</strong>: remove a specific element by value from the tree</li>
 *     <li><strong>Contains</strong>: check if an element is present in the tree</li>
 *     <li><strong>Traverse</strong>: traverse the tree in various orders:
 *     <ol>
 *      <li>In-order</li>
 *      <li>Pre-order</li>
 *      <li>Post-order</li>
 *      </ol>
 *      <li><strong>size</strong>: number of nodes in the tree</li>
 *      <li><strong>toString</strong>: comma seperated list of elements, appended built using in-order tree traversal</li>
 *     </li>
 * </ol>
 * <p>
 * The shape of the binary search tree depends entirely on the order of
 * insertions and deletions and can become degenerate which may lead to
 * O(n) time for various operations.
 *
 * @author gaurs
 */
public class BinarySearchTree<T extends Comparable<T>>{

    private Node root;
    private int size;

    /**
     * Insert the element ensuring that the BST property remains intact
     *
     * @param element to be inserted
     * @throws NullPointerException if element is null
     */
    public void insert(T element){
        if(null != element){
            if(root == null){
                root = new Node(element);
                size++;
            } else{
                append(root, element);
            }
        } else{
            throw new NullPointerException("element can't be null");
        }
    }

    /**
     * Returns the current number of elements in the tree
     *
     * @return number of elements
     */
    public int getSize(){
        return size;
    }

    /**
     * Traverse the tree in-order i.e. left, root, right
     * and perform the action denoted by consumer in each step.
     *
     * @param consumer the element consumer
     */
    public void inOrder(Consumer<T> consumer){
        inOrder(root, consumer);
    }

    /**
     * Traverse the tree in pre-order i.e. root, left, right
     * and perform the action denoted by consumer in each step.
     *
     * @param consumer the element consumer
     */
    public void preOrder(Consumer<T> consumer){
        preOrder(root, consumer);
    }

    /**
     * Traverse the tree in post-order i.e. left, right, root
     * and perform the action denoted by consumer in each step.
     *
     * @param consumer the element consumer
     */
    public void postOrder(Consumer<T> consumer){
        postOrder(root, consumer);
    }

    /**
     * Check if the element is present in the tree or not
     *
     * @param element to be searched
     * @return true if present, false otherwise
     */
    public boolean contains(T element){
        return checkPresence(root, element);
    }

    /**
     * The method is used to remove an element from the tree.
     * After the removal, the BST property should be intact.
     *
     * @param element to be removed
     * @return true if the element was present and is removed now; false otherwise
     * @throws NullPointerException for null element
     */
    public boolean remove(T element){
        if(element == null){
            throw new NullPointerException("null element are not allowed");
        }

        int originalSize = size;
        root = remove(root, element);
        return size + 1 == originalSize;
    }

    @Override
    public String toString(){
        StringJoiner stringJoiner = new StringJoiner(", ");
        inOrder(element -> stringJoiner.add(element.toString()));
        return stringJoiner.toString();
    }

    private void append(Node start, T element){
        if(element.compareTo(start.data) < 0){
            if(null == start.left){
                start.left = new Node(element);
                size++;
            } else{
                append(start.left, element);
            }
        } else{
            if(null == start.right){
                start.right = new Node(element);
                size++;
            } else{
                append(start.right, element);
            }
        }
    }

    private void inOrder(Node head, Consumer<T> consumer){
        if(null == head){
            return;
        }

        inOrder(head.left, consumer);
        consumer.accept(head.data);
        inOrder(head.right, consumer);
    }

    private void preOrder(Node head, Consumer<T> consumer){
        if(null == head){
            return;
        }

        consumer.accept(head.data);
        inOrder(head.left, consumer);
        inOrder(head.right, consumer);
    }

    private void postOrder(Node head, Consumer<T> consumer){
        if(null == head){
            return;
        }

        inOrder(head.left, consumer);
        inOrder(head.right, consumer);
        consumer.accept(head.data);
    }

    private Node remove(Node root, T element){
        // empty tree, return null
        if(root == null){
            return null;
        }

        // data at root is to be deleted
        if(element.compareTo(root.data) == 0){
            // no element in left of current root, return the right subtree
            if(root.left == null){
                size--;
                return root.right;
            }
            // no element in right of current root, return the left subtree
            else if(root.right == null){
                size--;
                return root.left;
            }
            // i.e. both left and right subtree are present
            // then set the current root value to be the leftmost
            // value in right subtree i.e. its inOrderSuccessor
            else{
                T leftMostValueInRightTree = getInOrderSuccessor(root.right);
                root.data = leftMostValueInRightTree;

                // update the right subtree to be the one with
                // leftMostValueInRightTree removed as it's already
                // copied to the current root and is duplicate
                root.right = remove(root.right, leftMostValueInRightTree);
            }
        }
        // value is less than current root value
        else if(element.compareTo(root.data) < 0){
            root.left = remove(root.left, element);
        }
        // value is greater than current root value
        else{
            root.right = remove(root.right, element);
        }

        // return updated root
        return root;
    }

    private T getInOrderSuccessor(Node currentRoot){
        T element = currentRoot.data;
        while(currentRoot.left != null){
            currentRoot = currentRoot.left;
            element = currentRoot.data;
        }

        return element;
    }

    private boolean checkPresence(Node root, T element){
        if(null != root){
            if(element.compareTo(root.data) == 0){
                return true;
            } else if(element.compareTo(root.data) < 0){
                return checkPresence(root.left, element);
            } else{
                return checkPresence(root.right, element);
            }
        }
        return false;
    }

    private class Node{
        T data;
        Node left;
        Node right;

        Node(T data){
            this.data = data;
        }
    }

}