package ru.job4j.collections.exam;

import java.util.*;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        boolean result = false;
        if (left.length() == right.length()) {
            Map<Character, Character> map1 = new HashMap<>();
            Map<Character, Character> map2 = new HashMap<>();

            char[] doubledLetters1 = new char[left.length()];
            int index = 0;
            for (char c : left.toCharArray()) {
                if (map1.containsKey(c)) {
                    doubledLetters1[index++] = c;
                }
                map1.put(c, c);
            }

            char[] doubledLetters2 = new char[right.length()];
            int index2 = 0;
            for (char c : right.toCharArray()) {
                map1.put(c, c);
                if (map2.containsKey(c)) {
                    doubledLetters2[index2++] = c;
                }
                map2.put(c, c);
            }
            if (map1.equals(map2) && Arrays.equals(doubledLetters1, doubledLetters2)) {
                result = true;
            }
        }
        return result;
    }
}
