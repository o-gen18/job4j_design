package ru.job4j.design.game;

public interface Steps {
    int steps(); // метод вернёт колитчество шагов которые игроку надо сделать.

    String name(); // метод вернёт название генератора  ходов.
}
