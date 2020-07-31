package ru.job4j.design.calculator;

public interface CalcInput {

    /**
     * Returns input data straight after printing the question asked.
     *
     * @param question
     * @return the input.
     */
    String askStr(String question);

    /**
     * Asks for input numbers using previous method.
     *
     * @param question
     * @return
     */
    double askDouble(String question);

    /**
     * Asks for position of certain operation from provided list.
     *
     * @param select offers to the user to select anything from menu.
     * @param max    takes number of all operations from the array.
     * @return int that stands for index of certain operation in a list.
     */
    int askChoice(String select, int max);
}
