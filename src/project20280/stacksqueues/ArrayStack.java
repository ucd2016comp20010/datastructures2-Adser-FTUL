package project20280.stacksqueues;

import project20280.interfaces.Stack;

import java.util.Optional;

public class ArrayStack<E> implements Stack<E> {

    /**
     * Default array capacity.
     */
    public static final int CAPACITY = 100;   // default array capacity

    /**
     * Generic array used for storage of stack elements.
     */
    private E[] data;                        // generic array used for storage

    /**
     * Index of the top element of the stack in the array.
     */
    private int t = -1;                      // index of the top element in stack

    /**
     * Constructs an empty stack using the default array capacity.
     */
    public ArrayStack() {
        this(CAPACITY);
    }  // constructs stack with default capacity

    /**
     * Constructs and empty stack with the given array capacity.
     *
     * @param capacity length of the underlying array
     */
    @SuppressWarnings({"unchecked"})
    public ArrayStack(int capacity) {        // constructs stack with given capacity
        data = (E[]) new Object[CAPACITY];
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return number of elements in the stack
     */
    @Override
    public int size() {
        return (t + 1);
    }

    /**
     * Tests whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Inserts an element at the top of the stack.
     *
     * @param e the element to be inserted
     * @throws IllegalStateException if the array storing the elements is full
     */
    @Override
    public void push(E e) {
        this.data[size()] = e;
        t++;
    }

    /**
     * Returns, but does not remove, the element at the top of the stack.
     *
     * @return top element in the stack (or null if empty)
     */
    @Override
    public E top() {
        if(isEmpty()) {
            return null;
        }
        return this.data[t];
    }

    /**
     * Removes and returns the top element from the stack.
     *
     * @return element r    emoved (or null if empty)
     */
    @Override
    public E pop() {
        if(size() == 0) {
            return null;
        }
        E topElement = this.data[t];
        this.data[t] = null;
        t--;
        return topElement;
    }

    /**
     * Produces a string representation of the contents of the stack.
     * (ordered from top to bottom). This exists for debugging purposes only.
     *
     * @return textual representation of the stack
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (!isEmpty()) {
            for (int i = t; i >= 0; --i) {
                sb.append(data[i]);
                if (i != 0) sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    /**
     * Demonstrates sample usage of a stack.
     */
    public static void main(String[] args) {

    }
}
