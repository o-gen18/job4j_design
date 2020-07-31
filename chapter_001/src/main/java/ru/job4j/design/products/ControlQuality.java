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

    private Set<Storage> storages = new HashSet<>();

    public ControlQuality(Storage... storages) {
        for (Storage storage : storages) {
            this.storages.add(storage);
        }
    }

    /**
     * Getter and Setter of Calendar-now instance for testing;
     *
     * @return
     */
    public Calendar getNow() {
        return now;
    }

    public void setNow(Calendar calendar) {
        this.now = calendar;
    }

    public void allocate(Set<Food> foodPack) {
        for (Food food : foodPack) {
            for (Storage storage : storages) {
                if (storage.accept(food)) {
                    storage.add(food);
                    break;
                }
            }
        }
    }

    public void resort() {
        Set<Food> retaken = new HashSet<>();
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
