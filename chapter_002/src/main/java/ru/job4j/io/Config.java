package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(line -> {
                        if (!line.isEmpty()) {
                            if (line.contains("/")) {
                                line = line.substring(0, line.indexOf("/"));
                            }
                            if (!line.isEmpty()) {
                                values.put(line.substring(0, line.indexOf("=")), line.substring(line.indexOf("=")+1));
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void load() { //пытался сделать так, но постоянно был IndexOfBoundsException
//        StringJoiner intoValues = new StringJoiner(System.lineSeparator());
//        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
//            read.lines().map(line -> line.replace(" ", ""))
//                        .map(line -> line.substring(0, line.indexOf("/")))
//                                .forEach(line -> values.put(line.substring(0, line.indexOf("=")), line.substring(line.indexOf("=")+1, line.length()-1)));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
