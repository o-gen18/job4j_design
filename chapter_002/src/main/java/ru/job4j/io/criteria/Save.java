package ru.job4j.io.criteria;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class Save implements Saver {
    @Override
    public void writeToFile(List<Path> wantedFiles, Writer writer) throws IOException {
        for (Path file : wantedFiles) {
            writer.write(file.toString() + System.lineSeparator());
        }
    }
}
