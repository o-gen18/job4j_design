package ru.job4j.design.game;

public interface Input {
    String askStr(String question);

    int askInt(String question);

    int askInt(String question, int max);
}
