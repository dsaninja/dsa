package com.dsaninja.ds;

import net.jcip.annotations.NotThreadSafe;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A custom linked list implementation which is circular in nature. The list is a singly linked list
 * and supports the following operations:
 *
 * <ol>
 *     <li><code>add(value)</code>: add <code>value</code> at the end of the list</li>
 *     <li><code>add(index,value)</code>: add <code>value</code> at <code>index</code></li>
 *     <li><code>remove(value)</code>: remove node identified by <code>value</code>. If multiple occurrences are there, delete the first one</li>
 *     <li><code>size()</code>: returns the size of list</li>
 *     <li><code>search(value)</code>: returns the index where the <code>value</code> is present; -1 otherwise</li>
 *     <li><code>stream()</code>: returns a stream of elements</li>
 *     <li><code>toString()</code>: returns a comma seperated list of elements</li>
 * </ol>
 * <p>
 * Except <code>size()</code> method, other methods complete in <code>O(n)</code> time.
 *
 * @author gaurs
 */
@NotThreadSafe
public class CircularLinkedList<E>{
    private int size;
    private Node head;
    private Node end;

    /**
     * The method is used to insert a value at the end of the list.
     *
     * @param element the  vale to be inserted
     * @throws NullPointerException if element is null
     */
    public void add(E element){
        if(null == element){
            throw new NullPointerException("element cannot be null");
        }

        if(null == head){
            head = new Node(element);
            head.next = head;
            end = head;
        } else{
            Node newNode = new Node(element);
            newNode.next = head;
            end.next = newNode;
            end = newNode;
        }
        size++;
    }

    /**
     * Insert element at index location.
     *
     * @param index   the index on which value is to be inserted
     * @param element element to be inserted
     * @throws IllegalArgumentException if index > size
     */
    public void add(int index, E element){
        if(index > size){
            throw new IllegalArgumentException("please specify a valid index");
        }

        // last node
        if(index == size){
            add(element);
            return;
        }

        Node indexMinusOneNode = getIthNode(index - 1);
        Node nextNode = indexMinusOneNode.next;

        Node newNode = new Node(element);
        indexMinusOneNode.next = newNode;
        newNode.next = nextNode;
        size++;
    }

    /**
     * The method will trigger a search on this list and will return an index on which
     * the specified element is found or -1 otherwise.
     *
     * @param element to be searched
     * @return index if found; -1 otherwise
     */
    public int search(E element){
        Node temp = head;
        int index = 0;

        // temp == null for empty list
        while(temp != null && !temp.data.equals(element)){
            index++;
            temp = temp.next;
            // circled back
            if(temp == head){
                break;
            }
        }

        if(index >= size){
            return -1;
        } else{
            return index;
        }
    }

    /**
     * Remove the specific element if exist from the list
     *
     * @param element to be removed
     */
    public void remove(E element){
        Node current = head;
        Node previous = null;

        // the while loop will not work here
        // as for first node current == head will
        // be true and the loop will break
        do{
            if(current.data.equals(element)){
                if(null == previous){
                    // head node
                    // delete head node
                    current = current.next;
                    head = current;
                    end.next = head;
                } else{
                    previous.next = current.next;
                    current.next = null;
                }
                size--;
                break;
            }
            previous = current;
            current = current.next;
        } while(current != head);
    }

    /**
     * Remove the value at specified index if present.
     *
     * @param index to be deleted
     * @throws IllegalArgumentException in case of invalid index
     */
    public void remove(int index){
        if(index >= size){
            throw new IllegalArgumentException("invalid index specified");
        }

        if(index == 0){
            Node toBeDeleted = head;
            head = head.next;
            end.next = head;

            toBeDeleted.data = null;
            toBeDeleted.next = null;
        } else{
            Node previousToIndex = head;
            while(--index > 0){
                previousToIndex = previousToIndex.next;
            }

            Node toBeDeleted = previousToIndex.next;
            previousToIndex.next = toBeDeleted.next;

            toBeDeleted.next = null;
            toBeDeleted.data = null;
        }
        size--;
    }

    @Override
    public String toString(){
        return this.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    /**
     * Returns a stream of elements stored in this list.
     *
     * @return stream of elements
     */
    public Stream<E> stream(){
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<>(){
            Node dataRef = head;
            int temp = size;

            @Override
            public boolean hasNext(){
                return temp > 0;
            }

            @Override
            public E next(){
                E data = dataRef.data;
                dataRef = dataRef.next;
                temp--;
                return data;
            }
        }, Spliterator.ORDERED), false);
    }

    public int size(){
        return size;
    }

    private Node getIthNode(int index){
        Node node = head;
        while(index-- > 0){
            node = node.next;
        }

        return node;
    }

    /**
     * A private class denoting a node in the linked list.
     */
    private class Node{
        E data;
        Node next;

        Node(E data){
            this.data = data;
        }
    }

}
