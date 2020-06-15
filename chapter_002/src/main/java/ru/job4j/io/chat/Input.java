package ru.job4j.io.chat;

import java.io.BufferedReader;
import java.io.IOException;

public interface Input {
    String takeInput(BufferedReader reader) throws IOException;
}
