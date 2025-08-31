package pl.gensty.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ExitPanel extends JPanel {
    public ExitPanel(ActionListener exitListener) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setBackground(Color.BLACK);

        JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(exitListener);
        add(exitButton);
    }
}
