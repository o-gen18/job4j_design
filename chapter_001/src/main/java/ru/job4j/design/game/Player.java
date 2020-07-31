package ru.job4j.design.game;

public interface Player {
    String name();

    int go(Square square, Steps steps);

    int showStepsDone();
}
