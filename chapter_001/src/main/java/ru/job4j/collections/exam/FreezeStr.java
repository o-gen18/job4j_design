package ru.job4j.collections.exam;

import java.util.HashSet;
import java.util.Set;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        boolean result = false;
        if (left.length() == right.length()) {
            Set<Character> map1 = new HashSet<>();
            Set<Character> map2 = new HashSet<>();
            for (char c : left.toCharArray()) {
                map1.add(c);
            }
            for (char c : right.toCharArray()) {
                map2.add(c);
            }
            if (map1.equals(map2)) {
                result = true;
            }
        }
        return result;
    }
}
