package ru.job4j.collections.exam;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FreezeStrTest {
    @Test
    public void whenEq() {
        assertThat(FreezeStr2.eq("Hello", "Hlloe"), is(true));
    }

    @Test
    public void whenNotEq() {
        assertThat(FreezeStr2.eq("Hello", "Halle"), is(false));
    }

    @Test
    public void whenNotMultiEq() {
        assertThat(FreezeStr2.eq("heloo", "hello"), is(false));
    }
}
