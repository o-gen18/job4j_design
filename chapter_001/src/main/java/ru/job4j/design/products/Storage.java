package ru.job4j.design.products;

import java.util.Set;

public interface Storage {
    boolean accept(Food food);

    void add(Food food);

    Set<Food> getPack();
}
