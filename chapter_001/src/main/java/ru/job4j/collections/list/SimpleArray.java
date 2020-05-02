package ru.job4j.collections.list;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;

    private int modCount = 0;

    private int index = 0;

    private final int initialCapacity = 10;

    private int latestCapacity;

    private void enlargeContainer() {
        container = Arrays.copyOf(container, latestCapacity * 2);
        latestCapacity *= 2;
        modCount++;
    }

    public SimpleArray() {
        container = new Object[initialCapacity];
        latestCapacity += container.length;
    }

    public SimpleArray(int size) {
        container = new Object[size];
        latestCapacity += container.length;
    }

    public void add(T model) {
        if (index >= container.length) {
            enlargeContainer();
        }
        this.container[index++] = model;
        this.modCount++;
    }

    public T get(int position) {
        return (T) this.container[Objects.checkIndex(position, index)];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private T[] array = (T[]) container;
            private int index = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                boolean result = false;
                for (int i = index; i < array.length; i++) {
                    if (array[i] != null && array.length > index) {
                        result = true;
                        break;
                    } else {
                        index++;
                    }
                }
                return result;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) array[index++];
            }
        };
    }
}
