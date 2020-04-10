package ru.job4j.design.crosszeros;

public interface Player {
    String name();
    Symbol go(Board board, Input input);
}
