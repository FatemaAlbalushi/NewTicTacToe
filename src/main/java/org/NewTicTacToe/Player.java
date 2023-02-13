package org.NewTicTacToe;

import java.util.Scanner;

/** This class represents a Player in a Tic-Tac-Toe game.
        *
        * @author [Your Name Here]
        * @version [Version Number Here]
        */

public class Player {
    private String name;
    private Symbol symbol;

    /**
     * Creates a new Player with the given name and symbol.
     * @param name the name of the Player
     * @param symbol the symbol that represents the Player's moves on the Board
     */
    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * Prompts the Player to make a move on the given Board.
     * @param board the Board on which the Player is making a move
     */
    public void makeMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(this.name + " it's your turn. Choose a location to place your " + this.symbol + " symbol.");
        System.out.print("Enter x: ");
        int x = scanner.nextInt();
        System.out.print("Enter y: ");
        int y = scanner.nextInt();
        if (!board.update(x, y, this.symbol)) {
            System.out.println("Invalid move. Please try again.");
            makeMove(board);
        }
    }

    /**
     * Get the name of the player
     * @return The name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Get the symbol used by the player
     * @return The symbol used by the player
     */
    public Symbol getSymbol() {
        return symbol;
    }
}
