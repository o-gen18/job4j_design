package ru.job4j.design.crosszeros;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String askStr(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    @Override
    public int askChoice(String question) {
        int result = -1;
        boolean invalid = true;
        do {
            try {
                result = Integer.parseInt(askStr(question));
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Введите число!");
            }
        } while (invalid);
        return result;
    }

    @Override
    public int askInt(String question, int max) {
        int selected = askChoice(question);
        if (!(selected >= 0 && selected < max)) {
            throw new IllegalStateException(String.format(
                    "Out of limit %s > [0, %s]", selected, max));
        }
        return selected;
    }

    @Override
    public Symbol askSymbol(String question) {
        Symbol symbolX = new SymbolX();
        Symbol symbolO = new SymbolO();
        Symbol result = null;
        System.out.println(question);
        char input = scanner.nextLine().charAt(0);
        if (symbolX.verify(input)) {
            result = symbolX;
        } else if (symbolO.verify(input)) {
            result = symbolO;
        } else {
            throw new NumberFormatException("Ввести нужно либо 'X', либо 'O'!");
        }
        return result;
    }
}
