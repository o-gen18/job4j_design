package ru.job4j.softreference;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


public class Cache {

    private Map<String, SoftReference<String>> cache = new HashMap<>();

    public String get(String file) {
        SoftReference<String> finding = cache.get(file);
        if (finding != null) {
            return finding.get();
        } else {
            String result = null;
            try {
                result = String.join(System.lineSeparator(), Files.readAllLines(Path.of("C:\\Projects\\job4j_design\\garbage_collection\\src\\main\\resources", file)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            cache.put(file, new SoftReference<>(result));
            return result;
        }
    }

    public static void main(String[] args) {
        Cache cache = new Cache();
        String names = cache.get("Names.txt");
        String addresses = cache.get("Addresses.txt");
        System.out.println(names);
        System.out.println();
        System.out.println(addresses);
    }
}
