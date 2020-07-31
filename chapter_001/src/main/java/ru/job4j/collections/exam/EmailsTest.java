package ru.job4j.collections.exam;

import org.junit.Test;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EmailsTest {
    @Test
    public void whenAddSeveralUsersWithIntersectionedURLSThenMerge() {
        Emails emails = new Emails();
        Map<Emails.User, Set<String>> map = Map.of(
                new Emails.User("Petr", Set.of("URL1", "URL2", "URL3")),
                Set.of("URL1", "URL2", "URL3"),
                new Emails.User("Oleg", Set.of("URL4", "URL5")), Set.of("URL4", "URL5"),
                new Emails.User("Ivan", Set.of("URL6", "URL1")), Set.of("URL6", "URL1"),
                new Emails.User("Den", Set.of("URL7", "URL5")), Set.of("URL7", "URL5"));
        Map<Emails.User, Set<String>> expected = Map.of(
                new Emails.User("Petr", Set.of("URL1", "URL2", "URL3", "URL6")),
                Set.of("URL1", "URL2", "URL3", "URL6"),
                new Emails.User("Oleg", Set.of("URL4", "URL5", "URL7")),
                Set.of("URL4", "URL5", "URL7"));
        Map<Emails.User, Set<String>> result = emails.mergeIfCommonURL(map);
        assertThat(result, is(expected));
    }
}
