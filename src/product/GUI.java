package product;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.util.List;

public class GUI implements ActionListener {

    

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
    public static final int CELL_SIZE = 100;

    Random random = new Random();
    JRadioButton bS = new JRadioButton("S");
    JRadioButton bO = new JRadioButton("O");
    JRadioButton rS = new JRadioButton("S");
    JRadioButton rO = new JRadioButton("O");
    
    JCheckBox check = new JCheckBox("Record");

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

    private void setNamesOfButtons() {
        bS.setName("BS");
        bO.setName("BO");
        rS.setName("RS");
        rO.setName("RO");
        sgame.setName("SimpleGame");
        ggame.setName("GeneralGame");
        bhuman.setName("BHuman");
        bcomputer.setName("BComputer");
        rhuman.setName("RHuman");
        rcomputer.setName("RComputer");

    }

    public GUI(Game game) {

        this.game = game;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.getContentPane().setBackground(Color.cyan);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

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
        leftside.add(check);
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
                        frame.setVisible(false);
                        frame.dispose();
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
        gameStatusBar.setText("Current turn: " + game.getTurn());
        gameStatusBar.setOpaque(true);
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);
        title_panel.add(gameStatusBar);

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

        setNamesOfButtons();

        List<JRadioButton> buttonList = new ArrayList<JRadioButton>();
        buttonList.add(bS);
        buttonList.add(bO);
        buttonList.add(rS);
        buttonList.add(rO);
        buttonList.add(sgame);
        buttonList.add(ggame);
        buttonList.add(bhuman);
        buttonList.add(bcomputer);
        buttonList.add(rhuman);
        buttonList.add(rcomputer);
        GameFileManager.setActionListenerToButtons(buttonList);
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

    public void randomBtnSelectorForRed(int randomNum) {
        if (randomNum == 0) {
            rO.setSelected(false);
            rS.setSelected(true);
        } else {
            rO.setSelected(true);
            rS.setSelected(false);
        }
    }

    public void randomBtnSelectorForBlue(int randomNum) {
        if (randomNum == 0) {
            bO.setSelected(false);
            bS.setSelected(true);
        } else {
            bO.setSelected(true);
            bS.setSelected(false);
        }
    }

