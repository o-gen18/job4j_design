package ru.job4j.io.shildt;

import java.io.*;

public class SimpleRead {
    public static void main(String[] args) throws IOException {
        char c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Type symbols, 'q' - to exit");
        do {
            c = (char) br.read();
            System.out.println(c);
        } while (c != 'q');
    }
}
