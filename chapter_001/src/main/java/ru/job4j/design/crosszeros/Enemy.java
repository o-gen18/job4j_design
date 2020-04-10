package ru.job4j.design.crosszeros;

public interface Enemy extends Player {
    String name();
    Symbol go(Board board, Input input);
}
