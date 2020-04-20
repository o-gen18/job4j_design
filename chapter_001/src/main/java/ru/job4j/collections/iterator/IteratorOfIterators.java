package ru.job4j.collections.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorOfIterators {
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator <Integer> temp = it.next();

            private void setNext() {
                if (it.hasNext()) {
                    temp = it.next();
                }
            }

            @Override
            public boolean hasNext() {
                if (!temp.hasNext()) {
                    setNext();
                }
                return temp.hasNext();
            }

            @Override
            public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    if (!temp.hasNext()) {
                        setNext();
                    }
                    return temp.next();
                }
            };
        }
    }

