package com.dsaninja.ds;

import net.jcip.annotations.NotThreadSafe;

import java.util.Optional;

/**
 * A custom fixed size stack implementation backed by an array. The following operations are supported:
 * <ol>
 *     <li>push: the element on top of stack</li>
 *     <li>pop: pop the top element from the stack</li>
 *     <li>peek: peek the top element without removing it</li>
 *     <li>size: check the current number of elements present</li>
 * </ol>
 * <p>
 * Exceptions are thrown if an item is pushed to a full stack or an item is
 * popped from an empty stack.
 *
 * @author gaurs
 */
@NotThreadSafe
@SuppressWarnings("unchecked")
public class Stack<E>{

    private final int size;
    private final Object[] elements;
    private int top;

    /**
     * Create a fixed size stack backed by an array
     *
     * @param size the initial size of stack
     */
    public Stack(int size){
        this.size = size;
        elements = new Object[size];
    }

    /**
     * Push an element on top of the stack. If the stack is already full, it will throw
     * a RuntimeException denoting the same.
     *
     * @param value the value to be pushed.
     * @throws RuntimeException in case stack is already full
     */
    public void push(E value){
        if(top == size){
            throw new RuntimeException("stack is full");
        }

        elements[top++] = value;
    }

    /**
     * Pop the value from the stack and decrease the count by 1. If the stack is empty,
     * it will throw a RuntimeException
     *
     * @return the top element
     * @throws RuntimeException in case stack is empty
     */
    public E pop(){
        if(top == 0){
            throw new RuntimeException("empty stack");
        }

        // index is 0 based
        top--;
        return (E) elements[top];
    }

    /**
     * Returns an optional value from the top of the stack without removing
     * it from the stack.
     *
     * @return value from top of the stack if present.
     */
    public Optional<E> peek(){
        return top == 0 ? Optional.empty() : Optional.of((E) elements[top - 1]);
    }

    /**
     * Check the current number of items present in the stack
     *
     * @return current item count
     */
    public int size(){
        return top;
    }
}
