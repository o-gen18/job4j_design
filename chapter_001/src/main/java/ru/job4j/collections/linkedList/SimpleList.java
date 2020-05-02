package ru.job4j.collections.linkedList;

import java.util.*;

public class SimpleList<E> implements Iterable<E> {
    private int size = 0;

    private int modCount = 0;

    private Node<E> first;

    private Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> previous;

        Node(Node<E> previous, E item, Node<E> next) {
            this.next = next;
            this.previous = previous;
            this.item = item;
        }
    }

    private E[] toArray() {
        E[] array = (E[]) new Object[size];
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            array[i] = node.item;
            node = node.next;
        }
        return array;
    }

    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<E>(l, value, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }

    public E get(int index) {
        if (Objects.checkIndex(index, size) < size/2) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return (E) node.item;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.previous;
            }
            return (E) node.item;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private E[] array = toArray();

            private int index = 0;

            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return array.length > index;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return array[index++];
            }
        };
    }
}
