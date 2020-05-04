package ru.job4j.collections.linkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> last;

    private static class Node<T> {
        T value;
        Node<T> next;

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

    public void deleteFirst() {
        if (head != null) {
            head = head.next;
        } else throw new NoSuchElementException();
    }

    public T deleteLast() {
        Node<T> nodeHead = head;
        Node<T> nodeLast = last;
        if (nodeHead != null) {
            while (nodeHead.next != null) {
                last = nodeHead;
                nodeHead = nodeHead.next;
            }
            return nodeLast.value;
        } else throw new NoSuchElementException();
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
