package pl.gensty;

import pl.gensty.games.Snake.SnakeGameFrame;
import pl.gensty.panels.ExitPanel;
import pl.gensty.panels.GamesPanel;
import pl.gensty.utils.Game;
import pl.gensty.utils.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamesHub extends JFrame {
    public GamesHub() {
        setTitle("GameHub");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Screen.WIDTH, Screen.HEIGHT);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        List<Game> games = gamesList();

        GamesPanel gamesPanel = new GamesPanel(games);

        ExitPanel exitPanel = new ExitPanel(e -> System.exit(0));

        add(gamesPanel, BorderLayout.CENTER);
        add(exitPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GamesHub gamesHub = new GamesHub();
            gamesHub.setVisible(true);
        });
    }

    private List<Game> gamesList() {
        return List.of(
                new Game("Snake", e -> new SnakeGameFrame()),
                new Game("TicTacToe", e -> ticTacToeFrame()),
                new Game("Chess", e -> chessFrame())
        );
    }

    private void ticTacToeFrame() {
        JOptionPane.showMessageDialog(this, "Coming soon");
    }

    private void chessFrame(){
        JOptionPane.showMessageDialog(this,"Coming soon");
    }
}