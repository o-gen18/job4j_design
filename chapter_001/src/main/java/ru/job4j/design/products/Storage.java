package ru.job4j.design.products;

import java.util.Set;

public interface Storage {
    void allocate(Set<Food> foodPack, ControlQuality control);
}
