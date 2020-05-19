package ru.job4j.collections.map;

import java.util.*;

public class MyHashMap<K, V> implements Iterable<V> {
    private Entry<K, V>[] array;

    private int index = 0;

    private int modCount = 0;

    private final float LOAD_FACTOR = 0.75f;

    private int latestCapacity;

    private static class Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Entry<K, V> next;

        protected Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            if (value == null) {
                throw new NullPointerException();
            }
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

    private int rehash(K key, Entry<?, ?>[] newArray) {
        int hash = Objects.hash(key);
        return (hash & 0x7FFFFFFF) % newArray.length;
    }

    private void enlargeArray() {
        Entry<K, V>[] newArray = (Entry<K, V>[]) new Entry<?, ?>[latestCapacity << 1];
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == null) {
                continue;
            }
            for (Entry<K, V> pair = (Entry<K, V>) array[i]; pair != null; ) {
                int position = rehash(pair.getKey(), newArray);
                if (newArray[position] != null) {
                    newArray[position].next = pair;
                } else {
                    newArray[position] = pair;
                }
                pair = pair.next;
            }
        }
        array = newArray;
        latestCapacity = array.length;
    }

    public MyHashMap() {
        array = (Entry<K, V>[]) new Entry<?, ?>[10];
        latestCapacity = array.length;
    }

    public boolean insert(K key, V value) {
        if ((float) array.length/index >= LOAD_FACTOR) {
            enlargeArray();
        }
        int hash = Objects.hash(key);
        int position = (hash & 0x7FFFFFFF) % array.length;
        if (array[position] != null) {
            array[position].next = new Entry<K, V>(hash, key, value, null);
        } else {
            array[position] = new Entry<>(hash, key, value, null);
            index++;
            modCount++;
        }
        return true;
    }

    public V get(K key) {
        int hash = Objects.hash(key);
        int index = (hash & 0x7FFFFFFF)  % array.length;
        V value = null;
        for (Entry<K, V> pair = array[index]; pair != null;) {
            if (pair.getKey().equals(key)) {
                value = pair.getValue();
                break;
            }
            pair = pair.next;
        }
        return value;
    }

    public boolean delete(K key) {
        int hash = Objects.hash(key);
        int position = (hash & 0x7FFFFFFF) % array.length;
        if (array[position] == null) {
            return false;
        } else {
            for (Entry<K, V> pair = array[position]; pair != null;) {
                if (pair.getKey().equals(key)) {
                    pair = null;
                    break;
                }
                pair = pair.next;
            }
            index--;
            modCount++;
            return true;
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private Entry<K, V>[] arrayItr = array;
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
                return (V) arrayItr[indexItr++].getValue();
            }
        };
    }
}
