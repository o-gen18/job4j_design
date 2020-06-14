package ru.job4j.io.archive;

public class ArgZip {

    private final String[] args;

    private boolean valid() {
        int counter = 0;
        for (String arg : args) {
            if (arg.contains("-d") || arg.contains("-e") || arg.contains("-o")) {
                counter++;
            }
        }
        return counter == 3;
    }

    private String findByKey(String key) {
        if (!valid()) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        String result = null;
        for (String arg : args) {
            if (arg.startsWith(key)) {
                result = arg.substring(arg.indexOf("=") + 1);
            }
        }
        return result;
    }

    public ArgZip(String[] args) {
        this.args = args;
    }

    public String directory() {
        return findByKey("-d");
    }

    public String exclude() {
        return findByKey("-e");
    }

    public String output() {
        return findByKey("-o");
    }
}
