package project20280.list;

import project20280.interfaces.List;

import java.awt.image.Kernel;
import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private final E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
            data = e;
            prev = p;
            next = n;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.next = tail;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        Node <E> newNode= new Node<>(e, pred, succ);
        pred.next = newNode;
        succ.prev = newNode;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head.data == null && size() == 0;
    }

    @Override
    public E get(int i) {
        Node<E> node = head.next;

        for (int j = 0; j < i; j++) {
            node = node.next;
        }

        return node.data;
    }

    @Override
    public void add(int i, E e) {
        if (i > size() || i < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (i == 0) {
            addFirst(e);
        }
        else if (i == size()) {
            addLast(e);
        }
        else {
            Node<E> node = head.next;

            for (int j = 0; j < i; j++) {
                node = node.next;
            }

            addBetween(e, node.prev, node);
        }
    }

    @Override
    public E remove(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> node = head.next;

        if (i == 0) {
            return removeFirst();
        }
        else if (i == size() - 1) {
            return removeLast();
        }
        else {
            for (int j = 0; j < i; j++) {
                node = node.next;
            }
            return remove(node);
        }
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head.next;

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
        return new DoublyLinkedListIterator<E>();
    }

    private E remove(Node<E> n) {
        Node<E> prevNode = n.prev;
        Node<E> nextNode = n.next;

        n.prev = null;
        n.next = null;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        size--;
        return n.data;
    }

    public E first() {
        return head.next.getData();
    }

    public E last() {
        return tail.prev.getData();
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) return null;
        return remove(head.next);
    }

    @Override
    public E removeLast() {
        if (isEmpty()) return null;
        return remove(tail.prev);
    }

    @Override
    public void addLast(E e) {
        addBetween(e, tail.prev, tail);
    }

    @Override
    public void addFirst(E e) {
        addBetween(e, head, head.next);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.next;
        while (curr != tail) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        for (int i = 0; i < 5; ++i) ll.addLast(i);

        for (int i = 0; i < ll.size(); i++) {

            //System.out.println(ll.size());
            System.out.println(ll.get(i));
        }

    }
}