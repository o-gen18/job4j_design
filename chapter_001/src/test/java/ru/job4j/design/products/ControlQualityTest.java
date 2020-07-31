package ru.job4j.design.products;

import org.junit.Test;

import java.util.Calendar;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ControlQualityTest {
    @Test
    public void whenAllocateProducts() {
        Calendar january20 = Calendar.getInstance();
        january20.set(2020, Calendar.JANUARY, 15);
        Calendar november19 = Calendar.getInstance();
        november19.set(2019, Calendar.NOVEMBER, 15);
        Calendar february20 = Calendar.getInstance();
        february20.set(2020, Calendar.FEBRUARY, 15);
        Calendar march20 = Calendar.getInstance();
        march20.set(2020, Calendar.MARCH, 15);
        Calendar april20 = Calendar.getInstance();
        april20.set(2020, Calendar.APRIL, 5);
        Calendar may20 = Calendar.getInstance();
        may20.set(2020, Calendar.MAY, 15);
        Calendar june20 = Calendar.getInstance();
        june20.set(2020, Calendar.JUNE, 15);

        Set<Food> food = Set.of(new Food("milkRotten", november19, february20, 60, "0"),
                new Food("milkOld", january20, april20, 60, "0"),
                new Food("milkFresh", february20, may20, 60, "0"),
                new Food("milkJustMade", march20, june20, 60, "0"));
        Shop shop = new Shop();
        Warehouse warehouse = new Warehouse();
        Trash trash = new Trash();
        ControlQuality control = new ControlQuality(shop, warehouse, trash);
        control.allocate(food);
        Set<Food> expectedShop = Set.of(new Food("milkOld", january20, april20, 60, "50%"),
                new Food("milkFresh", february20, may20, 60, "0"));
        Set<Food> expectedWarehouse = Set.of(new Food("milkJustMade", march20, june20, 60, "0"));
        Set<Food> expectedTrash = Set.of(new Food("milkRotten", november19, february20, 60, "0"));

        Set<Food> actualShop = shop.getPack();
        Set<Food> actualWarehouse = warehouse.getPack();
        Set<Food> actualTrash = trash.getPack();
        assertThat(actualShop, is(expectedShop));
        assertThat(actualWarehouse, is(expectedWarehouse));
        assertThat(actualTrash, is(expectedTrash));
    }

    @Test
    public void whenCheckQualityPercentageMethod() {
        Calendar january20 = Calendar.getInstance();
        january20.set(2020, Calendar.JANUARY, 15);
        Calendar november19 = Calendar.getInstance();
        november19.set(2019, Calendar.NOVEMBER, 15);
        Calendar february20 = Calendar.getInstance();
        february20.set(2020, Calendar.FEBRUARY, 15);
        Calendar march20 = Calendar.getInstance();
        march20.set(2020, Calendar.MARCH, 15);
        Calendar april20 = Calendar.getInstance();
        april20.set(2020, Calendar.APRIL, 15);
        Calendar may20 = Calendar.getInstance();
        may20.set(2020, Calendar.MAY, 15);
        Calendar june20 = Calendar.getInstance();
        june20.set(2020, Calendar.JUNE, 15);

        Food milkFresh = new Food("milkFresh", february20, may20, 60, "0");
        ControlQuality control = new ControlQuality();
        int result = milkFresh.getExpiryPercentage();
        int expected = 47;
        assertThat(result, is(expected));
    }

    @Test
    public void whenResortProducts() {
        Calendar january20 = Calendar.getInstance();
        january20.set(2020, Calendar.JANUARY, 15);
        Calendar november19 = Calendar.getInstance();
        november19.set(2019, Calendar.NOVEMBER, 15);
        Calendar february20 = Calendar.getInstance();
        february20.set(2020, Calendar.FEBRUARY, 15);
        Calendar march20 = Calendar.getInstance();
        march20.set(2020, Calendar.MARCH, 15);
        Calendar april20 = Calendar.getInstance();
        april20.set(2020, Calendar.APRIL, 5);
        Calendar may20 = Calendar.getInstance();
        may20.set(2020, Calendar.MAY, 15);
        Calendar june20 = Calendar.getInstance();
        june20.set(2020, Calendar.JUNE, 15);

        Set<Food> food = Set.of(new Food("milkRotten", november19, february20, 60, "0"),
                new Food("milkOld", january20, april20, 60, "0"),
                new Food("milkFresh", february20, may20, 60, "0"),
                new Food("milkJustMade", march20, june20, 60, "0"));
        Shop shop = new Shop();
        Warehouse warehouse = new Warehouse();
        Trash trash = new Trash();
        ControlQuality control = new ControlQuality(shop, warehouse, trash);
        control.allocate(food);
        Set<Food> expectedShop = Set.of(new Food("milkOld", january20, april20, 60, "50%"),
                new Food("milkFresh", february20, may20, 60, "0"));
        Set<Food> expectedWarehouse = Set.of(new Food("milkJustMade", march20, june20, 60, "0"));
        Set<Food> expectedTrash = Set.of(new Food("milkRotten", november19, february20, 60, "0"));

        Set<Food> actualShop = shop.getPack();
        Set<Food> actualWarehouse = warehouse.getPack();
        Set<Food> actualTrash = trash.getPack();
        assertThat(actualShop, is(expectedShop));
        assertThat(actualWarehouse, is(expectedWarehouse));
        assertThat(actualTrash, is(expectedTrash));

        control.resort();
        Set<Food> actualShop2 = shop.getPack();
        Set<Food> actualWarehouse2 = warehouse.getPack();
        Set<Food> actualTrash2 = trash.getPack();
        assertThat(actualShop2, is(expectedShop));
        assertThat(actualWarehouse2, is(expectedWarehouse));
        assertThat(actualTrash2, is(expectedTrash));
    }
}
