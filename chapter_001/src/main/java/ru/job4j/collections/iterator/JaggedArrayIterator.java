package ru.job4j.collections.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JaggedArrayIterator implements Iterator {
    private final int[][] matrix;
    private int rowIndex = 0;
    private int colIndex = 0;

    public JaggedArrayIterator(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public boolean hasNext() {
        return matrix[rowIndex].length - 1 > colIndex || matrix.length - 1 > rowIndex;
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (colIndex == matrix[rowIndex].length) {
            colIndex = 0;
            rowIndex++;
        }
        return matrix[rowIndex][colIndex++];
    }
}
