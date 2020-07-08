package ru.job4j.io.criteria;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

public class Start {
    public static void main(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException(
                    "Введите необходимые ключи:" + System.lineSeparator()
            + "-d   директория, в которой начинать поиск" + System.lineSeparator()
            + "-n   имя файла, маска, либо регулярное выражение" + System.lineSeparator()
            + "-m   искать по маске файла (-f полному совпадению,"
                             + " -r регулярному выражению)" + System.lineSeparator()
            + "-o   путь, по которому записать в файл");
        }
        new Start().run(args);
    }

    public void run(String[] args) {
        Searcher searcher = new Find();
        List<Path> wantedFiles = null;

        try {
            wantedFiles = searcher.search(args[0], args[1], args[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Saver saver = new Save();

        String target = args[3].replace("-o=", "");

        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            saver.writeToFile(wantedFiles, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
