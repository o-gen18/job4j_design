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

    private Shop shop = new Shop();
    private Warehouse warehouse = new Warehouse();
    private Trash trash = new Trash();

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

    public Shop getShop() {
        return shop;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Trash getTrash() {
        return trash;
    }

    /**
     * Calculates the percentage value of food's expiry date.
     * @param food
     * @return int number meaning percents.
     */
    public int expiryPercentage(Food food) {
        int percent = -1;
        if (food != null && !(getNow().getTimeInMillis() > food.getExpiryDate().getTimeInMillis())) {
            percent = (int) (100 * (now.getTimeInMillis() - food.getCreateDate().getTimeInMillis()) /
                    (food.getExpiryDate().getTimeInMillis() - food.getCreateDate().getTimeInMillis()));
        }
        return percent;
    }

    public void allocate(Set<Food> foodPack) {
        shop.allocate(foodPack, this);
        warehouse.allocate(foodPack, this);
        trash.allocate(foodPack, this);
    }
}
