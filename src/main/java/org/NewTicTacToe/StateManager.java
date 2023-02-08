package org.NewTicTacToe;
import com.google.gson.Gson;

import java.io.*;

/**
 * The {@code StateManager} class is responsible for saving and loading the game state to and from a file.
 * The state of the game is stored in a file named {@code game_state.json} under a directory named {@code data}.
 * The class uses the GSON library to serialize and deserialize objects to and from JSON format.
 */
public class StateManager {
    private static final String SAVE_FILE = "data/game_state.json";

    private Gson gson;

    /**
     * The constructor creates an instance of the GSON library and makes sure the data directory exists.
     * If the directory does not exist, it will be created.
     */
    public StateManager() {
        gson = new Gson();
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
    }

    /**
     * Saves the current state of the game to a file.
     * @param board The current state of the board.
     * @param player1Name The name of the first player.
     * @param player2Name The name of the second player.
     */
    public void saveGameState(Board board, String player1Name, String player2Name) {
        GameState gameState = new GameState(board, player1Name, player2Name);
        try {
            FileWriter writer = new FileWriter(SAVE_FILE);
            gson.toJson(gameState, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to save game state");
        }

    }

    /**
     * Loads the game state from the file.
     * @return The current state of the board if the file exists, otherwise returns {@code null}.
     */

    public Board loadGameState() {
        FileReader reader = null;
        try {
            reader = new FileReader(SAVE_FILE);

            GameState gameState = gson.fromJson(reader, GameState.class);
            if (gameState != null) {
                return gameState.getBoard();
            }

            return null;
        } catch (IOException e) {
            System.out.println("Failed to load game state");
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Failed to close reader");
                }
            }
        }
    }

    /**
     * Deletes the game state file.
     * @param board The current state of the board.
     */
    public void deleteGameState(Board board) {
        File file = new File(SAVE_FILE);
        try {
            file.delete();
            if (file.exists() && !file.delete()) {
                throw new IOException("Failed to delete file " + SAVE_FILE);
            }
        } catch (IOException e) {
            System.out.println("Failed to delete game state");
        }
    }

}
