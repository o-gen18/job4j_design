package ru.job4j.design.products;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class Warehouse implements Storage {
    private Set<Food> pack = new HashSet<>();

    public Set<Food> getPack() {
        return pack;
    }

    public void allocate(Set<Food> foodPack, ControlQuality control) {
        for (Food food : foodPack) {
            if (control.expiryPercentage(food) < 25 && control.expiryPercentage(food) > 0) {
                pack.add(food);
            }
        }
    }
}
