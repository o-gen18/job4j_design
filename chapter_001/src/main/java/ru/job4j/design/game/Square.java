package ru.job4j.design.game;

public interface Square {
    boolean magicAction(int step);// метод вернёт наступил ли игрок на особую клетку.
    String turn();// метод вернёт имя следующего игрока .
}
