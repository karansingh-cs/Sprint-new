package product;

import java.awt.event.*;
import java.util.List;

import javax.swing.*;

public class GameFileManager {

    public static void logAction(String logActionContent) {
        String contentWithNewLine = logActionContent + "\n";
        try {
            GameFileWriter.writeIntoFile(contentWithNewLine);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

    public static void setActionListenerToButtons(List<JRadioButton> buttonList) {
        for (JRadioButton button : buttonList) {
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            if (button.isSelected()) {
                                logAction(button.getName() + " was selected");
                            } else {
                                logAction(button.getName() + " was unselected");
                            }
                        }
                    });
                }
            });
        }
    }
}

