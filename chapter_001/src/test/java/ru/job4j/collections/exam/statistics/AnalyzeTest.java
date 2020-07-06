package ru.job4j.collections.exam.statistics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalyzeTest {
    @Test
    public void when1Added1Changed1Deleted2Same() {
        List<Analyze.User> previous = List.of(
                new Analyze.User(1, "A"),
                new Analyze.User(2, "B"),
                new Analyze.User(3, "C"),
                new Analyze.User(4, "D")
        );
        List<Analyze.User> current = List.of(
                new Analyze.User(1, "AA"),
                new Analyze.User(11, "S"),
                new Analyze.User(2, "B"),
                new Analyze.User(4, "D")
        );
        Analyze.Info info = new Analyze().diff(previous, current);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getChanged(), is(1));
        assertThat(info.getDeleted(), is(1));
    }

    @Test
    public void whenAllNewAddedThenAddedEqualToDeleted() {
        List<Analyze.User> previous = List.of(
                new Analyze.User(1, "A"),
                new Analyze.User(2, "B"),
                new Analyze.User(3, "C"),
                new Analyze.User(4, "D")
        );
        List<Analyze.User> current = List.of(
                new Analyze.User(5, "E"),
                new Analyze.User(6, "F"),
                new Analyze.User(7, "G"),
                new Analyze.User(8, "H")
        );
        Analyze.Info info = new Analyze().diff(previous, current);
        assertThat(info.getAdded(), is(4));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(4));
    }

    @Test
    public void whenReversedOrderThenNoChanges() {
        List<Analyze.User> previous = List.of(
                new Analyze.User(1, "A"),
                new Analyze.User(2, "B"),
                new Analyze.User(3, "C"),
                new Analyze.User(4, "D")
        );
        List<Analyze.User> current = List.of(
                new Analyze.User(4, "D"),
                new Analyze.User(3, "C"),
                new Analyze.User(2, "B"),
                new Analyze.User(1, "A")
        );
        Analyze.Info info = new Analyze().diff(previous, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenAllAreChangedThenChanged4() {
        List<Analyze.User> previous = List.of(
                new Analyze.User(1, "A"),
                new Analyze.User(2, "B"),
                new Analyze.User(3, "C"),
                new Analyze.User(4, "D")
        );
        List<Analyze.User> current = List.of(
                new Analyze.User(1, "W"),
                new Analyze.User(2, "X"),
                new Analyze.User(3, "Y"),
                new Analyze.User(4, "Z")
        );
        Analyze.Info info = new Analyze().diff(previous, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(4));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenAllAreDeleted() {
        List<Analyze.User> previous = List.of(
                new Analyze.User(1, "A"),
                new Analyze.User(2, "B"),
                new Analyze.User(3, "C"),
                new Analyze.User(4, "D")
        );
        List<Analyze.User> current = List.of(
        );
        Analyze.Info info = new Analyze().diff(previous, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(4));
    }

    @Test
    public void whenAllAreAdded() {
        List<Analyze.User> previous = List.of(
        );
        List<Analyze.User> current = List.of(
                new Analyze.User(1, "A"),
                new Analyze.User(2, "B"),
                new Analyze.User(3, "C"),
                new Analyze.User(4, "D")
        );
        Analyze.Info info = new Analyze().diff(previous, current);
        assertThat(info.getAdded(), is(4));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void when4Added1Changed1Deleted() {
        List<Analyze.User> previous = List.of(
                new Analyze.User(1, "A"),
                new Analyze.User(2, "B"),
                new Analyze.User(3, "C"),
                new Analyze.User(4, "D")
        );
        List<Analyze.User> current = List.of(
                new Analyze.User(1, "A"),
                new Analyze.User(2, "B"),
                new Analyze.User(4, "@"),
                new Analyze.User(5, "E"),
                new Analyze.User(6, "F"),
                new Analyze.User(7, "G"),
                new Analyze.User(8, "H")

        );
        Analyze.Info info = new Analyze().diff(previous, current);
        assertThat(info.getAdded(), is(4));
        assertThat(info.getChanged(), is(1));
        assertThat(info.getDeleted(), is(1));
    }

    @Test
    public void when2Added2Changed4Deleted() {
        List<Analyze.User> previous = List.of(
                new Analyze.User(1, "A"),
                new Analyze.User(2, "B"),
                new Analyze.User(3, "C"),
                new Analyze.User(4, "D"),
                new Analyze.User(5, "E"),
                new Analyze.User(6, "F"),
                new Analyze.User(7, "G"),
                new Analyze.User(8, "H"),
                new Analyze.User(9, "I"),
                new Analyze.User(10, "J")
        );
        List<Analyze.User> current = List.of(
                new Analyze.User(1, "A"),
                new Analyze.User(2, "B"),
                new Analyze.User(11, "K"),
                new Analyze.User(5, "EE"),
                new Analyze.User(12, "L"),
                new Analyze.User(7, "G"),
                new Analyze.User(8, "HH"),
                new Analyze.User(10, "J")
        );
        Analyze.Info info = new Analyze().diff(previous, current);
        assertThat(info.getAdded(), is(2));
        assertThat(info.getChanged(), is(2));
        assertThat(info.getDeleted(), is(4));
    }
}
