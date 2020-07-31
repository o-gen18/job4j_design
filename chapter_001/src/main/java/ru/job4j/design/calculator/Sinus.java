package ru.job4j.design.calculator;

import java.util.function.Consumer;

public class Sinus implements UserChoice {
    @Override
    public String name() {
        return "Найти синус угла в " + CalcStartUI.Buffer.getBuffer() + " радиан";
    }

    @Override
    public boolean execute(double first, CalcInput input,
                           Calculator calculator, Consumer<String> output) {
        double result = Math.sin(first);
        CalcStartUI.Buffer.setBuffer(result);
        output.accept("Синус угла " + first + " равен " + result);
        return true;
    }
}
