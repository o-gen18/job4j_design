package ru.job4j.collections.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private T[] array;
    private int index = 0;
    private int expectedModCount;
    private SimpleArray reference;

    public SimpleArrayIterator(T[] array, int modCount, SimpleArray reference) {
        this.array = array;
        expectedModCount = modCount;
        this.reference = reference;
    }

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
        if (reference.getModCount() != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        return (T) array[index++];
    }
}
