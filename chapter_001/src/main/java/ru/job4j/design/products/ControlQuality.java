package ru.job4j.design.products;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.YEAR;

public class ControlQuality {
    /**
     * This field is created to compare with food's dates.
     */
    private Calendar now = Calendar.getInstance();
    private Set<Food> foodPack = new HashSet<>();

    static Shop shop = new Shop();
    static Warehouse warehouse = new Warehouse();
    static Trash trash = new Trash();

    public ControlQuality(Set<Food> food) {
        if (food != null) {
            this.foodPack.addAll(food);
        }
    }

    /**
     * Getter and Setter of Calendar-now instance for testing;
     * @return
     */
    public Calendar getNow() {
        return now;
    }

    public void setNow(Calendar calendar) {
        this.now = calendar;
    }

    /**
     * Calculates the percentage value of food's expiry date.
     * @param food
     * @return int number meaning percents.
     */
    public int expiryPercentage(Food food) {
        return (int) (100 * (now.getTimeInMillis() - food.getCreateDate().getTimeInMillis()) /
                (food.getExpiryDate().getTimeInMillis() - food.getCreateDate().getTimeInMillis()));
    }

    public void allocate() {
        for (Food food : foodPack) {
            if (now.getTimeInMillis() > food.getExpiryDate().getTimeInMillis()) {
                trash.add(food);
            } else if (expiryPercentage(food) >= 75) {
                food.setDiscount("50%");
                shop.add(food);
            } else if (expiryPercentage(food) < 75 && expiryPercentage(food) >= 25) {
                shop.add(food);
            } else if (expiryPercentage(food) < 25) {
                warehouse.add(food);
            }
        }
    }
}
