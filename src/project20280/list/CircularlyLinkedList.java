package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private  T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        Node<E> node = tail.next;

        for (int j = 0; j < i; j++) {
            node = node.next;
        }

        return node.data;
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {

        if (i == 0) {
            addFirst(e);
        }
        else if (i == size()) {
            addLast(e);
        }
        else {
            Node<E> node = tail.next;

            for (int j = 0; j < i - 1; j++) {
                node = node.next;
            }

            Node<E> newNode = new Node<>(e, node.next);
            node.next = newNode;
        }
    }

    @Override
    public E remove(int i) {
        if(isEmpty()) {
            return null;
        }

        E data;
        if (i == 0) {
            data = tail.next.data;
            removeFirst();
        }
        else if (i == size() - 1)
        {
            data = tail.data;
            removeLast();
        }
        else {
            Node<E> node = tail.next;

            for (int j = 0; j < i - 1; j++) {
                node = node.next;
            }
            Node<E> nextNode = node.next;
            data = nextNode.data;

            node.next = nextNode.next;
            nextNode = null;
            size--;
        }

        return data;
    }

    public void rotate() {
        if (tail != null) {
            tail = tail.next;
        }
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        if(isEmpty()) return null;

        E data = tail.next.data;

        if (tail.next == tail) {
            tail = null;
        }
        else {
            Node<E> node = tail.next;
            tail.next = node.next;
        }

        size--;
        return data;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) return null;
        E data = tail.data;

        if (tail.next == tail) {
            tail = null;
        }
        else
        {
            Node<E> node = tail.next;

            while (node.next != tail) {
                node = node.next;
            }

            node.next = tail.next;
            tail = node;
        }

        size--;
        return data;
    }

    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e, null);
        if(isEmpty()) {
            tail = newNode;
            tail.next = tail;
        }
        else {
            newNode.next = tail;
            tail.next = newNode;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e, null);

        if(isEmpty()) {
            tail = newNode;
            tail.next = tail;
        }
        else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
