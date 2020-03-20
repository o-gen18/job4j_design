package ru.job4j.design.icp.menu;

import java.util.function.Consumer;

public class ExitAction implements Action {
    @Override
    public String name() {
        return "~~~ Выход ~~~";
    }

    @Override
    public boolean execute(Input input, Menu menu, Consumer<String> output) {
        return false;
    }
}
