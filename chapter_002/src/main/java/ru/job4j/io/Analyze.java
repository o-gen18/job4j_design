package ru.job4j.io;

import java.io.*;
import java.util.StringJoiner;

public class Analyze {

    public void unavailable(String source, String target) {
        StringJoiner result = new StringJoiner("");
        try (BufferedReader read = new BufferedReader(new FileReader(source));
            PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            read.lines().forEach(line -> {
                char latestChar = '\u0000';
                if (result.length() > 0) {
                    latestChar = result.toString().charAt(result.toString().length() - 1);
                }
                if ((line.startsWith("400") || line.startsWith("500"))&&(latestChar != ';')) {
                    result.add(line.substring(4) + ";");
                } else if ((line.startsWith("200") || line.startsWith("300"))&&(latestChar == ';')) {
                    result.add(line.substring(4) + System.lineSeparator());
                }
            });
            out.write(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * Returns a String representation of the result produced by method "unavailable"
    * to check it in the test class. (Add some words to commit again)
     */

    public String readResult(String path) {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines().forEach(result::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
