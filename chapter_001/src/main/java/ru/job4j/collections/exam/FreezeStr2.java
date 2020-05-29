package ru.job4j.collections.exam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FreezeStr2 {
    public static boolean eq(String left, String right) {
        boolean result = false;
        if (left.length() == right.length()) {
            Map<Character, Character> map = new HashMap<>();
            Map<Character, Character> map2 = new HashMap<>();
            Map<Integer, Character> mapForDuplicates = new HashMap<>();
            Map<Integer, Character> mapForDuplicates2 = new HashMap<>();
            int index = 0;
            int index2 = 0;
            for (char c : left.toCharArray()) {
                if (map.containsKey(c)) {
                    mapForDuplicates.put(index++, c);
                }
                map.put(c, c);
            }
            for (char c : right.toCharArray()) {
                if (map2.containsKey(c)) {
                    mapForDuplicates2.put(index2++, c);
                }
                map2.put(c, c);
            }
            if (mapForDuplicates.equals(mapForDuplicates2) && map.equals(map2))
            {
                result = true;
            }
        }
        return result;
    }
}
