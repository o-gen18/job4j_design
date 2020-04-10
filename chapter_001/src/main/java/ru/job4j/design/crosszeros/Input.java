package ru.job4j.design.crosszeros;

public interface Input {
    String askStr(String question);

    int askChoice(String question);

    int askInt(String question, int max);

    Symbol askSymbol(String question);
}
