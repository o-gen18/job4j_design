package ru.job4j.design.icp.menu;

import java.util.function.Consumer;

public class CreateAction implements Action {
    @Override
    public String name() {
        return "~~~ Добавить пункт ~~~";
    }

    @Override
    public boolean execute(Input input, Menu menu, Consumer<String> output) {
        String name = input.askStr("Введите название пункта: ");
        Paragraph paragraph = new Paragraph(name);
        menu.add(paragraph);
        output.accept("Пункт добавлен");
        return true;
    }
}
