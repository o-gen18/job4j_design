package ru.job4j.design.products;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
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


    public void allocate(Set<Food> foodPack) {
        for (Food food : foodPack) {
            if (shop.accept(food)) {
                shop.add(food);
            } else if (warehouse.accept(food)) {
                warehouse.add(food);
            } else if (trash.accept(food)) {
                trash.add(food);
            }
        }
    }

    public void resort() {
        Set<Food> retaken = new HashSet<>();
        Set<Storage> storages = Set.of(shop, warehouse, trash);
        Iterator<Food> foodIterator;
        for (Storage storage : storages) {
            foodIterator = storage.getPack().iterator();
            while (foodIterator.hasNext()) {
                retaken.add(foodIterator.next());
                foodIterator.remove();
            }
        }
        allocate(retaken);
    }
}
