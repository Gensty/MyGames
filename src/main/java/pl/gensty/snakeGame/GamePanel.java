package pl.gensty.snakeGame;

import pl.gensty.DifficultySelector;
import pl.gensty.enums.Difficulty;
import pl.gensty.snakeGame.apple.Apple;
import pl.gensty.snakeGame.apple.AppleCreator;
import pl.gensty.snakeGame.apple.ApplePositioner;
import pl.gensty.snakeGame.apple.appleBasic.AppleBasicFactory;
import pl.gensty.snakeGame.displayer.DisplayerApple;
import pl.gensty.snakeGame.displayer.Displayer;
import pl.gensty.snakeGame.displayer.DisplayerSnake;
import pl.gensty.snakeGame.keyHandler.SnakeKeyHandler;
import pl.gensty.snakeGame.snake.Snake;
import pl.gensty.snakeGame.snake.SnakeCollisionChecker;
import pl.gensty.snakeGame.snake.SnakeMover;
import pl.gensty.snakeGame.snake.SnakeWrapHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static pl.gensty.snakeGame.SettingsSnake.*;

public class GamePanel extends JPanel implements ActionListener {
    private final SnakeMover mover = new SnakeMover();
    private final SnakeCollisionChecker collisionChecker = new SnakeCollisionChecker();
    private final SnakeWrapHandler wrapHandler = new SnakeWrapHandler();
    private final AppleCreator appleCreator = new AppleCreator(new ApplePositioner(), new AppleBasicFactory());
    private final Displayer<Snake> snakeDisplayer = new DisplayerSnake();
    private final Displayer<Apple> appleDisplayer = new DisplayerApple();

    private SettingsSnake settings;

    private Apple apple;
    private Snake snake;
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
            move();
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
        apple = appleCreator.createApple(snake);
        applesEaten = 0;
        running = true;
        this.addKeyListener(new SnakeKeyHandler(snake, running, this::startGame));

        timer.start();
    }

    private void chooseDifficulty() {
        Difficulty difficulty = DifficultySelector.chooseDifficulty();
        settings = SettingsSnake.from(difficulty);

        timer = new Timer(settings.getDelay(), this);
    }

    private void move() {
        mover.move(snake);
    }

    private void checkApple() {
        if((snake.getHead().equals(apple.getPoint()))) {
            snake.grow();
            applesEaten++;
            apple = appleCreator.createApple(snake);
        }
    }

    private void checkCollisions() {
        if (collisionChecker.checkSelfCollision(snake)) {
            running = false;
            return;
        }

        if (settings.wrapEnabled()) {
            wrapHandler.applyWrap(snake);
        } else if (collisionChecker.isOutOfBounds(snake)) {
            running = false;
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
            appleDisplayer.display(g, apple);
        }
    }

    private void displaySnake(Graphics g) {
        if (snake != null) {
            snakeDisplayer.display(g, snake);
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
