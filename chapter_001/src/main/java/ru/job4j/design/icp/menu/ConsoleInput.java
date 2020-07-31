package ru.job4j.design.icp.menu;

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
        return Integer.parseInt(askStr(question));
    }

    @Override
    public int askInt(String question, int max) {
        int select = askChoice(question);
        if (!(select >= 0 && select < max)) {
            throw new IllegalStateException(String.format(
                    "Out of bounds %s > [0, %s]", select, max));
        }
        return select;
    }
}
