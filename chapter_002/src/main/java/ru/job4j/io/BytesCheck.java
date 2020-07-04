package ru.job4j.io;

import java.util.Arrays;

public class BytesCheck {
    public static void main(String[] args) {
        char[] ascii = new char[65536];
        for (int i = 0; i < 65536; i++) {
            ascii[i] = (char) i;
        }
        System.out.print(Arrays.toString(ascii));
    }
}
