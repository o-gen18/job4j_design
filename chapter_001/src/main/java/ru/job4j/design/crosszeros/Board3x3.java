package ru.job4j.design.crosszeros;

public class Board3x3 implements Board {
    private Symbol lastSymbol;

    private char[][] board = {
            {'_', '_', '_'},
            {'_', '_', '_'},
            {'_', '_', '_'}
    };

    @Override
    public String name() {
        return "Поле 3х3";
    }

    @Override
    public int length() {
        return board.length;
    }

    @Override
    public void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(String.valueOf(cell) + ' ');
            }
            System.out.println();
        }
    }

    @Override
    public boolean repeatingSymbol(Symbol symbol) {
        return symbol.equals(lastSymbol);
    }

    @Override
    public void makeStep(int row, int cell, Symbol symbol) {
        if (repeatingSymbol(symbol) || !this.isEmpty(row, cell, board)) {
            throw new IllegalStateException("Ошибка! Занята клетка или попытка поставить символ противника!");
        }
        board[row][cell] = symbol.get();
        lastSymbol = symbol;
    }

    @Override
    public boolean isEmpty(int row, int cell, char[][] board) {
        return board[row][cell] == '_';
    }

    @Override
    public boolean isWin(Symbol symbol) {
        boolean result = false;
        Symbol x = new SymbolX();
        int diagonalX1 = 0, diagonalX2 = 0, diagonalO1 = 0, diagonalO2 = 0;
        for (int row = 0; row < board.length; row++) {
            int countX = 0, countO = 0, countX2 = 0, countO2 = 0;
            for (int cell= 0; cell < board.length; cell++) {
                if (board[row][cell] == symbol.get()) {
                    if (symbol.get() == x.get()) {
                        countX++;
                        if (row == cell) {
                            diagonalX1++;
                        }
                    } else {
                        countO++;
                        if (row == cell) {
                            diagonalO1++;
                        }
                    }
                }
                if (countX == 3 || countO == 3) {
                    result = true;
                }
                if (board[cell][row] == symbol.get()) {
                    if (symbol.get() == x.get()) {
                        countX2++;
                        if (cell == row) {
                            diagonalX2++;
                        }
                    } else {
                        countO2++;
                        if (cell == row) {
                            diagonalO2++;
                        }
                    }
                }
                if (countX2 == 3 || countO2 == 3) {
                    result = true;
                }
            }
        }
        if (diagonalX1 == 3 || diagonalX2 == 3 || diagonalO1 == 3 || diagonalO2 == 3) {
            result = true;
        }
        return result;
    }
}
