package org.NewTicTacToe;

import java.util.Scanner;

/**
 * This is the main class that starts the Tic-Tac-Toe game.
 * The game starts by loading the saved game state, or creating a new game board if there is no saved game state.
 * The players are prompted to enter their names, and then their desired symbol (X or O).
 * The game runs in a loop until the game is over or the user quits.
 * The current player is prompted to make a move by entering the row and column (1-3).
 * If the move is valid, the move is made on the board, and the current player changes.
 */
public class App {

    /**
     * Scanner instance for reading input from console.
     */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Instance of the StateManager class to handle saving and loading of game state.
     */
    private static final StateManager STATE_MANAGER = new StateManager();

    /**
            * The main method that starts the Tic-Tac-Toe game.
            * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe!");

        // Try to load the saved game state, if it exists
        Board board = STATE_MANAGER.loadGameState();
        if (board == null) {
            // If there is no saved game state, create a new board
            board = new Board();
        }

        System.out.println("Enter name for Player 1: ");
        String player1Name = SCANNER.nextLine();

        System.out.println("Enter name for Player 2: ");
        String player2Name = SCANNER.nextLine();

        Player player1 = new Player(player1Name, Symbol.getSymbolFromUser());
        Player player2 = new Player(player2Name, Symbol.getSymbolFromUser());


        Player currentPlayer = player1;

        boolean isQuit = false;
        while (!board.isGameOver() && !isQuit) {
            System.out.println(board);

            System.out.println(currentPlayer.getName() + " (" + currentPlayer.getSymbol() + "), it's your turn.");
            System.out.println("Enter row and column (e.g. 1 1) or q to quit::");
            String input = SCANNER.nextLine();

            if (input.equals("q")) {
                STATE_MANAGER.saveGameState(board, player1Name, player2Name);
                isQuit = true;
                break;

            }

            try {
                int row = Integer.parseInt(input.split(" ")[0]) - 1;
                int col = Integer.parseInt(input.split(" ")[1]) - 1;
                if (board.isValidMove(row, col)) {
                    board.makeMove(row, col, currentPlayer.getSymbol());
                    currentPlayer = currentPlayer == player1 ? player2 : player1;
                } else {
                    System.out.println("Invalid move, try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, try again.");
            }
        }

        System.out.println(board);
        if (!isQuit) {
            if (board.getWinner() == Symbol.NONE) {
                System.out.println("It's a tie!");
            } else {
                System.out.println(board.getWinner() + " wins!");
            }
            STATE_MANAGER.deleteGameState(board);
        }
    }
}