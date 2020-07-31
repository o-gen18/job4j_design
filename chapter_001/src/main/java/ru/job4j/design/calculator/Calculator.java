package ru.job4j.design.calculator;

public class Calculator {
    /**
     * Sums up the two given arguments;
     *
     * @param first
     * @param second
     * @return the result of sum.
     */
    public double add(double first, double second) {
        return first + second;
    }

    /**
     * Divides the first argument by the second one.
     *
     * @param first
     * @param second
     * @return the result of division.
     */
    public double divide(double first, double second) {
        return first / second;
    }

    /**
     * Multiplies two given arguments;
     *
     * @param first
     * @param second
     * @return the result of multiplication.
     */
    public double multiply(double first, double second) {
        return first * second;
    }

    /**
     * Subtracts the second argument from the first one.
     *
     * @param first
     * @param second
     * @return the result of subtraction.
     */
    public double subtract(double first, double second) {
        return first - second;
    }

    public double sinus(double angle) {
        return Math.sin(angle);
    }

    public double cosinus(double angle) {
        return Math.cos(angle);
    }
}
