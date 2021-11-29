package product;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import product.Game.GameState;

public class GUI implements ActionListener {

    public static final int CELL_SIZE = 100;

    int blueCount = 0;
    int redCount = 0;

    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JPanel toppanel = new JPanel();
    JLabel toptext = new JLabel();
    JPanel leftpanel = new JPanel();
    JLabel lefttext = new JLabel();
    JPanel rightpanel = new JPanel();
    JLabel righttext = new JLabel();
    JLabel gameStatusBar = new JLabel();
    JLabel ptext = new JLabel();
    JButton[][] buttons = new JButton[8][8];

    JButton newgame = new JButton("New Game");

    Random random = new Random();
    JRadioButton bS = new JRadioButton("S");
    JRadioButton bO = new JRadioButton("O");
    JRadioButton rS = new JRadioButton("S");
    JRadioButton rO = new JRadioButton("O");

    JRadioButton sgame = new JRadioButton("Simple game");
    JRadioButton ggame = new JRadioButton("General game");
    JRadioButton bhuman = new JRadioButton("Human");
    JRadioButton bcomputer = new JRadioButton("Computer");
    JRadioButton rhuman = new JRadioButton("Human");
    JRadioButton rcomputer = new JRadioButton("Computer");

    private Game game;

    public GUI() {
        this(new Game());
    }

