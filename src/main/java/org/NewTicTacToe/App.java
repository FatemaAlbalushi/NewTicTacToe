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
        if (board != null) {
            System.out.println("A saved game state was found. Do you want to complete the previous play? (y/n)");
            String chose = SCANNER.nextLine();
            if (!chose.equalsIgnoreCase("y")) {
                STATE_MANAGER.deleteGameState(board);
                board = new Board();
            }
        } else {
            // If there is no saved game state, create a new board
            board = new Board();
        }

        System.out.println("Enter name for Player 1: ");
        String player1Name = SCANNER.nextLine();

        boolean isBot = false;
        int level = 0;
        System.out.println("Do you want to play against the bot? (y/n)");
        String usinput = SCANNER.nextLine();
        if (usinput.equalsIgnoreCase("y")) {
            isBot = true;
            System.out.println("Choose the difficulty level: 1 - easy, 2 - medium, 3 - hard");
            level = Integer.parseInt(SCANNER.nextLine());
        }

        String player2Name = "";
        Player player2;
        if (isBot) {
            player2Name = "Bot";
            player2 = new BotPlayer(player2Name, Symbol.O, level);
        } else {
             System.out.println("Enter name for Player 2: ");
            player2Name = SCANNER.nextLine();
            player2 = new Player(player2Name, Symbol.getSymbolFromUser());
        }

//        System.out.println("Enter name for Player 2: ");
//        String player2Name = SCANNER.nextLine();

        Player player1 = new Player(player1Name, Symbol.getSymbolFromUser());
        //Player player2 = new Player(player2Name, Symbol.getSymbolFromUser());


        Player currentPlayer = player1;

        boolean isQuit = false;
        while (!board.isGameOver() && !isQuit) {
            System.out.println(board);

            System.out.println(currentPlayer.getName() + " (" + currentPlayer.getSymbol() + "), it's your turn.");
            if (isBot && currentPlayer == player2) {
                currentPlayer.makeMove(board);
                STATE_MANAGER.saveGameState(board, player1Name, player2Name);
                currentPlayer = player1;
            } else {

                System.out.println("Enter row and column (e.g. 1 1) or q to quit:");
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
                        STATE_MANAGER.saveGameState(board, player1Name, player2Name);
                        currentPlayer = currentPlayer == player1 ? player2 : player1;
                    } else {
                        System.out.println("Invalid move, try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input, try again.");
                }
            }
        }

//        while (!board.isGameOver() && !isQuit) {
//            System.out.println(board);
//
//            if (isBot && currentPlayer.getSymbol() == Symbol.O) {
//                // Bot's turn
//                System.out.println("Bot is thinking...");
//                BotMove botMove = BotPlayer.getNextMove(board, level, Symbol.O);
//                board.makeMove(botMove.getRow(), botMove.getCol(), Symbol.O);
//                System.out.println("Bot played " + (botMove.getRow() + 1) + " " + (botMove.getCol() + 1));
//                currentPlayer = player1;
//            }
//            else {
//                try {
//                    int row = Integer.parseInt(input.split(" ")[0]) - 1;
//                    int col = Integer.parseInt(input.split(" ")[1]) - 1;
//                    if (board.isValidMove(row, col)) {
//                        board.makeMove(row, col, currentPlayer.getSymbol());
//                        STATE_MANAGER.saveGameState(board, player1Name, player2Name);
//                        currentPlayer = currentPlayer == player1 ? player2 : player1;
//                    } else {
//                        System.out.println("Invalid move, try again.");
//                    }
//                } catch (Exception e) {
//                    System.out.println("Invalid input, try again.");
//                }
//            }
//
//
//            System.out.println(currentPlayer.getName() + " (" + currentPlayer.getSymbol() + "), it's your turn.");
//            System.out.println("Enter row and column (e.g. 1 1) or q to quit::");
//            String Uinput = SCANNER.nextLine();
//
//            if (Uinput.equals("q")) {
//                STATE_MANAGER.saveGameState(board, player1Name, player2Name);
//                isQuit = true;
//                break;
//
//            }
//
//            try {
//                int row = Integer.parseInt(input.split(" ")[0]) - 1;
//                int col = Integer.parseInt(input.split(" ")[1]) - 1;
//                if (board.isValidMove(row, col)) {
//                    board.makeMove(row, col, currentPlayer.getSymbol());
//                    STATE_MANAGER.saveGameState(board,player1Name,player2Name);
//                    currentPlayer = currentPlayer == player1 ? player2 : player1;
//
//                } else {
//                    System.out.println("Invalid move, try again.");
//                }
//            } catch (Exception e) {
//                System.out.println("Invalid input, try again.");
//            }
       // }

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