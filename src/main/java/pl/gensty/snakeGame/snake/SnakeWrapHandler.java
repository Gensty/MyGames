package pl.gensty.snakeGame.snake;

import static pl.gensty.snakeGame.Settings.*;

public class SnakeWrapHandler {
    public void applyWrap(Snake snake) {
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
    }
}
