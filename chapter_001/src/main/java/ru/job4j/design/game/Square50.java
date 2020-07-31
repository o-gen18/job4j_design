package ru.job4j.design.game;

import java.util.Random;

/**
 * An instance of this class stands for a playing square with 50 steps in total.
 */

public class Square50 implements Square {
    private final int[] magicSquares;
    private final int length = 50;

    public Square50() {
        Random random = new Random();
        int[] magic = new int[7];
        for (int i = 0; i < 7; i++) {
            magic[i] = random.nextInt(50);
        }
        magicSquares = magic;
    }

    @Override
    public boolean magicPosition(int position) {
        boolean result = false;
        for (int i : magicSquares) {
            if (position == i) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public String turn() {
        return null;
    }

    @Override
    public int getTotalLength() {
        return length;
    }

    @Override
    public String name() {
        return "Игровое поле на " + getTotalLength() + " шагов";
    }
}
