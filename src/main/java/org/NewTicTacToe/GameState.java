package org.NewTicTacToe;

/**
 * The GameState class stores information about the current state of the Tic-Tac-Toe game.
 * This information includes the board, player1 name, and player2 name.
 */
public class GameState {
    private Board board;
    private String player1Name;
    private String player2Name;

    /**
     * Constructor for the GameState class.
     * @param board The current state of the game board.
     * @param player1Name The name of player1.
     * @param player2Name The name of player2.
     */
    public GameState(Board board, String player1Name, String player2Name) {
        this.board = board;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    /**
     * Gets the current state of the game board.
     * @return The current state of the game board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets the name of player1.
     * @return The name of player1.
     */
    public String getPlayer1Name() {
        return player1Name;
    }

    /**
     * Gets the name of player2.
     * @return The name of player2.
     */
    public String getPlayer2Name() {
        return player2Name;
    }
}