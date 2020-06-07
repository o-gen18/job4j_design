package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;
import java.util.StringJoiner;

public class AnalyzeTest2 {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenFindUnavailableDiapasonsThenWriteToFile() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("unavailable.scv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01" + System.lineSeparator()
                    + "500 10:57:01" + System.lineSeparator()
                    + "400 10:58:01" + System.lineSeparator()
                    + "200 10:59:01" + System.lineSeparator()
                    + "500 11:01:02" + System.lineSeparator()
                    + "200 11:02:02" + System.lineSeparator());
        }
        new Analyze().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::add);
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01" + System.lineSeparator()
                                            + "11:01:02;11:02:02"));
    }
}
