package ru.job4j.collections.linkedList;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int index = 0;

    public T poll() {
        int count = 0;
        for (int i = 0; i < index; i++) {
            out.push(in.pop());
            count++;
        }
        index -= count;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        index++;
    }
}