    public GUI(Game game) {

        this.game = game;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.getContentPane().setBackground(Color.cyan);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        // Game selection buttons
        ButtonGroup selectGame = new ButtonGroup();
        selectGame.add(sgame);
        selectGame.add(ggame);

        // Blue Player S or O
        ButtonGroup blue = new ButtonGroup();
        blue.add(bS);
        blue.add(bO);

        ButtonGroup blueside = new ButtonGroup();
        blueside.add(bhuman);
        blueside.add(bcomputer);

        // Red Player S or O
        ButtonGroup red = new ButtonGroup();
        red.add(rS);
        red.add(rO);

        ButtonGroup redside = new ButtonGroup();
        redside.add(rhuman);
        redside.add(rcomputer);

        // Game Mode selection
        toppanel.setLayout(new BorderLayout());
        toppanel.setBounds(100, 500, 100, 800);
        JPanel top = new JPanel(new GridLayout(0, 3));
        toptext.setText("SOS");
        top.add(toptext);
        toptext.setHorizontalAlignment(JLabel.CENTER);
        top.add(sgame);
        top.add(ggame);
        toppanel.add(top);

        // Blue Player side
        leftpanel.setLayout(new BorderLayout());
        leftpanel.setBounds(0, 0, 50, 400);
        JPanel leftside = new JPanel(new GridLayout(6, 0));
        lefttext.setText("Blue player");
        lefttext.setHorizontalAlignment(JLabel.CENTER);
        leftside.add(lefttext);
        ((AbstractButton) leftside.add(bhuman)).setHorizontalAlignment(JLabel.CENTER);
        ((AbstractButton) leftside.add(bS)).setHorizontalAlignment(JLabel.CENTER);
        ((AbstractButton) leftside.add(bO)).setHorizontalAlignment(JLabel.CENTER);
        ((AbstractButton) leftside.add(bcomputer)).setHorizontalAlignment(JLabel.CENTER);
        leftpanel.add(leftside);

        // Red Player side
        rightpanel.setLayout(new BorderLayout());
        rightpanel.setBounds(100, 500, 100, 800);
        JPanel rightside = new JPanel(new GridLayout(6, 0));
        righttext.setText("Red player");
        righttext.setHorizontalAlignment(JLabel.CENTER);
        rightside.add(righttext);
        ((AbstractButton) rightside.add(rhuman)).setHorizontalAlignment(JLabel.CENTER);
        ((AbstractButton) rightside.add(rS)).setHorizontalAlignment(JLabel.CENTER);
        ((AbstractButton) rightside.add(rO)).setHorizontalAlignment(JLabel.CENTER);
        ((AbstractButton) rightside.add(rcomputer)).setHorizontalAlignment(JLabel.CENTER);

        rightside.add(newgame);
        newgame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameStatusBar.setText("Working");
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new GUI(new Game(game.getTotalRows(), game.getTotalColumns()));
                    }
                });
            }
        });

        rightpanel.add(rightside);

        // Turn Panel at bottom
        gameStatusBar.setBackground(Color.DARK_GRAY);
        gameStatusBar.setForeground(Color.white);
        gameStatusBar.setFont(new Font("Ink Free", Font.BOLD, 30));
        gameStatusBar.setHorizontalAlignment(JLabel.CENTER);
        gameStatusBar.setText("Curren turn: ");
        gameStatusBar.setOpaque(true);
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);
        title_panel.add(gameStatusBar);

        // Adjust the Status Bar
        {
            if (game.getGameState() == GameState.PLAYING) {
                gameStatusBar.setForeground(Color.BLACK);
                if (game.getTurn() == "Blue") {
                    gameStatusBar.setText("Blue's Turn");
                } else {
                    gameStatusBar.setText("Red's Turn");
                }
            } else if (game.getGameState() == GameState.DRAW) {
                gameStatusBar.setForeground(Color.RED);
                gameStatusBar.setText("It's a Draw! Click to play again.");
            } else if (game.getGameState() == GameState.BLUE_WON) {
                gameStatusBar.setForeground(Color.RED);
                gameStatusBar.setText("Blue Won! Click to play again.");
            } else if (game.getGameState() == GameState.RED_WON) {
                gameStatusBar.setForeground(Color.RED);
                gameStatusBar.setText("Red Won! Click to play again.");
            }
        }

        // Grid-panel
        button_panel.setLayout(new GridLayout(game.getTotalRows(), game.getTotalColumns()));
        button_panel
                .setPreferredSize(new Dimension(CELL_SIZE * game.getTotalRows(), CELL_SIZE * game.getTotalColumns()));
        button_panel.setBackground(Color.gray);
        frame.add(title_panel, BorderLayout.SOUTH);
        frame.add(toppanel, BorderLayout.NORTH);
        frame.add(leftpanel, BorderLayout.WEST);
        frame.add(rightpanel, BorderLayout.EAST);
        frame.add(button_panel);

        // numbers of cells
        for (int i = 0; i < game.getTotalRows(); i++) {
            for (int j = 0; j < game.getTotalColumns(); j++) {
                buttons[i][j] = new JButton();
                button_panel.add(buttons[i][j]);
                buttons[i][j].setFont(new Font("MV Boli", Font.BOLD, 24));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
                buttons[i][j].setBackground(Color.white);

            }
        }
    }

    public JRadioButton getLetterSelected(JRadioButton sBtn, JRadioButton oBtn) {
        if (sBtn.isSelected()) {
            return sBtn;
        }
        return oBtn;
    }

    public JRadioButton getGameSelection(JRadioButton simpleBtn, JRadioButton generalBtn) {
        if (simpleBtn.isSelected()) {
            return simpleBtn;
        }
        return generalBtn;
    }

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
                                // buttons[i][j].setText("S");
                                buttons[i][j].setText(getLetterSelected(bS, bO).getText());
                                game.turn = (game.turn == "Blue") ? "Red" : "Blue";
                            }
                        } else {
                            if (buttons[i][j].getText() == "") {
                                gameStatusBar.setText("BLUE");
                                buttons[i][j].setForeground(Color.RED);
                                // buttons[i][j].setText("O");
                                buttons[i][j].setText(getLetterSelected(rS, rO).getText());
                                game.turn = (game.turn == "Red") ? "Blue" : "Red";
                            }
                        }

                        if (!buttons[i][j].getText().equals("")) {

                            gameStatusBar.setText(game.getGameState() + "! Click to play again."); // an empty cell
                                                                                                   // found,
                                                                                                   // not draw
                        }

                        Boolean hasWon = game.hasWon(buttons);

                        if (hasWon) {

                            if (sgame.isSelected()) {
                                game.turn = (game.turn == "Red") ? "Blue" : "Red";
                                game.currentGameState = (game.getTurn() == "Blue") ? GameState.BLUE_WON
                                        : GameState.RED_WON;
                                for (int r = 0; r < game.getTotalRows(); r++) {
                                    for (int c = 0; c < game.getTotalColumns(); c++) {
                                        buttons[r][c].setEnabled(false);
                                    }
                                }
                                gameStatusBar.setText(game.getGameState() + " Won! Click to play again.");
                                game.resetGame();

                            } else if (ggame.isSelected()) {

                                if (hasWon) {
                                    if (game.getTurn() == "Blue") {
                                        ++blueCount;
                                        gameStatusBar.setText(game.getTurn() + "Count : " + blueCount);
                                    } else {
                                        ++redCount;
                                        gameStatusBar.setText(game.getTurn() + "Count : " + redCount);
                                    }
                                }

                                hasWon = false;

                            }

                        }

                    }
                }
            }
        }

        if (game.isDraw()) {
            // game.currentGameState = GameState.DRAW;
            gameStatusBar.setText(game.getGameState() + "! Click to play again.");
            // game.resetGame();
            // for (int r = 0; r < game.getTotalRows(); r++) {
            // for (int c = 0; c < game.getTotalColumns(); c++) {
            // buttons[r][c].setEnabled(false);
            // }
            // }

        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI(new Game());
            }
        });
    }
}
