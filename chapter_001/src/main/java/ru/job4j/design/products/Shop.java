package ru.job4j.design.products;

import java.util.HashSet;
import java.util.Set;

public class Shop implements Storage {
    private Set<Food> pack = new HashSet<>();

    public Set<Food> getPack() {
        return pack;
    }

    @Override
    public void allocate(Set<Food> foodPack, ControlQuality control) {
        for (Food food : foodPack) {
            if (control.expiryPercentage(food) >= 75) {
                food.setDiscount("50%");
                pack.add(food);
            } else if (control.expiryPercentage(food) < 75 && control.expiryPercentage(food) >= 25) {
                pack.add(food);
            }
        }
    }
}
