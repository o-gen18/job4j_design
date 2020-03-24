package ru.job4j.design.srp;

public interface DocFormat {
    StringBuilder formatHead(String head);
    StringBuilder formatBody(String body);
    String formatTail();
}
