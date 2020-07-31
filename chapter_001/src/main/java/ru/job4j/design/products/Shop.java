package ru.job4j.design.products;

import java.util.HashSet;
import java.util.Set;

public class Shop implements Storage {
    private Set<Food> pack = new HashSet<>();

    public Set<Food> getPack() {
        return pack;
    }

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        if (food.getExpiryPercentage() >= 75) {
            food.setDiscount("50%");
            result = true;
        } else if (food.getExpiryPercentage() < 75 && food.getExpiryPercentage() >= 25) {
            result = true;
        }
        return result;
    }

    @Override
    public void add(Food food) {
        pack.add(food);
    }
}
