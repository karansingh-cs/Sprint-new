package product;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Auto extends Game{

    Random random = new Random();
    int so = random.nextInt(2);
    
   
    public Auto() {
       String autoPlayer = "Blue";
       if (autoPlayer == "Blue") {
    	   blueRandomMove();
       }
    }       

    public void resetGame() {
        super.resetGame();
    }

    public void makeRandomMove() {
        int numberOfEmptyCells = getNumberOfEmptyCells();
        Random random = new Random();
        int so = random.nextInt(2);
        int targetMove = random.nextInt(numberOfEmptyCells);
        int index = 0;
        for (int i = 0; i < getTotalRows(); ++i) {
            for (int j = 0; j < getTotalColumns(); ++j) {
                if (buttons[i][j].getText() == "") {
                    if (targetMove == index) {
                        if (getTurn() == "Blue") {
                            while (buttons[i][j].getText().equals("")) {
                                if (buttons[i][j].getText().equals("")) {
                                    buttons[i][j].setForeground(Color.BLUE);
                                    buttons[i][j].setText("S");
                                    turn = (turn == "Blue") ? "Red" : "Blue";
                                    break;
                                }
                            }
                        } else if (getTurn() == "Red") {
                            while (buttons[i][j].getText().equals("")) {
                                if (buttons[i][j].getText().equals("")) {
                                    buttons[i][j].setForeground(Color.RED);
                                    buttons[i][j].setText("O");
                                    turn = (turn == "Red") ? "Blue" : "Red";
                                    break;
                                }
                            }

                        }
                        return;
                    } else
                        index++;
                }
            }
        }
    }

    public void blueRandomMove() {
        int numberOfEmptyCells = getNumberOfEmptyCells();
        Random random = new Random();
        int bSO = random.nextInt(2);
        int targetMove = random.nextInt(numberOfEmptyCells);
        int index = 0;
        for (int i = 0; i < getTotalRows(); ++i) {
            for (int j = 0; j < getTotalColumns(); ++j) {
                if (buttons[i][j].getText() == "") {
                    if (targetMove == index) {
                        if (getTurn() == "Blue") {
                            if (buttons[i][j].getText().equals("")) {
                                buttons[i][j].setForeground(Color.BLUE);
                                buttons[i][j].setText("O");
                               turn = (turn == "Blue") ? "Red" : "Blue";
                            }

                        }
                        return;
                    } else
                        index++;
                }
            }
        }
    }
    
    

    public void redRandomMove() {
        int numberOfEmptyCells = getNumberOfEmptyCells();
        Random random = new Random();
        int rSO = random.nextInt(2);
        int targetMove = random.nextInt(numberOfEmptyCells);
        int index = 0;
        for (int i = 0; i < getTotalRows(); ++i) {
            for (int j = 0; j < getTotalColumns(); ++j) {
                if (buttons[i][j].getText() == "") {
                    if (targetMove == index) {
                        if (getTurn() == "Red") {
                            if (buttons[i][j].getText().equals("")) {
                                buttons[i][j].setForeground(Color.RED);
                                buttons[i][j].setText("O");
                                turn = (turn == "Red") ? "Blue" : "Red";
                            }

                        }
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
