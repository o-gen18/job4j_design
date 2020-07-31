package ru.job4j.design.icp.menu;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ExtendAction implements Action {
    @Override
    public String name() {
        return "~~~ Добавить подпункт ~~~";
    }

    @Override
    public boolean execute(Input input, Menu menu, Consumer<String> output) {
        menu.showAll();
        ArrayList<Paragraph> list = menu.returnList();
        int number = input.askInt("Какой пункт расширить?", list.size());
        String prefix = list.get(number).getPrefix();
        int sub = list.get(number).getSubParagraphs();
        list.get(number).iterateSubParagraphs();
        String name = input.askStr("Введите название подпункта: ");
        Paragraph paragraph = new Paragraph();
        paragraph.setPrefix(prefix + "---");
        paragraph.setName(paragraph.getPrefix() + name);
        menu.add(number + 1 + sub, paragraph);
        //As the ArrayList is used to store paragraphs,
        // we need to insert the subParagraph one item ahead plus quantity of existing ones.
        output.accept("Пункт расширен");
        return true;
    }
}
