package ru.job4j.design.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SubParagraphLeaf implements MenuComponent {
    private List<MenuComponent> subSubParagraphs = new ArrayList<MenuComponent>();

    private String name;

    public SubParagraphLeaf(String name) {
        this.name = name;
    }

    @Override
    public void add(MenuComponent subSubParagraph) {
        subSubParagraphs.add(subSubParagraph);
    }

    @Override
    public void remove(MenuComponent subSubParagraph) {
        subSubParagraphs.remove(subSubParagraph);
    }

    @Override
    public MenuComponent getChild(int i) {
        return subSubParagraphs.get(i);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void print() {
        System.out.print("--- ");
        System.out.println(getName());

        Iterator<MenuComponent> menuIterator = subSubParagraphs.iterator();
        while (menuIterator.hasNext()) {
            MenuComponent comp = menuIterator.next();
            comp.print();
        }
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder("--- ");
        text.append(getName()).append(System.lineSeparator());

        Iterator<MenuComponent> menuIterator = subSubParagraphs.iterator();
        while (menuIterator.hasNext()) {
            MenuComponent comp = menuIterator.next();
            text.append(comp.toString());
        }
        return text.toString();
    }
}
