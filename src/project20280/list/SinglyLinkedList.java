package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E extends Comparable<E>> implements List<E> {

    private static class Node<E> {

        private final E element;            // reference to the currentElement stored at this node

        private Node<E> next;         // reference to the subsequent node in the list

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        // Accessor methods

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        // Modifier methods

        public void setNext(Node<E> n) {
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        return size;
    }

    //@Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public E get(int position) {
        if(position > size() || position < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> node = head;

        for(int i = 0; i < position ; i++) {
            node = node.getNext();
        }

        return node.getElement();
    }

    @Override
    public void add(int position, E e) {
        if(position > size() || position < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (position == 0) {
            addFirst(e);
        }
        else if (position == size()) {
            addLast(e);
        }
        else {
            Node<E> node = head;

            for (int i = 0; i < position - 1; i++) {
                node = node.getNext();
            }
            Node<E> newNode = new Node<>(e, null);

            newNode.setNext(node.getNext());
            node.setNext(newNode);

            size++;
        }
    }


    @Override
    public void addFirst(E e) {

        head = new Node<>(e, head);

        size++;
    }

    @Override
    public void addLast(E e) {

        Node<E> node = head;

        if (node == null) {
            head = new Node<>(e, null);
        }
        else
        {
            for (int i = 0; i < size() - 1; i++) {
                node = node.getNext();
            }

            Node<E> newNode = new Node<>(e, null);
            node.setNext(newNode);
        }

        size++;
    }

    @Override
    public E remove(int position) {

        if(position > size() || position < 0) {
            throw new IndexOutOfBoundsException();
        }


        if (position == 0) {
            return removeFirst();
        }
        if (position == size()) {
            return removeLast();
        }

        Node<E> node = head;

        for (int i = 0; i < position-1; i++)
        {
            node = node.getNext();
        }

        Node<E> deadNode = node.getNext();

        node.setNext(deadNode.getNext());

        size--;
        return deadNode.getElement();
    }

    @Override
    public E removeFirst() {
        if(head == null) {
            return null;
        }

        E element = head.getElement();

        if (head.getNext() == null) {
            head = null;
        }
        else {
            head = head.getNext();
        }

        size--;
        return element;
    }

    @Override
    public E removeLast() {
        if (head == null) {
            return null;
        }
        Node<E> node = head;

        E element = node.getElement();

        if (node.getNext() == null) {
            head = null;
        }
        else
        {
            for (int i = 0; i < size() - 2; i++) {
                node = node.getNext();
            }

            Node<E> deadNode = node.getNext();
            element = deadNode.getElement();

            node.setNext(null);
        }
        size--;

        return element;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public SinglyLinkedList<E> sortedMerge(SinglyLinkedList<E> ll2) {
        SinglyLinkedList<E> merged = new SinglyLinkedList<>();

        Node<E> node1 = this.head;
        Node<E> node2 = ll2.head;

        while (node1 != null && node2 != null) {
            if (node1.getElement().compareTo(node2.getElement()) <= 0){
                merged.addLast(node1.getElement());
                node1 = node1.getNext();
            } else {
                merged.addLast(node2.getElement());
                node2 = node2.getNext();
            }
        }

        while (node1 != null) {
            merged.addLast(node1.getElement());
            node1 = node1.getNext();
        }

        while (node2 != null) {
            merged.addLast(node2.getElement());
            node2 = node2.getNext();
        }

        return merged;
    }

    public void reverse() {
        Node<E> prev = null;
        Node<E> curr = head;
        Node<E> next;
        while(curr != null) {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> twin = new SinglyLinkedList<E>();
        Node<E> tmp = head;
        while (tmp != null) {
            twin.addLast(tmp.getElement());
            tmp = tmp.next;
        }
        return twin;
    }

    public void recursiveReverse(SinglyLinkedList<E> list) {
        int i = 0;
        recursiveReverseHelper(list, i);
    }

    public void recursiveReverseHelper(SinglyLinkedList<E> list, int i) {
        if (i < list.size) {
            recursiveReverseHelper(list,i + 1);
            System.out.println(list.get(i));
        }
    }

    public SinglyLinkedList<E> recursiveCopy() {
        SinglyLinkedList<E> l2 = new SinglyLinkedList<>();

        recursiveCopyHelper(this.head , l2);
        return l2;
    }

    private void recursiveCopyHelper(Node<E> current, SinglyLinkedList<E> l2) {
        if (current != null) {
            l2.addLast(current.element);
            recursiveCopyHelper(current.next, l2);
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> l1 = new SinglyLinkedList<>();

        l1.addLast(2);
        l1.addLast(6);
        l1.addLast(20);
        l1.addLast(24);

        SinglyLinkedList<Integer> l2 = l1.recursiveCopy();

        System.out.println(l1);
        System.out.println(l2);

        l1.recursiveReverse(l1);
    }
}
