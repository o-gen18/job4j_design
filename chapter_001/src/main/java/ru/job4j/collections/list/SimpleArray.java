package ru.job4j.collections.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;

    private int modCount =0;

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

    public int getModCount() {
        return modCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator(container, modCount, this);
    }
}
