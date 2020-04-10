package ru.job4j.design.crosszeros;

import java.util.Random;

public class ComputerEnemy implements Enemy {
    private Random random = new Random();

    @Override
    public String name() {
        return "Компьютер";
    }

    @Override
    public Symbol go(Board board, Input input) {
        System.out.println("Ходит " + name());
        Symbol[] symbols = {new SymbolX(), new SymbolO()};
        Symbol step = null;
        boolean invalid = true;
        do {
            try {
                int row = random.nextInt(board.length());
                int cell = random.nextInt(board.length());
                step = symbols[random.nextInt(2)];
                board.makeStep(row, cell, step);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Введите 'X' или 'O'");
            } catch (IllegalStateException ise) {
                System.out.println("Измените координаты или символ!");
            }
        } while (invalid);
        return step;
    }
}
