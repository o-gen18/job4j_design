package ru.job4j.design.products;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class Warehouse {
    private Set<Food> pack = new HashSet<>();

    public Warehouse() {
    }

    public Warehouse(Set<Food> foodPack) {
        pack.addAll(foodPack);
    }

    public void add(Food food) {
        pack.add(food);
    }

    public Set<Food> getPack() {
        return pack;
    }
}
