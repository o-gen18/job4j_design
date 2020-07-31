package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static class Matrix {
        public static String multiple(int size) {
            StringBuilder string = new StringBuilder();
            int[][] table = new int[size][size];
            for (int i = 0; i != size; i++) {
                for (int j = 0; j != size; j++) {
                    table[i][j] = (i + 1) * (j + 1);
                    string.append(table[i][j] + "  ");
                }
                string.append(System.lineSeparator());
            }
            return string.toString();
        }
    }

    public static void main(String[] args) {
        String result = Matrix.multiple(9);
        try (FileOutputStream out = new FileOutputStream("MutiplyTable.txt")) {
            out.write(result.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}