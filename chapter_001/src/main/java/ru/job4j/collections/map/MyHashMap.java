package ru.job4j.collections.map;

import java.util.*;

public class MyHashMap<K, V> implements Iterable<V> {
    private Object[] array;

    private int index = 0;

    private int modCount = 0;

    private int latestCapacity;


    private void enlargeArray() {
        array = Arrays.copyOf(array, latestCapacity * 2);
        latestCapacity = array.length;
        modCount++;
    }

    public MyHashMap() {
        array = new Object[10];
        latestCapacity = array.length;
    }

    public boolean insert(K key, V value) {
        if (index >= array.length) {
            enlargeArray();
        }
        int hash = Objects.hash(key);
        int position = (hash & 0x7FFFFFFF) % array.length;
        if (array[position] != null) {
            return false;
        } else {
            array[position] = value;
            index++;
            modCount++;
            return true;
        }
    }

    public V get(K key) {
        int hash = Objects.hash(key);
        int index = (hash & 0x7FFFFFFF) % array.length;
        return (V) array[index];
    }

    public boolean delete(K key) {
        int hash = Objects.hash(key);
        int position = (hash & 0x7FFFFFFF) % array.length;
        if (array[position] == null) {
            return false;
        } else {
            array[position] = null;
            index--;
            modCount++;
            return true;
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private Object[] arrayItr = array;
            private int indexItr = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                boolean result = false;
                for (int i = index; i < arrayItr.length; i++) {
                    if (arrayItr[i] != null) {
                        result = true;
                        indexItr = i;
                        break;
                    }
                }
                return result;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (V) arrayItr[indexItr++];
            }
        };
    }
}
