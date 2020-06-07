package ru.job4j.io;

import java.io.*;
import java.util.StringJoiner;

public class Analyze {

    public void unavailable(String source, String target) {
        StringJoiner serverLog = new StringJoiner(System.lineSeparator());
        StringJoiner result = new StringJoiner("");
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            read.lines().forEach(serverLog::add);
            String[] array = serverLog.toString().split(System.lineSeparator());
            for (int i = 0; i < array.length; i++) {
                if (array[i].startsWith("400") || array[i].startsWith("500")) {
                    result.add(array[i].substring(4) + ";");
                    int k = i;
                    while (!array[k].startsWith("300") && !array[k].startsWith("200")) {
                        k++;
                    }
                    result.add(array[k].substring(4) + System.lineSeparator());
                    i = k;
                }
            }
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
