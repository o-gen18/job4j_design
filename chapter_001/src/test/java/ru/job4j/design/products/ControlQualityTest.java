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
        january20.set(2020,0,15);
        Calendar november19 = Calendar.getInstance();
        november19.set(2019, 10, 15);
        Calendar february20 = Calendar.getInstance();
        february20.set(2020, 1, 15);
        Calendar march20 = Calendar.getInstance();
        march20.set(2020, 2, 15);
        Calendar april20 = Calendar.getInstance();
        april20.set(2020, 3, 15);
        Calendar may20 = Calendar.getInstance();
        may20.set(2020, 4, 15);
        Calendar june20 = Calendar.getInstance();
        june20.set(2020, 5, 15);

        Set<Food> food = Set.of(new Food("milkRotten", november19, february20, 60, "0"),
                new Food("milkOld", january20, april20, 60, "0"),
                new Food("milkFresh", february20, may20, 60, "0"),
                new Food("milkJustMade", march20, june20, 60, "0"));
        ControlQuality control = new ControlQuality(food);
        control.allocate();
        Set<Food> expectedShop = Set.of(new Food("milkOld", january20, april20, 60, "50%"),
                new Food("milkFresh", february20, may20, 60, "0"));
        Set<Food> expectedWarehouse = Set.of(new Food("milkJustMade", march20, june20, 60, "0"));
        Set<Food> expectedTrash = Set.of(new Food("milkRotten", november19, february20, 60, "0"));

        Set<Food> actualShop = control.shop.getPack();
        Set<Food> actualWarehouse = control.warehouse.getPack();
        Set<Food> actualTrash = control.trash.getPack();
        assertThat(actualShop, is(expectedShop));
        assertThat(actualWarehouse, is(expectedWarehouse));
        assertThat(actualTrash, is(expectedTrash));
    }
}
