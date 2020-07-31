package ru.job4j.design.game;

import java.util.Iterator;
import java.util.List;

public class PlayerTurnIterator implements Iterator {
    private final List<Player> players;
    private int index = 0;

    public PlayerTurnIterator(List<Player> players) {
        this.players = players;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Object next() {
        if (index >= players.size()) {
            index = 0;
        }
        return players.get(index++);
    }

    public Player getPlayer(int i) {
        return players.get(i);
    }
}
