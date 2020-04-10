package ru.job4j.design.crosszeros;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartUI {
    public static void main(String[] args) {
        List<Board> boards = new ArrayList<>();
        boards.add(new Board3x3());
        boards.add(new Board5x5());
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new SecondPlayer());
        enemies.add(new ComputerEnemy());
        List<Difficulty> victories = new ArrayList<>();
        victories.add(new OneVictoryDifficulty());
        victories.add(new ThreeVictoryDifficulty());
        StartUI start = new StartUI();
        Input input = new ConsoleInput();
        Board board = start.askBoard(boards, input);
        Enemy enemy = start.askEnemy(enemies, input);
        Difficulty difficulty = start.askDifficulty(victories, input);
        Player[] players = {enemy, new MainPlayer()};
        Player[] firstPlayer = start.firstPlayer(players);
        start.init(start, board, firstPlayer, difficulty, input);
    }

    public void init(StartUI start, Board board, Player[] firstPlayer, Difficulty difficulty, Input input) {
        System.out.println("Начало игры! Первым ходит " + firstPlayer[0].name() + " - Режим: " + difficulty.name());
        while (!(difficulty.victory())) {
            board.printBoard();
            Symbol symbol = firstPlayer[0].go(board, input);
            if (board.isWin(symbol)) {
                difficulty.iterate();
            }
            firstPlayer = start.switchTurn(firstPlayer);
        }
        board.printBoard();
        System.out.println("Игра окончена! Победил " + firstPlayer[1].name());
    }

    public void showBoards(List<Board> boards) {
        for (Board board : boards) {
            System.out.println(boards.indexOf(board) + ". " + board.name());
        }
    }

    public Board askBoard(List<Board> boards, Input input) {
        this.showBoards(boards);
        int selected = input.askInt("Выберите размер доски: ", boards.size());
        return boards.get(selected);
    }

    public void showEnemies(List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            System.out.println(enemies.indexOf(enemy) + ". " + enemy.name());
        }
    }

    public Enemy askEnemy(List<Enemy> enemies, Input input) {
        this.showEnemies(enemies);
        int selected = input.askInt("Выберите с кем играть: ", enemies.size());
        return enemies.get(selected);
    }

    public Difficulty askDifficulty(List<Difficulty> victories, Input input) {
        for (Difficulty difficulty : victories) {
            System.out.println(victories.indexOf(difficulty) + ". " + difficulty.name());
        }
        int selected = input.askInt("Выберите сложность игры: ", victories.size());
        return victories.get(selected);
    }

    public Player[] firstPlayer(Player[] players) {
        int turnFirst = new Random().nextInt(2);
        return new Player[] {players[turnFirst], players[players.length - 1 - turnFirst]};
    }

    public Player[] switchTurn(Player[] players) {
        return new Player[]{players[1], players[0]};
    }
}
