package pl.gensty.panels;

import pl.gensty.utils.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class GamesPanel extends JPanel {
    public GamesPanel(List<Game> games) {
        setLayout(new GridLayout(games.size(), 1, 15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 50,20,50));
        setBackground(Color.BLACK);

        int number = 1;
        for (Game game : games) {
            JButton button = createButton(number + ". " + game.name(), game.actionListener());
            add(button);
            number++;
        }
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.ITALIC, 48));
        button.setBackground(Color.BLACK);
        button.setForeground(new Color(40, 116, 178));
//        button.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        button.addActionListener(listener);

        return button;
    }
}
