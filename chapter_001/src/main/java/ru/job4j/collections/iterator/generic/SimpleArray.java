package ru.job4j.collections.iterator.generic;

import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {
    private T[] array;
    private int position = 0;

    public SimpleArray(int size) {
        array = (T[]) new Object[size];
    }

    public void add(T model) throws Exception {
        if (position >= array.length) {
            throw new Exception();
        }
        array[position++] = model;
    }

    public void set(int index, T model) {
        array[index] = model;
    }

    public void remove(int index) {
        for (int i = index; i < array.length - 1; i++) {
            array[i] = array[i + 1];
            position--;
        }
    }

    public T get(int index) {
        return array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator(this.array);
    }
}
