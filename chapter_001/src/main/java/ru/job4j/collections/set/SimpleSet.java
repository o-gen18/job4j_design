package ru.job4j.collections.set;

import ru.job4j.collections.list.SimpleArray;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArray<E> set = new SimpleArray<>();

    public boolean contains(E value) {
        boolean result = false;
        for (E element : set) {
            if (element.equals(value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void add(E e) {
        if (contains(e)) {
            return;
        } else {
            set.add(e);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }
}
