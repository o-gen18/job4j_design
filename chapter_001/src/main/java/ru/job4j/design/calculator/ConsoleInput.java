package ru.job4j.design.calculator;

import java.util.Scanner;

public class ConsoleInput implements CalcInput {
    /**
     * Creating the field Scanner and passing the standard input stream inside its constructor;
     * Now it's ready to supply next methods with input data.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Returns input data straight after printing the question asked.
     *
     * @param question
     * @return the input.
     */
    @Override
    public String askStr(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Returns Double-type representation of the input taken from the previous method.
     *
     * @param question
     * @return integer value.
     */
    public double askDouble(String question) {
        return Double.parseDouble(askStr(question));
    }

    /**
     * Asks for position of certain operation from provided list.
     *
     * @param select offers to the user to select anything from menu.
     * @param max    takes number of all operations from the array.
     * @return int that stands for index of certain operation in a list.
     */
    public int askChoice(String select, int max) {
        int chosen = (int) askDouble(select);
        if (!(chosen >= 0 && chosen < max)) {
            throw new IllegalStateException(String.format(
                    "Out of bounds: %s > [0, %s]", chosen, max));
        }
        return chosen;
    }
}
