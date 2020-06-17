package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Oleg Generalov";
        int age = 25;
        short s = 12;
        float f = 15.5f;
        double d = 36.6666;
        char c = 'A';
        byte b = 50;
        long l = 999999999999999L;
        int bitwise = 0b10101010;
        LOG.debug("User info name : {}, age : {}, short : {}, "
                + "float : {}, double : {}, char : {}, byte : {}, "
                + "long : {}, bitwize : {}", name, age, s, f, d, c, b, l, bitwise);
    }
}