package ru.job4j.design.icp.menu;

import java.util.function.Consumer;

public class DepictAction implements Action {
    @Override
    public String name() {
        return "~~~ Отобразить меню ~~~";
    }

    @Override
    public boolean execute(Input input, Menu menu, Consumer<String> output) {
        menu.showAll();
        return true;
    }
}
