package pl.gensty.snakeGame;

import pl.gensty.Displayer;
import pl.gensty.snakeGame.apple.Apple;
import pl.gensty.snakeGame.apple.displayer.AppleDisplayer;
import pl.gensty.snakeGame.snake.Snake;
import pl.gensty.snakeGame.snake.SnakeController;
import pl.gensty.snakeGame.snake.displayer.SnakeDisplayer;

import java.awt.*;

import static pl.gensty.snakeGame.Settings.*;

public class GameRenderer {
    private final Displayer<Snake> snakeDisplayer = new SnakeDisplayer();
    private final Displayer<Apple> appleDisplayer = new AppleDisplayer();
    private final SnakeController controller;

    public GameRenderer(SnakeController controller) {
        this.controller = controller;
    }

    public void render(Graphics g) {
        if (controller.isRunning()) {
            drawBoard(g);
            drawApple(g);
            drawSnake(g);
            drawScore(g);
        } else {
            drawGameOver(g);
        }
    }

    private void drawBoard(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
    }

    private void drawSnake(Graphics g) {
        if (controller.getSnake() != null) {
            snakeDisplayer.display(g, controller.getSnake());
        }
    }

    private void drawApple(Graphics g) {
        if (controller.getApple() != null) {
            appleDisplayer.display(g, controller.getApple());
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Gensty", Font.BOLD, 25));
        FontMetrics metrics = g.getFontMetrics();
        String text = "Points: " + controller.getScore();
        g.drawString(text, (SCREEN_WIDTH - metrics.stringWidth(text)) / 2, g.getFont().getSize());
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Gensty", Font.BOLD, 75));
        FontMetrics metrics1 = g.getFontMetrics();
        g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2 - 50);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Gensty", Font.PLAIN, 25));
        FontMetrics metrics2 = g.getFontMetrics();
        String retryText = "PLAY AGAIN? (PRESS ENTER)";
        g.drawString(retryText, (SCREEN_WIDTH - metrics2.stringWidth(retryText)) / 2, SCREEN_HEIGHT / 2 + 20);
    }

}
