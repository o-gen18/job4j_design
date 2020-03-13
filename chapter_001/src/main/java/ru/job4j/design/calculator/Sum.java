package ru.job4j.design.calculator;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Sum implements UserChoice{

    /**
     * Names this particular implementation.
     * @return name of the action performed by this class.
     */
    @Override
    public String name() {
        return " + ";
    }

    /**
     * Performs the adding up action.
     * @param input
     * @param calculator
     * @param output
     * @return true
     */
    @Override
    public boolean execute(double first, CalcInput input, Calculator calculator, Consumer<String> output) {
        double second = input.askDouble("Прибавить: ");
        double result = calculator.add(first, second);
        CalcStartUI.setBuffer(result);
        output.accept("Ответ: " + first + " + " + second + " = " + result + System.lineSeparator());
        return true;
    }
}
