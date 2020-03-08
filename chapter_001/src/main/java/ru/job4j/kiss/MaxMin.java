package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparatorMax) {
        return reduce(value, comparatorMax);
    }

    public <T> T min(List<T> value, Comparator<T> comparatorMin) {
        return reduce(value, comparatorMin);
    }

    public <T> T reduce(List<T> value, Comparator<T> comparator) {
        T result = value.get(0);
        for (int i = 0; i < value.size() - 1; i++) {
            if (!(comparator.compare(result, value.get(i + 1)) >= 0)) {
                result = value.get(i + 1);
            }
        }
        return result;
    }
}
