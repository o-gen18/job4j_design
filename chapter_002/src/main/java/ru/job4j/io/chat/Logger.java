package ru.job4j.io.chat;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Logger implements Saver {

    private List<String> history = new ArrayList<>();

    private Calendar time = Calendar.getInstance();

    private String fileToLog;

    public Logger(String file) {
        fileToLog = file;
    }

    @Override
    public void collect(String phrase) {
        StringBuilder now = new StringBuilder(time.getTime().toString());
        now.append("---").append(phrase).append(System.lineSeparator());
        history.add(now.toString());
    }

    @Override
    public void writeToLog() {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(fileToLog)))) {
            out.write(history.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
