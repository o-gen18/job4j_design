package ru.job4j.collections.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> last;

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
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

    public T deleteFirst() {
        Node<T> deleted = head;
        if (head == null) {
            throw new NoSuchElementException();
        } else {
            head = head.next;
        }
        return deleted.value;
    }

    public T deleteLast() {
        Node<T> nodeHead = head;
        Node<T> nodeLast = last;
        if (nodeHead == null) {
            throw new NoSuchElementException();
        } else {
            while (nodeHead.next != null) {
                last = nodeHead;
                nodeHead = nodeHead.next;
            }
        }
        return nodeLast.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> node = head;

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
