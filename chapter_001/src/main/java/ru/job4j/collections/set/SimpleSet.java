package ru.job4j.collections.set;

import ru.job4j.collections.list.SimpleArray;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArray<E> set = new SimpleArray<>();

    public void add(E e) {
        for (E element : set) {
            if (element.equals(e)) {
                return;
            }
        }
        set.add(e);
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }
}
