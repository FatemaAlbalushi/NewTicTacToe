package org.NewTicTacToe;

import java.util.Arrays;

/**
 * The Board class represents the Tic-Tac-Toe board. It contains methods to update the board,
 * check if a move is valid, check if the game is over, and get the winner of the game.
 */
public class Board {
    private Symbol[][] board;

    /**
     * Constructor for Board class.
     * Initializes a 3x3 board with all cells set to Symbol.NONE.
     */
    public Board() {
        board = new Symbol[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Symbol.NONE;
            }
        }
    }

    /**
     * Makes a move on the board.
     * @param row Row index to place the symbol.
     * @param col Column index to place the symbol.
     * @param symbol Symbol to place on the board.
     * @return True if the move was successful, false otherwise.
     */
    public boolean makeMove(int row, int col, Symbol symbol) {
        if (board[row][col] == Symbol.NONE) {
            board[row][col] = symbol;
            return true;
        }
        return false;
    }

    /**
     * Updates the board with the given symbol at the specified position.
     * @param x Row index to place the symbol.
     * @param y Column index to place the symbol.
     * @param symbol Symbol to place on the board.
     * @return True if the update was successful, false otherwise.
     */
    public boolean update(int x, int y, Symbol symbol) {
        if (x < 0 || x >= 3 || y < 0 || y >= 3) {
            return false;
        }
        if (board[x][y] != Symbol.NONE) {
            return false;
        }
        board[x][y] = symbol;
        return true;
    }

    /**
     * Checks if the specified move is valid.
     * @param row Row index to check.
     * @param col Column index to check.
     * @return True if the move is valid, false otherwise.
     */
    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == Symbol.NONE;
    }

/**
 * Gets the winner of the game.
 * @return Symbol of the winning player, or Symbol.NONE if there is no winner yet
 */
    public Symbol getWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != Symbol.NONE) {
                return board[i][0];
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != Symbol.NONE) {
                return board[0][i];
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != Symbol.NONE) {
            return board[0][0];
        }
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != Symbol.NONE) {
            return board[2][0];
        }

        return Symbol.NONE;
    }

    /**
     * Check if the game is over.
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return getWinner() != Symbol.NONE || isBoardFull();

    }

    /**
     * Check if the board is full.
     * @return true if the board is full, false otherwise.
     */
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Symbol.NONE) {
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * Returns a string representation of the TicTacToe board.
     * @return String representation of the TicTacToe board.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j].getSymbol());
                if (j < 2) {
                    sb.append("|");
                }
            }
            sb.append("\n");
            if (i < 2) {
                sb.append("-+-+-\n");
            }
        }
        return sb.toString();
    }


}