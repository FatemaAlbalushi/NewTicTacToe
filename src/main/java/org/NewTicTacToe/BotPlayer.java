package org.NewTicTacToe;

import java.util.Random;

public class BotPlayer extends Player {
    public BotPlayer(String name, Symbol symbol, int level) {
        super(name, symbol);
    }

    @Override
    public void makeMove(Board board) {
        Random random = new Random();
        int row = random.nextInt(3);
        int col = random.nextInt(3);
        while (!board.isValidMove(row, col)) {
            row = random.nextInt(3);
            col = random.nextInt(3);
        }
        board.makeMove(row, col, this.getSymbol());
        System.out.println(this.getName() + " has made a move at row " + (row+1) + ", column " + (col+1));
    }
}
