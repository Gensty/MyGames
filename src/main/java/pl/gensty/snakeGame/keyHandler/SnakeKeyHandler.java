package pl.gensty.snakeGame.keyHandler;

import pl.gensty.snakeGame.snake.Snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static pl.gensty.enums.Direction.*;

public class SnakeKeyHandler extends KeyAdapter {
    private final Snake snake;
    private final boolean running;
    private final Runnable restart;

    public SnakeKeyHandler(Snake snake, boolean running, Runnable restart) {
        this.snake = snake;
        this.running = running;
        this.restart = restart;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (running) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> snake.setDirection(LEFT);
                case KeyEvent.VK_RIGHT -> snake.setDirection(RIGHT);
                case KeyEvent.VK_UP -> snake.setDirection(UP);
                case KeyEvent.VK_DOWN -> snake.setDirection(DOWN);
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                restart.run();
            }
        }
    }
}
