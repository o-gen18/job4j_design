package ru.job4j.design.game;

import java.util.Random;

public class Dice implements Steps {
    /**
     * Imitates throwing of dice.
     *
     * @return a number from 0 to 5 meaning steps to go.
     */
    @Override
    public int steps() {
        return new Random().nextInt(13);
    }

    @Override
    public String name() {
        return "Кости";
    }
}
