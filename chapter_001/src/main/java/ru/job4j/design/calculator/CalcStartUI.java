package ru.job4j.design.calculator;

import java.util.ArrayList;
import java.util.function.Consumer;

public class CalcStartUI {
    /**
     * Static field used to indicate if the previous operations can be used next.
     */
    private static boolean runBuffer;

    /**
     * Static field used to store temporary values got from any
     * class that implement abstract method "execute" in UserChoice interface.
     */
    private static double buffer;

    public static void main(String[] args) {
        CalcInput input = new ConsoleInput();
        Calculator calculator = new Calculator();
        ArrayList<UserChoice> actions = new ArrayList<UserChoice>();
        actions.add(new Sum());
        actions.add(new Subtraction());
        actions.add(new Division());
        actions.add(new Multiplication());
        actions.add(new Sinus());
        actions.add(new Cosinus());
        actions.add(new ClearMemory());
        actions.add(new Exit());
        new CalcStartUI().init(input, calculator, actions, System.out::println);
    }

    public static void setBuffer(Double newBuffer) {
        buffer = newBuffer;
    }

    public static double getBuffer() { return buffer; }

    public static void setRunBuffer(boolean run) {
        runBuffer = run;
    }

    public void init(CalcInput input, Calculator calculator, ArrayList<UserChoice> actions, Consumer<String> output) {
        boolean runCalc = true;
        while (runCalc) {
            buffer = input.askDouble("Введите число: ");
            this.runBuffer = true;
            while (runBuffer) {
                this.showMenu(actions);
                int choice = input.askChoice("", actions.size());
                UserChoice selected = actions.get(choice);
                runCalc = selected.execute(buffer, input, calculator, output);
                if(runCalc == false) break;
            }
        }
    }

    public void showMenu(ArrayList<UserChoice> actions) {
        System.out.println("Выберите действие: ");
        for (UserChoice choice : actions) {
            System.out.println(actions.indexOf(choice) + ". " + choice.name());
        }
    }
}
