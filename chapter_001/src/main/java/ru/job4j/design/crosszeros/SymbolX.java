package ru.job4j.design.crosszeros;

import java.util.Objects;

public class SymbolX implements Symbol {
    private final char x = 'X';

    @Override
    public boolean verify(char input) {
        boolean result = false;
        if (input == x) {
            result = true;
        }
        return result;
    }

    @Override
    public char get() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SymbolX symbolX = (SymbolX) o;
        return Objects.equals(this.x, symbolX.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x);
    }
}
