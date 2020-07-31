package ru.job4j.collections.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatIt {
    public static List<Integer> flatten(Iterator<Iterator<Integer>> it) {
        List<Integer> list = new ArrayList<>();
        it.forEachRemaining(subIt -> subIt.forEachRemaining(list::add));
        return list;
    }
}