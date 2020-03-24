package ru.job4j.design.products;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class Trash implements Storage {
    private Set<Food> pack = new HashSet<>();

    public Set<Food> getPack() {
        return pack;
    }

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        if (food.getExpiryDate().getTimeInMillis() <= Calendar.getInstance().getTimeInMillis()) {
            result = true;
        }
        return result;
    }

    @Override
    public void add(Food food) {
        pack.add(food);
    }
}
