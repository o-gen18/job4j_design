package ru.job4j.design.icp.menu;

import java.util.function.Consumer;

public interface Action {
    String name();
    boolean execute(Input input, Menu menu, Consumer<String> output);
}
