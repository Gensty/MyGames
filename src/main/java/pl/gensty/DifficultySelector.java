package pl.gensty;

import pl.gensty.enums.Difficulty;

import javax.swing.*;

import static pl.gensty.enums.Difficulty.*;

public class DifficultySelector {
    public static Difficulty chooseDifficulty() {
        Difficulty[] options = values();
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose difficulty:",
                "Select Difficulty",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        return (choice >= 0 && choice < options.length) ? options[choice] : EASY;
    }
}
