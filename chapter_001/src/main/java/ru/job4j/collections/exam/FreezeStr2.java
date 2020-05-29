package ru.job4j.collections.exam;

import java.util.*;

public class FreezeStr2 {
    public static boolean eq(String left, String right) {
        boolean result = false;
        if (left.length() == right.length()) {
            Map<Character, Character> map1 = new HashMap<>();
            Map<Character, Character> map2 = new HashMap<>();
            Set<Character> setForDuplicates1 = new HashSet<>();
            Set<Character> setForDuplicates2 = new HashSet<>();

            for (char c : left.toCharArray()) {
                if (map1.containsKey(c)) {
                    setForDuplicates1.add(c);
                }
                map1.put(c, c);
            }

            for (char c : right.toCharArray()) {
                if (map2.containsKey(c)) {
                    setForDuplicates2.add(c);
                }
                map2.put(c, c);
            }

            if (setForDuplicates1.equals(setForDuplicates2) && map1.equals(map2))
            {
                result = true;
            }
        }
        return result;
    }
}
