package ru.job4j.io.archive;

import ru.job4j.io.searcher.Search;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;



public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgZip argZip = new ArgZip(args);
        String exclude = argZip.exclude();
        String directory = argZip.directory();
        String output = argZip.output();
        Path root = Paths.get(directory);
        List<Path> filesToPackIntoZip = new ArrayList<>();
        try {
            filesToPackIntoZip = Search.search(root, exclude);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<File> files = new ArrayList<>();
        for (Path path : filesToPackIntoZip) {
            files.add(path.toFile());
        }
        new Zip().packFiles(files, new File(output));
    }

}
