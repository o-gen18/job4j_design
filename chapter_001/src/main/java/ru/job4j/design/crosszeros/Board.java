package ru.job4j.design.crosszeros;

public interface Board {
    String name();

    int length();

    void printBoard();

    void makeStep(int row, int cell, Symbol symbol);

    boolean isEmpty(int row, int cell, char[][] board);

    boolean isWin(Symbol symbol);

    boolean repeatingSymbol(Symbol symbol);
}
