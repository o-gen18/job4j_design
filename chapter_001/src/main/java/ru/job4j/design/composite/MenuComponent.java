package ru.job4j.design.composite;

public interface MenuComponent {
    void add(MenuComponent paragraph);
    public void remove(MenuComponent paragraph);
    MenuComponent getChild(int i);
    String getName();
    void print();
}
