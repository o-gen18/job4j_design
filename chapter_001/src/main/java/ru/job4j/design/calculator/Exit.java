package ru.job4j.design.calculator;

import java.util.function.Consumer;

public class Exit implements UserChoice {
    @Override
    public String name() {
        return "Выход";
    }

    @Override
    public boolean execute(double first, CalcInput input, Calculator calculator, Consumer<String> output) {
        return false;
    }
}
