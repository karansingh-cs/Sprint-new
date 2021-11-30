package product;

import java.awt.Color;
import java.util.*;
import javax.swing.*;

public class Game {

    static int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };

    static int TOTALCOLUMNS;
    static int TOTALROWS;

    public enum Box {
        EMPTY, SignS, SignO
    }

    protected JButton[][] buttons;
    public String turn;

    public enum GameState {
        PLAYING, DRAW, BLUE_WON, RED_WON
    }

    GameState currentGameState;

    public Game() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter the size of the grid");
            System.out.print("Row : ");
            TOTALROWS = sc.nextInt();
            System.out.print("Col : ");
            TOTALCOLUMNS = sc.nextInt();
        }
        buttons = new JButton[TOTALROWS][TOTALCOLUMNS];
        initGame();
    }

    public Game(int totalRows, int totalColumns) {

        buttons = new JButton[totalRows][totalColumns];
        initGame();
    }

    // public Game newgame() {
    // return Game(TOTALROWS, TOTALCOLUMNS);

    // }

    public void initGame() {
        for (int row = 0; row < TOTALROWS; ++row) {
            for (int col = 0; col < TOTALCOLUMNS; ++col) {
                buttons[row][col] = new JButton();
            }
        }
        currentGameState = GameState.PLAYING;
        turn = "Blue";
    }

    public void resetGame() {
        initGame();
    }

    public int getTotalRows() {
        return TOTALROWS;
    }

    public int getTotalColumns() {
        return TOTALCOLUMNS;
    }

    public JButton getCell(int row, int column) {
        if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS) {
            return buttons[row][column];
        } else {
            return null;
        }
    }

    public String getTurn() {
        return turn;
    }

    public void makeMove(int row, int column) {
        if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS
                && buttons[row][column].getText() == "") {
            updateGameState(turn, row, column);
            turn = (turn == "Blue") ? "Red" : "Blue";
        }
    }

    private void updateGameState(String turn, int row, int column) {
        if (hasWon(buttons)) { // check for win
            currentGameState = (turn == "Blue") ? GameState.BLUE_WON : GameState.RED_WON;
        } else if (isDraw()) {
            currentGameState = GameState.DRAW;
        }
    }

    public int getNumberOfEmptyCells() {
        int numberOfEmptyCells = 0;
        for (int row = 0; row < TOTALROWS; ++row) {
            for (int col = 0; col < TOTALCOLUMNS; ++col) {
                if (buttons[row][col].getText() == "") {
                    numberOfEmptyCells++;
                }
            }
        }
        return numberOfEmptyCells;
    }

    boolean isDraw() {
        for (int row = 0; row < TOTALROWS; ++row) {
            for (int col = 0; col < TOTALCOLUMNS; ++col) {
                if (buttons[row][col].getText().equals("")) {
                    return false; // an empty cell found, not draw
                }
            }
        }
        return true;
    }

    static boolean search(JButton[][] buttons, int row, int col, String word) {
        if (!buttons[row][col].getText().equals(word.substring(0, 1)))
            return false; // If first character of word doesn't match the grid
        int len = word.length();
        for (int dir = 0; dir < 8; dir++) { // Search word in all 8 directions
            int k, rd = row + x[dir], cd = col + y[dir]; // Initialize starting point

            // First character is already checked, match remaining characters
            for (k = 1; k < len; k++) {
                if (rd >= TOTALROWS || rd < 0 || cd >= TOTALCOLUMNS || cd < 0)
                    break; // If index out of bound

                if (!buttons[rd][cd].getText().equals(word.substring(k, k + 1))) // If word not matched
                    break;

                if (buttons[rd][cd].getText().equals(word.substring(k, k + 1))) {
                }
                rd += x[dir]; // Moving in particular direction
                cd += y[dir];
            }

            if (k == len) // If all character matched, then value == length of word
                return true;
        }
        return false;
    }

    // Searches SOS
    boolean hasWon(JButton[][] buttons) {
        for (int row = 0; row < TOTALROWS; row++) {
            for (int col = 0; col < TOTALCOLUMNS; col++) {
                if (search(buttons, row, col, "SOS"))
                    return true;
            }
        }
        return false;
    }

    public GameState getGameState() {
        return currentGameState;
    }

}
