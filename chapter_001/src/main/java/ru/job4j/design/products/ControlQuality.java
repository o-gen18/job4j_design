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

    private Set<Storage> storages = Set.of(new Shop(), new Warehouse(), new Trash());

//    private Shop shop = new Shop();
//    private Warehouse warehouse = new Warehouse();
//    private Trash trash = new Trash();

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
        Shop searched = null;
        for (Iterator<Storage> it = storages.iterator(); it.hasNext(); ) {
            Storage shop = it.next();
            if (shop.getClass().equals(Shop.class)){
                searched = (Shop) shop;
            }
        }
        return searched;
    }

    public Warehouse getWarehouse() {
        Warehouse searched = null;
        for (Iterator<Storage> it = storages.iterator(); it.hasNext(); ) {
            Storage warehouse = it.next();
            if (warehouse.getClass().equals(Warehouse.class)){
                searched = (Warehouse) warehouse;
            }
        }
        return searched;
    }

    public Trash getTrash() {
        Trash searched = null;
        for (Iterator<Storage> it = storages.iterator(); it.hasNext(); ) {
            Storage trash = it.next();
            if (trash.getClass().equals(Trash.class)){
                searched = (Trash) trash;
            }
        }
        return searched;
    }


    public void allocate(Set<Food> foodPack) {
        for (Food food : foodPack) {
            if (getShop().accept(food)) {
                getShop().add(food);
            } else if (getWarehouse().accept(food)) {
                getWarehouse().add(food);
            } else if (getTrash().accept(food)) {
                getTrash().add(food);
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
