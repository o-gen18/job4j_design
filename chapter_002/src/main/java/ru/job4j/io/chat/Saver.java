package ru.job4j.io.chat;

public interface Saver {
    void collect(String phrase);

    void writeToLog();
}
