package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

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
        if(position > size() || position < 1) {
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

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(1);
        ll.add(1, 2);
        ll.addLast(3);

        System.out.println(ll);
        System.out.println(ll.size);


        ll.remove(2);

        System.out.println(ll);
        System.out.println(ll.size);

        ll.addLast(3);
        System.out.println(ll);
        System.out.println(ll.size);

        ll.removeLast();
        System.out.println(ll);
        System.out.println(ll.size);
    }
}
