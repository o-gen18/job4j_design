package ru.job4j.design.srp;

public class DocDefault implements DocFormat {
    @Override
    public StringBuilder formatHead(String head) {
        return new StringBuilder(head);
    }

    @Override
    public StringBuilder formatBody(String body) {
        return new StringBuilder(body);
    }

    @Override
    public String formatTail() {
        return "";
    }
}
