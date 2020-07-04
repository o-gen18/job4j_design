package ru.job4j.io.shildt;

import java.io.*;

public class SimpleBufferedReader {
    public static void main(String[] args) throws IOException {
        //создать поток ввода типа BufferedReader, используя стандартный поток ввода System.in
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        System.out.println("Enter text strings");
        System.out.println("Enter 'STOP' to finish");
        do {
            str = br.readLine();
            System.out.println(str);
        } while (!str.equals("STOP"));
    }
}
