package ru.job4j.kiss;

import java.util.Comparator;

public class Element {
    private int age;
    private String name;

    public Element(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", age: " + age;
    }

    static class CompareByAgeForMax implements Comparator<Element> {
        @Override
        public int compare(Element el1, Element el2) {
            return Integer.compare(el1.getAge(), el2.getAge());
        }
    }

    public static class CompareByAgeForMin implements Comparator<Element> {
        @Override
        public int compare(Element el1, Element el2) {
            return Integer.compare(el2.getAge(), el1.getAge());
        }
    }
}
