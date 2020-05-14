package ru.job4j.collections.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MyHashMapTest {
    @Test
    public void whenAddPairThenGet() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.insert("first", 1);
        map.insert("second", 2);
        map.insert("third", 3);
        assertThat(map.get("second"), is(2));
        assertThat(map.get("first"), is(1));
        assertThat(map.get("third"), is(3));
    }

    @Test
    public void whenAddAndInvokeNextThenGet() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.insert("first", 1);
        Iterator<Integer> it = map.iterator();
        assertThat(it.next(), is(1));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCreateIterAndAddThenThrowCME() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.insert("first", 1);
        Iterator<Integer> it = map.iterator();
        map.insert("second", 2);
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCreateEmptyIterThenThrowNSEE() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        Iterator<Integer> it = map.iterator();
        it.next();
    }
}
