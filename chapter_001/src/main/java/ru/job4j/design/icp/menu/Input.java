package ru.job4j.design.icp.menu;

public interface Input {
    String askStr(String question);

    int askChoice(String question);

    int askInt(String question, int max);
}
