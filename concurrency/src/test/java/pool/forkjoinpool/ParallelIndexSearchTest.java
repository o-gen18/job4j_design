package pool.forkjoinpool;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ParallelIndexSearchTest {

    /** @noinspection checkstyle:VisibilityModifier*/
    public static User[] array;

    @Before
    public void init() {
        array = new User[]{new User("A"), new User("B"),
                new User("C"), new User("D"),
                new User("E"), new User("F"),
                new User("G"), new User("H"),
                new User("I"), new User("J"),
                new User("K"), new User("L"),
                new User("M"), new User("N"),
                new User("O"), new User("P"),
                new User("Q"), new User("R"),
                new User("S"), new User("T"),
                new User("U"), new User("V"),
                new User("W"), new User("X"),
                new User("Y"), new User("Z")};
    }

    @Test
    public void whenSearchIndexOfZThen25() {
        assertThat(
                ParallelIndexSearch.findIndex(array, new User("Z")), is(25));

    }

    @Test
    public void whenSearchIndexOfEThen4() {
        assertThat(
                ParallelIndexSearch.findIndex(array, new User("E")), is(4));

    }

    @Test
    public void whenSearchIndexOfCyrillicThenNegativeOne() {
        assertThat(
                ParallelIndexSearch.findIndex(array, new User("Ж")), is(-1));
    }
}
