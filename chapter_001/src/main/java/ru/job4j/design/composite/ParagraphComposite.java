package ru.job4j.design.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParagraphComposite implements MenuComponent {

    private List<MenuComponent> subParagraphs = new ArrayList<MenuComponent>();

    private String name;

    public ParagraphComposite(String name) {
        this.name = name;
    }

    @Override
    public void add(MenuComponent subParagraph) {
        subParagraphs.add(subParagraph);
    }

    @Override
    public void remove(MenuComponent subParagraph) {
        subParagraphs.remove(subParagraph);
    }

    @Override
    public MenuComponent getChild(int i) {
        return subParagraphs.get(i);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void print() {
        System.out.println(getName());

        Iterator<MenuComponent> menuIterator = subParagraphs.iterator();
        while (menuIterator.hasNext()) {
            MenuComponent subParagraph = menuIterator.next();
            subParagraph.print();
        }
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append(getName()).append(System.lineSeparator());

        Iterator<MenuComponent> menuIterator = subParagraphs.iterator();
        while (menuIterator.hasNext()) {
            MenuComponent comp = menuIterator.next();
            text.append(comp.toString());
        }
        return text.toString();
    }
}
