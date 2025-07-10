package pl.gensty.snakeGame;

import pl.gensty.enums.Difficulty;
import pl.gensty.snakeGame.apple.Apple;
import pl.gensty.snakeGame.apple.AppleFactory;
import pl.gensty.snakeGame.apple.ApplePositioner;
import pl.gensty.snakeGame.apple.appleBasic.AppleBasicFactory;
import pl.gensty.snakeGame.keyHandler.SnakeKeyHandler;
import pl.gensty.snakeGame.snake.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static pl.gensty.snakeGame.GameConfigSnake.*;
import static pl.gensty.enums.Difficulty.*;

public class GamePanel extends JPanel implements ActionListener {
    private final ApplePositioner applePositioner = new ApplePositioner();
    private final AppleFactory appleFactory = new AppleBasicFactory();

    private Apple apple;
    private Snake snake;
    private boolean wrapEnabled;
    private int applesEaten;
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        snake = new Snake();
        this.addKeyListener(new SnakeKeyHandler(snake, true, this::startGame));

        startGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            snake.move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public void startGame(){
        if (timer != null) {
            timer.stop();
        }

        chooseDifficulty();
        snake = new Snake();
        apple = createApple();
        applesEaten = 0;
        running = true;
        this.addKeyListener(new SnakeKeyHandler(snake, running, this::startGame));

        timer.start();
    }

    private void chooseDifficulty() {
        Difficulty[] options = {EASY, MEDIUM, HARD};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose level difficulty:",
                "Snake",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        int delay;
        switch (choice) {
            case 1 -> { // MEDIUM
                wrapEnabled = true;
                delay = BASE_DELAY - 15;
            }
            case 2 -> { // HARD
                wrapEnabled = false;
                delay = BASE_DELAY - 25;
            }
            default -> { // EASY (domyślnie)
                wrapEnabled = true;
                delay = BASE_DELAY;
            }
        }

        timer = new Timer(delay, this);
    }

    private Apple createApple() {
        Point point = applePositioner.findFreePosition(snake);
        return appleFactory.createApple(point);
    }

    private void checkApple() {
        if((snake.getHead().getX() == apple.getPoint().getX()) && (snake.getHead().getY() == apple.getPoint().getY())) {
            snake.grow();
            applesEaten++;
            apple = createApple();
        }
    }

    private void checkCollisions() {
        if (snake.checkSelfCollision()) {
            running = false;
        }

        if (!wrapEnabled) {
            if (snake.isOutOfBounds()) {
                running = false;
            }
        }

        if (wrapEnabled) {
            if (snake.getHead().x < 0) {
                snake.getHead().x = SCREEN_WIDTH - UNIT_SIZE;
            } else if (snake.getHead().x >= SCREEN_WIDTH) {
                snake.getHead().x = 0;
            }

            if (snake.getHead().y < 0) {
                snake.getHead().y = SCREEN_HEIGHT - UNIT_SIZE;
            } else if (snake.getHead().y >= SCREEN_HEIGHT) {
                snake.getHead().y = 0;
            }
        } else {
            if (snake.getHead().x < 0 || snake.getHead().x >= SCREEN_WIDTH || snake.getHead().y < 0 || snake.getHead().y >= SCREEN_HEIGHT) {
                running = false;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (running) {
            displayBoard(g);
            displayApple(g);
            displaySnake(g);
            displayPoints(g);
        } else {
            gameOver(g);
        }
    }

    private void displayBoard (Graphics g) {
        for(int i=0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
    }

    private void displayApple(Graphics g) {
        if (apple != null) {
            apple.render(g);
        }
    }

    private void displaySnake(Graphics g) {
        if (snake != null) {
            snake.render(g);
        }
    }

    private void displayPoints(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Gensty", Font.BOLD, 25));
        FontMetrics metrics = getFontMetrics(g.getFont());
        String scoreText = "Points: " + applesEaten;
        g.drawString(scoreText, (SCREEN_WIDTH - metrics.stringWidth(scoreText)) / 2, g.getFont().getSize());
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Gensty", Font.BOLD, 75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2 - 50);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Gensty", Font.PLAIN, 25));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        String retryText = "PLAY AGAIN? (PRESS ENTER)";
        g.drawString(retryText, (SCREEN_WIDTH - metrics2.stringWidth(retryText)) / 2, SCREEN_HEIGHT / 2 + 20);
    }
}
