package ru.job4j.io.criteria;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public interface Searcher {
    Predicate<Path> getSearchMode(String mode, String name);

    List<Path> search(String directory, String name, String mode) throws IOException;
}
