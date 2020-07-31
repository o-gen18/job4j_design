package ru.job4j.design.game;

import java.util.Random;

public class Arrow implements Steps {
    /**
     * Returns steps to go imitating a playing arrow.
     *
     * @return int.
     */
    @Override
    public int steps() {
        return new Random().nextInt(21);
    }

    @Override
    public String name() {
        return "Стрелка";
    }
}
