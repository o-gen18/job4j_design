package ru.job4j.design.calculator;

import java.util.function.Consumer;

public class Division implements UserChoice {
    /**
     * Names this particular implementation.
     * @return name of the action performed by this class.
     */
    @Override
    public String name() {
        return " / ";
    }

    /**
     * Performs the division of two arguments.
     * @param input
     * @param calculator
     * @param output
     * @return true
     */
    @Override
    public boolean execute(double first, CalcInput input, Calculator calculator, Consumer<String> output) {
        double second = input.askDouble("Разделить на: ");
        double result = calculator.divide(first, second);
        CalcStartUI.Buffer.setBuffer(result);
        output.accept("Ответ: " + first + " / " + second + " = " + result + System.lineSeparator());
        return true;
    }
}
