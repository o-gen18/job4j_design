package ru.job4j.collections.linkedList;

public class SimpleStack<T> {
    private ForwardLinked<T> list = new ForwardLinked<>();

    public void push(T value) {
        list.add(value);
    }

    public T pop() {
       return list.deleteLast();
    }
}
