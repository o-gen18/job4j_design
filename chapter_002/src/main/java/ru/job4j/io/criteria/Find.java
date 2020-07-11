package ru.job4j.io.criteria;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Find implements Searcher {

    @Override
    public Predicate<Path> getSearchMode(String mode, String name) {
        Predicate<Path> condition = null;
        switch (mode) {
            case "-m":
                String mask = name.replace("-n=", "");
                String prefix = mask.startsWith("*") ? "*" : "";
                FileSystem fs = FileSystems.getDefault();
                PathMatcher matcher = fs.getPathMatcher("glob:" +  prefix + mask);
                condition = matcher::matches;
                break;
            case "-f":
                String full = name.replace("-n=", "");
                condition = path -> path.toString().equals(full);
                break;
            case "-r":
                String regex = name.replace("-n=", "");
                Pattern p = Pattern.compile(regex);
                condition = path -> p.matcher(path.toString()).matches();
                break;
            default:
                System.out.println("Введены неверные аргументы! Введите заново, и перезапустите.");
                System.exit(1);
                break;
        }
        return condition;
    }

    @Override
    public List<Path> search(String directory, String name, String mode) throws IOException {
        Path start = Paths.get(directory.replace("-d=", ""));
        Predicate<Path> searchMode = getSearchMode(mode, name);
        Visitor visitor = new Visitor(searchMode);
        Files.walkFileTree(start, visitor);
        return visitor.getWantedFiles();
    }
}
