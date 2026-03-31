package project20280.stacksqueues;

import project20280.interfaces.Queue;
import project20280.list.DoublyLinkedList;

public class LinkedQueue<E> implements Queue<E> {

    private DoublyLinkedList<E> ll;

    public static void main(String[] args) {
    }

    public LinkedQueue() {
        ll = new DoublyLinkedList<>();
    }

    @Override
    public int size() {
        return ll.size();
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        ll.addLast(e);
    }

    @Override
    public E first() {
        if (ll.isEmpty()) {
            return null;
        }

        E first = ll.first();
        return first;
    }

    @Override
    public E dequeue() {
        E first = first();
        ll.removeFirst();

        return first;
    }

    public String toString() {
        return ll.toString();
    }
}
