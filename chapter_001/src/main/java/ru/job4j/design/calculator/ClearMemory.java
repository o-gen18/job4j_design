package ru.job4j.design.calculator;

import java.util.function.Consumer;

public class ClearMemory implements UserChoice {
    @Override
    public String name() {
        return "Очистить память";
    }

    @Override
    public boolean execute(double first, CalcInput input, Calculator calculator, Consumer<String> output) {
        CalcStartUI.setRunBuffer(false);
        return true;
    }
}
