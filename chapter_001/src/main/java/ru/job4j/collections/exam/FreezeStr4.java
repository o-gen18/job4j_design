package ru.job4j.collections.exam;

import java.util.*;

public class FreezeStr4 {
    public static boolean eq(String left, String right) {
        boolean result = false;
        if (left.length() == right.length()) {
            Map<Integer, Character> map = new HashMap<>();
            Map<Integer, Character> map2 = new HashMap<>();
            int index1 = 0;
            int index2 = 0;
            for (char c : left.toCharArray()) {
                map.put(index1++, c);
            }
            for (char c : right.toCharArray()) {
                map2.put(index2++, c);
            }
            if (map.values().equals(map2.values())) {
                result = true;
            }
        }
        return result;
    }
}
