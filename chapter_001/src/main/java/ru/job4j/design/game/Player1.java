package ru.job4j.design.game;

public class Player1 implements Player {
    private int stepsDone = 0;

    @Override
    public String name() {
        return "Игрок 1";
    }

    @Override
    public int go(Square square, Steps steps) {
        int result = -1;
        int thisStep = steps.steps();
        result = stepsDone + thisStep;
        if (stepsDone >= square.getTotalLength()) {
            result = -1;
            System.out.println("Игрок " + name() + " дошёл до окнца и победил!");
        } else {
            System.out.println("Игрок " + name() + " сделал" + result + " шагов");
        }
        return result;
    }

    @Override
    public int showStepsDone() {
        return stepsDone;
    }
}
