package product;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Auto extends Game implements ActionListener {

    Random random = new Random();
    int so = random.nextInt(2);



    @Override
        public void actionPerformed(ActionEvent e) {
            if (game.getGameState() == GameState.PLAYING) {
                for (int i = 0; i < game.getTotalRows(); i++) {
                    for (int j = 0; j < game.getTotalColumns(); j++) {
                        if (e.getSource() == buttons[i][j]) {
                            if (game.getTurn() == "Blue") {
                                if (buttons[i][j].getText().equals("")) {
                                    gameStatusBar.setText("RED");
                                    buttons[i][j].setForeground(Color.BLUE);
                                    buttons[i][j].setText(getLetterSelected(bS, bO).getText());
                                    game.turn = (game.turn == "Blue") ? "Red" : "Blue";
                                }
                            } else {
                                if (buttons[i][j].getText() == "") {
                                    gameStatusBar.setText("BLUE");
                                    buttons[i][j].setForeground(Color.RED);
                                    buttons[i][j].setText(getLetterSelected(rS, rO).getText());
                                    game.turn = (game.turn == "Red") ? "Blue" : "Red";
                                }
                            }
    
                            Boolean hasWon = game.hasWon(buttons);
    
                            if (hasWon) {
    
                                if (sgame.isSelected()) {
                                    for (int r = 0; r < game.getTotalRows(); r++) {
                                        for (int c = 0; c < game.getTotalColumns(); c++) {
                                            buttons[r][c].setEnabled(false);
                                        }
                                    }
                                    gameStatusBar.setText(game.getGameState() + " Won! Click to play again.");
    
                                } else if (ggame.isSelected()) {
    
                                    if (game.getTurn() == "Blue") {
                                        ++blueCount;
                                        gameStatusBar.setText(game.getTurn() + "Blue Count : " + blueCount);
                                    } else {
    
                                        gameStatusBar.setText(game.getTurn() + "Blue Count : " + blueCount);
                                    }
    
                                    if (game.getTurn() == "Red") {
                                        ++redCount;
                                        gameStatusBar.setText(game.getTurn() + "Red Count : " + redCount);
                                    }
    
                                }
                                hasWon = false;
    
                            }
    
                            // For simple game
                            if (isAllCellsFilled() && !hasWon) {
                                gameStatusBar.setText("DRAW! Click to play again."); // an empty cell found
                            }
    
                            // For general game
                            else if (isAllCellsFilled() && !hasWon && (blueCount == redCount)) {
                                gameStatusBar.setText("DRAW! Click to play again."); // an empty cell found
                            }
    
                        }
                    }
                }
            }
    
        }

   
    public AutoGame() {
                this("O");
            }

    public AutoGame(String player) {
                this.autoPlayer = player;
                if (autoPlayer == "Blue") {
                    makeFirstXMove();
                }
            }

    @Override
    public void resetGame() {
        super.resetGame();
        if (autoPlayer == "Blue") {
            makeFirstXMove();
        }
    }

    private void makeFirstXMove() {
        Random random = new Random();
        int position = random.nextInt(TOTALROWS * TOTALCOLUMNS);
        super.makeMove(position / TOTALROWS, position % TOTALCOLUMNS);
    }

    @Override
    public void makeMove(int row, int column) {
        if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS
                && buttons[row][column].getText() == "") {
            super.makeMove(row, column);
            if (turn == autoPlayer && getGameState() == GameState.PLAYING) {
                makeAutoMove();
            }
        }
    }

    private void makeAutoMove() {
        if (!makeWinningMove()) {
            if (!blockOpponentWinningMove())
                makeRandomMove();
        }

    }

    private boolean makeWinningMove() {
        return false;
    }

    private boolean blockOpponentWinningMove() {
        return false;
    }

    private void makeRandomMove() {
        int numberOfEmptyCells = getNumberOfEmptyCells();
        Random random = new Random();
        int targetMove = random.nextInt(numberOfEmptyCells);
        int index = 0;
        for (int row = 0; row < TOTALROWS; ++row) {
            for (int col = 0; col < TOTALCOLUMNS; ++col) {
                if (buttons[row][col].getText() == "") {
                    if (targetMove == index) {
                        super.makeMove(row, col);
                        return;
                    } else
                        index++;
                }
            }
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

}

}
