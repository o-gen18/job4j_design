package ru.job4j.design.composite;

import java.util.Iterator;

public class SubSubParagraph implements MenuComponent {

    private String name;

    public SubSubParagraph(String name) {
        this.name = name;
    }

    @Override
    public void add(MenuComponent paragraph) {
    }

    @Override
    public void remove(MenuComponent paragraph) {
    }

    @Override
    public MenuComponent getChild(int i) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void print() {
        System.out.print("------ ");
        System.out.println(getName());
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder("------ ");
        text.append(getName()).append(System.lineSeparator());
        return text.toString();
    }
}
