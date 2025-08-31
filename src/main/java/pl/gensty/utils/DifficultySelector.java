package pl.gensty.utils;

import pl.gensty.utils.enums.Difficulty;

import javax.swing.*;

import static pl.gensty.utils.enums.Difficulty.*;

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
