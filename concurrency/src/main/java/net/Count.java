package net;

public class Count {
    private int value;

    public void sayHello() {
        System.out.println("Count is saying hello!");
    }

    public void sayPhrase(String phrase) {
        System.out.println(phrase);

    }

    public void increment() {
        value++;
    }

    public int get() {
        return value;
    }
}
