package ru.job4j.design.crosszeros;

public class OneVictoryDifficulty implements Difficulty {
    private int victories = 0;

    @Override
    public void iterate() {
        victories++;
    }

    @Override
    public String name() {
        return "Одна победа";
    }

    @Override
    public boolean victory() {
        return victories == 1;
    }
}
