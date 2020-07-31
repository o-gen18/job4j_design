package ru.job4j.design.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Game game = new Game();
        List<Steps> steps = new ArrayList<>();
        steps.add(new Dice());
        steps.add(new Arrow());
        List<Square> squares = new ArrayList<>();
        squares.add(new Square50());
        List<Player> players = new ArrayList<>();
        players.add(new Player1());
        players.add(new Player2());
        players.add(new Player3());
        players.add(new Player4());
        List<Player> chosenPlayers = game.chosePlayers(players, input);
        List<Player> randomPlayers = game.randomizePlayers(chosenPlayers);
        PlayerTurnIterator playerTurned = new PlayerTurnIterator(randomPlayers);
        Square square = game.chooseSquare(squares, input);

    }

    public void init(Game game, Steps steps, Square square, MagicSquare magicSquare,
                     PlayerTurnIterator players, Input input) {
        System.out.println("Игру начинает " + players.getPlayer(0).name());
        boolean run = true;
        while (run) {
            int isWinner = players.getPlayer(0).go(square, steps);
            if (isWinner < 0) {
                run = false;
            }
        }
    }

    /**
     * Makes the list of players ordered randomly.
     *
     * @param players
     * @return randomized list of players.
     */
    public List<Player> randomizePlayers(List<Player> players) {
        List<Player> randomPlayers = new ArrayList<>();
        Random random = new Random();
        for (int index = players.size(); players.size() > 0;) {
            int rand = random.nextInt(index);
            randomPlayers.add(players.get(rand));
            players.remove(rand);
        }
        return randomPlayers;
    }

    public List<Player> chosePlayers(List<Player> players, Input input) {
        for (Player player : players) {
            System.out.println(players.indexOf(player) + ". " + player.name());
        }
        int selected = input.askInt("Выберите количество игроков до "
                + players.size() + "-x", players.size());
        int fromIndex = players.size() - selected;
        int toIndex = players.size() - 1;
        return players.subList(fromIndex, toIndex);
    }

    public Square chooseSquare(List<Square> squares, Input input) {
        for (Square square : squares) {
            System.out.println(squares.indexOf(square) + ". " + square.name());
        }
        int selected = input.askInt("Выберите игровое поле", squares.size());
        return squares.get(selected);
    }
}
