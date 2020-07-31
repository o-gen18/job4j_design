package ru.job4j.design.game;

public interface Square {
    boolean magicPosition(int position); // метод вернёт наступил ли игрок на особую клетку.

    String turn(); // метод вернёт имя следующего игрока .

    int getTotalLength();

    String name();
}
