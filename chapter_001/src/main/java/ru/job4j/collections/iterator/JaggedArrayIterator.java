package ru.job4j.collections.iterator;

import java.util.Iterator;

public class JaggedArrayIterator implements Iterator {
    private final int[][] matrix;
    private int index1 = 0;
    private int index2 = 0;

    public JaggedArrayIterator(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public boolean hasNext() {
        return matrix[index1].length - 1 > index2 || matrix.length - 1 > index1;
    }

    @Override
    public Object next() {
        if (index2 == matrix[index1].length) {
            index2 = 0;
            index1++;
        }
        return matrix[index1][index2++];
    }
}
