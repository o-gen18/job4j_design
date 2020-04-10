package ru.job4j.design.crosszeros;

public class SecondPlayer implements Enemy {
    @Override
    public String name() {
        return "Второй игрок";
    }

    @Override
    public Symbol go(Board board, Input input) {
        System.out.println("Ходит " + name());
        Symbol step = null;
        boolean invalid = true;
        do {
            try {
                int row = input.askInt("Выберете координату Y: ", board.length());
                int cell = input.askInt("Выберете координату X: ", board.length());
                step = input.askSymbol("Введите 'X' или 'O'");
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
