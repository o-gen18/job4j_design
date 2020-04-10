package ru.job4j.design.crosszeros;

public class ThreeVictoryDifficulty implements Difficulty {
    private int victories = 0;

    @Override
    public void iterate() {
        victories++;
    }

    @Override
    public String name() {
        return "Три победы";
    }

    @Override
    public boolean victory() {
        return victories == 3;
    }
}
