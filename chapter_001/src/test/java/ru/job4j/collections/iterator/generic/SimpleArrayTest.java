package ru.job4j.collections.iterator.generic;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class SimpleArrayTest {
    @Test
    public void whenAddElementsThenGetThem() throws Exception {
        SimpleArray array = new SimpleArray(3);
        String first = "Hello";
        int second = 2;
        boolean third = true;
        array.add(first);
        array.add(second);
        array.add(third);
        assertThat(array.get(0), is("Hello"));
        assertThat(array.get(1), is(2));
        assertThat(array.get(2), is(true));
    }

    @Test
    public void whenSetElementThenReplaceFormerOne() throws Exception {
        SimpleArray array = new SimpleArray(1);
        String first = "Hello";
        array.add(first);
        array.set(0, 22.22);
        assertThat(array.get(0), is(22.22));
    }

    @Test
    public void whenRemoveElementThenNull() throws Exception {
        SimpleArray array = new SimpleArray(3);
        String first = "Hello";
        int second = 2;
        boolean third = true;
        array.add(first);
        array.add(second);
        array.add(third);
        array.remove(0);
        assertThat(array.get(0), is(2));
    }

    @Test
    public void whenIterateThenEverythingIsOk() throws Exception {
        SimpleArray array = new SimpleArray(3);
        String first = "Hello";
        int second = 2;
        boolean third = true;
        array.add(first);
        array.add(second);
        array.add(third);
        SimpleArrayIterator iterator = (SimpleArrayIterator) array.iterator();
        assertThat(iterator.next(), is("Hello"));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(true));
        assertThat(iterator.hasNext(), is(false));
    }
}
