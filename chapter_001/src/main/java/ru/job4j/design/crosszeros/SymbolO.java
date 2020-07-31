package ru.job4j.design.crosszeros;

import java.util.Objects;

public class SymbolO implements Symbol {
    private final char o = 'O';

    @Override
    public boolean verify(char input) {
        boolean result = false;
        if (input == o) {
            result = true;
        }
        return result;
    }

    @Override
    public char get() {
        return o;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SymbolO symbolO = (SymbolO) o;
        return Objects.equals(this.o, symbolO.o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(o);
    }
}
