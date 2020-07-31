package ru.job4j.collections.map;

import java.util.*;

public class MyHashMap<K, V> implements Iterable<V> {
    private Entry<K, V>[] array;

    private int index = 0;

    private int modCount = 0;

    private final float loadFactor = 0.75f;

    private int latestCapacity;

    private static class Entry<K, V> {
        private final int hash;
        private final K key;
        private V value;
        private Entry<K, V> next;

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

    public MyHashMap() {
        array = (Entry<K, V>[]) new Entry<?, ?>[16];
        latestCapacity = array.length;
    }

    private int rehash(K key, Entry<?, ?>[] newArray) {
        int hash = Objects.hash(key);
        return (hash & 0x7FFFFFFF) % newArray.length;
    }

    private void enlargeArray() {
        Entry<K, V>[] newArray = new Entry[latestCapacity << 1];
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == null) {
                continue;
            }
            for (Entry<K, V> pair = (Entry<K, V>) array[i]; pair != null;) {
                Entry<K, V> newHead = pair;
                pair = pair.next;

                int position = rehash(newHead.getKey(), newArray);
                newHead.next = newArray[position];
                newArray[position] = newHead;
            }
        }
        array = newArray;
        latestCapacity = array.length;
    }
//    private void enlargeArray() { // Метод предложенный Rail Shamsemuhametov
//        int prevCapacity = latestCapacity;
//        latestCapacity = latestCapacity << 1;
//        Entry<K, V>[] newArray = new Entry[latestCapacity];
//        for (int i = 0; i < prevCapacity; i++) {
//            if (array[i] != null) {
//                int h = rehash(array[i].key, newArray);
//                newArray[h] = new Entry<>(h, array[i].key, array[i].value, null);
//            }
//        }
//        array = newArray;
//    }

    public V insert(K key, V value) {
        if ((float) array.length / index >= loadFactor) {
            enlargeArray();
        }
        int hash = Objects.hash(key);
        int position = (hash & 0x7FFFFFFF) % array.length;
        Entry<K, V> oldHeadOfBucket = array[position];
        for (; oldHeadOfBucket != null; oldHeadOfBucket = oldHeadOfBucket.next) {
            if ((oldHeadOfBucket.hash == hash) && oldHeadOfBucket.key.equals(key)) {
                V old = oldHeadOfBucket.value;
                oldHeadOfBucket.value = value;
                return old;
            }
        }
        array[position] = new Entry<K, V>(hash, key, value, oldHeadOfBucket);
        modCount++;
        index++;
        return null;
    }

    public V get(K key) {
        int hash = Objects.hash(key);
        int index = (hash & 0x7FFFFFFF) % array.length;
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
        boolean result = false;
        int hash = Objects.hash(key);
        int position = (hash & 0x7FFFFFFF) % array.length;
        if (array[position] == null) {
            return false;
        } else {
            Entry<K, V> previous = null;
            Entry<K, V> head = array[position];
            if (head.key.equals(key)) {
                if (array[position].next == null) {
                    index--;
                }
                array[position] = array[position].next;
                modCount++;
                result = true;
            } else {
                previous = head;
                head = head.next;
                while (head != null && !head.key.equals(key)) {
                    previous = head;
                    head = head.next;
                }
                if (head != null) {
                    previous.next = head.next;
                    modCount++;
                    result = true;
                }
            }
        }
        return result;
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
