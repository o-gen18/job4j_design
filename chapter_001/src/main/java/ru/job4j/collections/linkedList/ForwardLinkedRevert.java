package ru.job4j.collections.linkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinkedRevert<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> last;

    private static class Node<T> {
        T value;
        Node<T> previous;
        Node<T> next;

        public Node(Node<T> previous, T value, Node<T> next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }

    public void add(T value) {
        Node<T> node = new Node<T>(last, value, null);
        if (head == null) {
            head = node;
            last = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        last = node;
    }

    public void revert() {
        Node<T> current = head;
        Node<T> next;
        Node<T> previous = current.previous;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }
}
