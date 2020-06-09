package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String [] args) {
        File file = new File("C:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Does not exist %s", file.getAbsoluteFile()));
        }
        if(!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Is not a directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("Directory - %s, size : %s", file.getAbsoluteFile(), file.getTotalSpace()));
    }
}
