package ru.job4j.collections.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        boolean result = false;
        if (left.length() == right.length()) {
            List<Character> list = new ArrayList<>();
            List<Character> list2 = new ArrayList<>();
            for (char c : left.toCharArray()) {
                list.add(c);
            }
            for (char c : right.toCharArray()) {
                list2.add(c);
            }
            Collections.sort(list);
            Collections.sort(list2);
            if (list.equals(list2)) {
                result = true;
            }
        }
        return result;
    }
}
