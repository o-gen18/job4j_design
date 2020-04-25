package ru.job4j.collections.generic;

import java.util.ArrayList;
import java.util.List;

public class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (T element : mem) {
            if (element.getId().equals(id)) {
                mem.set(mem.indexOf(element), model);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (T element : mem) {
            if (element.getId().equals(id)) {
                mem.remove(element);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public T findBy(String id) {
        T result = null;
        for (T element : mem) {
            if (element.getId().equals(id)) {
                result = element;
            }
        }
        return result;
    }
}
