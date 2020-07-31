package ru.job4j.design.calculator;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface UserChoice {

    /**
     * Abstract method that names whatever action;
     *
     * @return string representation of action.
     */
    String name();

    /**
     * Abstract method that performs a certain action of Calculator
     * based on the chosen option in CalcInput and finally passes the
     * result of the calculation to Consumer.
     *
     * @param input
     * @param calculator
     * @param output
     * @return true if the operation succeeded, otherwise false.
     */
    boolean execute(double first, CalcInput input, Calculator calculator, Consumer<String> output);
}
