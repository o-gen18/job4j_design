package ru.job4j.io.criteria;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;

public interface Saver  {
    void writeToFile(List<Path> wantedFiles, Writer writer) throws IOException;
}