    public Boolean isAllCellsFilled() {
        for (int i = 0; i < game.getTotalRows(); i++) {
            for (int j = 0; j < game.getTotalColumns(); j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void makeRandomMove() {
        int numberOfEmptyCells = game.getNumberOfEmptyCells();
        Random random = new Random();
        int so = random.nextInt(2);
        int targetMove = random.nextInt(numberOfEmptyCells);
        int index = 0;
        for (int i = 0; i < game.getTotalRows(); ++i) {
            for (int j = 0; j < game.getTotalColumns(); ++j) {
                if (buttons[i][j].getText() == "") {
                    if (targetMove == index) {
                        if (game.getTurn() == "Blue") {
                            while (buttons[i][j].getText().equals("")) {
                                if (buttons[i][j].getText().equals("")) {
                                    randomBtnSelectorForBlue(so);
                                    gameStatusBar.setText("RED");
                                    buttons[i][j].setForeground(Color.BLUE);
                                    buttons[i][j].setText(getLetterSelected(bS, bO).getText());
                                    game.turn = (game.turn == "Blue") ? "Red" : "Blue";
                                    break;
                                }
                            }
                        } else if (game.getTurn() == "Red") {
                            while (buttons[i][j].getText().equals("")) {
                                if (buttons[i][j].getText().equals("")) {
                                    randomBtnSelectorForRed(so);
                                    gameStatusBar.setText("BLUE");
                                    buttons[i][j].setForeground(Color.RED);
                                    buttons[i][j].setText(getLetterSelected(rS, rO).getText());
                                    game.turn = (game.turn == "Red") ? "Blue" : "Red";
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
        int numberOfEmptyCells = game.getNumberOfEmptyCells();
        Random random = new Random();
        int bSO = random.nextInt(2);
        int targetMove = random.nextInt(numberOfEmptyCells);
        int index = 0;
        for (int i = 0; i < game.getTotalRows(); ++i) {
            for (int j = 0; j < game.getTotalColumns(); ++j) {
                if (buttons[i][j].getText() == "") {
                    if (targetMove == index) {
                        if (game.getTurn() == "Blue") {
                            if (buttons[i][j].getText().equals("")) {
                                randomBtnSelectorForBlue(bSO);
                                gameStatusBar.setText("RED");
                                buttons[i][j].setForeground(Color.BLUE);
                                buttons[i][j].setText(getLetterSelected(bS, bO).getText());
                                game.turn = (game.turn == "Blue") ? "Red" : "Blue";
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
        int numberOfEmptyCells = game.getNumberOfEmptyCells();
        Random random = new Random();
        int rSO = random.nextInt(2);
        int targetMove = random.nextInt(numberOfEmptyCells);
        int index = 0;
        for (int i = 0; i < game.getTotalRows(); ++i) {
            for (int j = 0; j < game.getTotalColumns(); ++j) {
                if (buttons[i][j].getText() == "") {
                    if (targetMove == index) {
                        if (game.getTurn() == "Red") {
                            if (buttons[i][j].getText().equals("")) {
                                randomBtnSelectorForRed(rSO);
                                gameStatusBar.setText("BLUE");
                                buttons[i][j].setForeground(Color.RED);
                                buttons[i][j].setText(getLetterSelected(rS, rO).getText());
                                game.turn = (game.turn == "Red") ? "Blue" : "Red";
                            }

                        }
                        return;
                    } else
                        index++;
                }
            }
        }
    }
    
    
    
    public void blueMove() {
    	for (int i = 0; i < game.getTotalRows(); i++) {
    	    for (int j = 0; j < game.getTotalColumns(); j++) {
    	                if (buttons[i][j].getText() == "") {
    	                    if (game.getTurn() == "Blue") {
    	                        if (buttons[i][j].getText().equals("")) {
    	                            gameStatusBar.setText("RED");
    	                            buttons[i][j].setForeground(Color.BLUE);
    	                            buttons[i][j].setText(getLetterSelected(bS, bO).getText());
    	                            game.turn = (game.turn == "Blue") ? "Red" : "Blue";
    	                        }
    	                    }
    	                }
    	            

    	     }
    	}
    }
    
    
    public void redMove() {
    	for (int i = 0; i < game.getTotalRows(); i++) {
    	    for (int j = 0; j < game.getTotalColumns(); j++) {
    	                if (buttons[i][j].getText() == "") {
    	                    	if (game.getTurn() == "Red") {
    	                            if (buttons[i][j].getText() == "") {
    	                                gameStatusBar.setText("BLUE");
    	                                buttons[i][j].setForeground(Color.RED);
    	                                buttons[i][j].setText(getLetterSelected(rS, rO).getText());
    	                                game.turn = (game.turn == "Red") ? "Blue" : "Red";
    	                            }
    	                        }
    	                  }
    	          
    	      }

    	 }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (game.getGameState() == Game.GameState.PLAYING) {
            for (int i = 0; i < game.getTotalRows(); i++) {
                for (int j = 0; j < game.getTotalColumns(); j++) {
                    if (e.getSource() == buttons[i][j]) {
                        
                        if (bcomputer.isSelected() && rcomputer.isSelected()) { // Blue Computer and Red Computer
                            makeRandomMove();
                        }

                        if (bhuman.isSelected() && rhuman.isSelected()) { // Blue Human and Red Human
                            if (buttons[i][j].getText() == "") {
                                if (game.getTurn() == "Blue") {
                                    if (buttons[i][j].getText().equals("")) {
                                        gameStatusBar.setText("RED");
                                        buttons[i][j].setForeground(Color.BLUE);
                                        buttons[i][j].setText(getLetterSelected(bS, bO).getText());
                                        GameFileManager.logAction(getLetterSelected(bS, bO).getText() + " in row:column " + i + ":" + j + " is placed");
                                        game.turn = (game.turn == "Blue") ? "Red" : "Blue";
                                    }
                                } else if (game.getTurn() == "Red") {
                                    if (buttons[i][j].getText() == "") {
                                        gameStatusBar.setText("BLUE");
                                        buttons[i][j].setForeground(Color.RED);
                                        buttons[i][j].setText(getLetterSelected(rS, rO).getText());
                                        GameFileManager.logAction(getLetterSelected(rS, rO).getText() + " in row:column " + i + ":" + j + " is placed");
                                        game.turn = (game.turn == "Red") ? "Blue" : "Red";
                                    }
                                }
                            }
                        }

                        if (bhuman.isSelected() && rcomputer.isSelected()) { // Blue Human and Red Computer
                            if (buttons[i][j].getText() == "") {
                                if (game.getTurn() == "Blue") {
                                    if (buttons[i][j].getText().equals("")) {
                                        gameStatusBar.setText("RED");
                                        buttons[i][j].setForeground(Color.BLUE);
                                        buttons[i][j].setText(getLetterSelected(bS, bO).getText());
                                        game.turn = (game.turn == "Blue") ? "Red" : "Blue";
                                    }
                                } else if (game.getTurn() == "Red") {
                                    redRandomMove();
                                }
                            }

                        }

                        if (bcomputer.isSelected() && rhuman.isSelected()) { // Blue Computer and Red Human
                            if (buttons[i][j].getText() == "") {
                                if (game.getTurn() == "Blue") {
                                    blueRandomMove();
                                } else if (game.getTurn() == "Red") {
                                    if (buttons[i][j].getText() == "") {
                                        gameStatusBar.setText("BLUE");
                                        buttons[i][j].setForeground(Color.RED);
                                        buttons[i][j].setText(getLetterSelected(rS, rO).getText());
                                        game.turn = (game.turn == "Red") ? "Blue" : "Red";
                                    }
                                }
                            }
                        }

                        {
                            Boolean hasWon = game.hasWon(buttons);

                            if (hasWon) {
                                if (sgame.isSelected()) {
                                    for (int r = 0; r < game.getTotalRows(); r++) {
                                        for (int c = 0; c < game.getTotalColumns(); c++) {
                                            buttons[r][c].setEnabled(false);
                                        }
                                    }
                                    game.turn = (game.turn == "Blue") ? "Red" : "Blue";
                                    gameStatusBar.setBackground(Color.RED);
                                    gameStatusBar.setText(game.getTurn() + " Won! Click to play again.");
                                    GameFileManager.logAction(game.getTurn() + " Won!");
                                    
                                }
                            }

                            if (game.hasWon(buttons)) {
                                if (ggame.isSelected()) {
                                    if (game.getTurn() == "Blue") {
                                        ++redCount;
                                        gameStatusBar.setText("Red Count : " + redCount);
                                        gameStatusBar.setBackground(Color.GRAY);
                                       
                                    } else if (game.getTurn() == "Red") {
                                        ++blueCount;
                                        gameStatusBar.setText("Blue Count : " + blueCount);
                                        gameStatusBar.setBackground(Color.GRAY);

                                    }
                                }

                            }
                            
                            if (isAllCellsFilled() && !hasWon) {
                            	gameStatusBar.setBackground(Color.RED);
                                //gameStatusBar.setText("DRAW!"); // an empty cell found
                            }
                            
                            if (isAllCellsFilled() && hasWon){
                                if (blueCount > redCount) {
                                	gameStatusBar.setBackground(Color.RED);
                                    gameStatusBar.setText("Blue Won"); 
                                } else if (blueCount < redCount) {
                                	gameStatusBar.setBackground(Color.RED);
                                    gameStatusBar.setText("RED won"); 
                                }
 
                           }

                        }

                    }

                }
            }
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