package org.NewTicTacToe;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * The {@code Symbol} enum represents the symbols used in Tic Tac Toe game.
 * The symbols can be either X, O, or NONE.
 */
public enum Symbol {
    X("X"), O("O"), NONE(" ");

    private String representation;
    private static Set<String> selectedSymbols = new HashSet<>();

    /**
     * Constructs a new symbol with the given representation.
     * @param representation the representation of the symbol.
     */
    Symbol(String representation) {
        this.representation = representation;
    }

    /**
     * Returns the representation of the symbol.
     * @return the representation of the symbol.
     */
    public String getSymbol() {
        return representation;
    }

    /**
     * Returns the string representation of the symbol.
     * @return the string representation of the symbol.
     */
    @Override
    public String toString() {
        return representation;
    }

    /**
     * Returns the symbol corresponding to the given string representation.
     * @param symbolString the string representation of the symbol.
     * @return the symbol corresponding to the given string representation.
     */

    public static Symbol getSymbolFromString(String symbolString) {
        for (Symbol symbol : values()) {
            if (symbol.representation.equals(symbolString)) {
                return symbol;
            }
        }
        return NONE;
    }

/**
 * Prompts the user to choose their symbol.
*/
 public static Symbol getSymbolFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your symbol (any string of length 1):");
        String symbolString = scanner.nextLine().trim();
        return getSymbolFromString(symbolString);
    }

}
