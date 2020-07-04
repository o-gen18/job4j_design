package ru.job4j.io.chat;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleInput implements Input {
    @Override
    public String takeInput(BufferedReader reader) throws IOException {
        return reader.readLine();
    }
}
