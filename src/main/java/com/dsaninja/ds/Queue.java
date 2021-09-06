package com.dsaninja.ds;

import net.jcip.annotations.NotThreadSafe;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * An unbounded queue backed by the {@link Node} class. The following operations are supported:
 * <ol>
 *     <li>add: the element on top of stack</li>
 *     <li>remove: pop the top element from the stack</li>
 *     <li>peek: peek the top element without removing it</li>
 *     <li>size: check the current number of elements present</li>
 *     <li>stream: a stream of current elements in queue</li>
 * </ol>
 * <p>
 * Exceptions are thrown if an item is pushed to a full stack or an item is
 * popped from an empty stack.
 *
 * @author gaurs
 */
@NotThreadSafe
public class Queue<E>{

    private Node first;
    private Node last;
    private int size;

    /**
     * Add the specified element to the end of the queue
     *
     * @param element to be added
     * @throws NullPointerException if element is null
     */
    public void add(E element){
        if(null == element){
            throw new NullPointerException("element cannot be null");
        }

        Node newNode = new Node();
        newNode.data = element;

        // empty queue
        if(last == null){
            last = newNode;
            first = last;
        } else{
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    /**
     * The method returns first element of the queue and removes it as well.
     *
     * @return the front element if available
     * @throws RuntimeException if queue is empty
     */
    public E remove(){
        if(first == null){
            throw new RuntimeException("empty queue");
        }

        E data = first.data;
        first = first.next;

        // removed one was last node
        if(null == first){
            last = null;
        }
        size--;
        return data;
    }

    /**
     * The method is used to check the element present at the front
     * of the queue.
     *
     * @return the first element if present; null otherwise
     */
    public E peek(){
        return null == first ? null : first.data;
    }

    /**
     * Returns the current size of the queue
     *
     * @return size
     */
    public int size(){
        return size;
    }

    /**
     * A stream of queue elements.
     *
     * @return steam of elements
     */
    public Stream<E> stream(){
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<>(){
            Node temp = first;

            @Override
            public boolean hasNext(){
                return temp != null;
            }

            @Override
            public E next(){
                E data = temp.data;
                temp = temp.next;
                return data;
            }
        }, Spliterator.ORDERED), false);
    }

    @Override
    public String toString(){
        return this.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    private class Node{
        E data;
        Node next;
    }
}
