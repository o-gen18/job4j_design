package ru.job4j.kiss;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MaxMinTest {
    @Test
    public void whenGetMaxElement() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(2, 2, 3, 4, 9, 5, 1, 7, 8);
        Integer expected = 9;
        Integer result = maxMin.max(list, (el1, el2) -> el1.compareTo(el2));
        assertThat(result, is(expected));
    }

    @Test
    public void whenGetMinElement() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(2, 2, 3, 4, 9, 5, 1, 7, 8);
        Integer expected = 1;
        Integer result = maxMin.max(list, (el1, el2) -> el2.compareTo(el1));
        assertThat(result, is(expected));
    }
}
