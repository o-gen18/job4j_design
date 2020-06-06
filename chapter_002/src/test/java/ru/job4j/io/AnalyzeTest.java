package ru.job4j.io;

import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalyzeTest {
    @Test
    public void whenFindUnavailableDiapasons() {
        String serverLog = "./data/server.txt";
        Analyze analyst = new Analyze();
        analyst.unavailable(serverLog, "./data/unavailable.csv");
        String result = analyst.readResult("./data/unavailable.csv");
        assertThat(result, is(new StringJoiner(System.lineSeparator())
                .add("10:57:01;10:59:01")
                .add("11:01:02;11:02:02").toString()));
    }
}
