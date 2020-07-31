package ru.job4j.design.game;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String askStr(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    @Override
    public int askInt(String question) {
        return Integer.parseInt(askStr(question));
    }

    @Override
    public int askInt(String question, int max) {
        int selected = askInt(question);
        if (!(selected >= 0 && selected < max)) {
            throw new IllegalStateException(String.format(
                    "Out of bounds %s > [0, %s]", selected, max));
        }
        return selected;
    }
}
