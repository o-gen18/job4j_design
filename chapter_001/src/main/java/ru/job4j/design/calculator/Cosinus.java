package ru.job4j.design.calculator;

import java.util.function.Consumer;

public class Cosinus implements UserChoice {
    @Override
    public String name() {
        return "Найти косинус угла в " + CalcStartUI.getBuffer() + " радиан";
    }

    @Override
    public boolean execute(double first, CalcInput input, Calculator calculator, Consumer<String> output) {
        double result = Math.cos(first);
        CalcStartUI.setBuffer(result);
        output.accept("Косинус угла " + first + " равен " + result);
        return true;
    }
}
