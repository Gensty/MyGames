package pl.gensty.games.Snake.snake;

import pl.gensty.games.Snake.SnakeSettings;

public class SnakeWrapHandler {
    public void applyWrap(Snake snake) {
        if (snake.getHead().x < 0) {
            snake.getHead().x = SnakeSettings.SCREEN_WIDTH - SnakeSettings.UNIT_SIZE;
        } else if (snake.getHead().x >= SnakeSettings.SCREEN_WIDTH) {
            snake.getHead().x = 0;
        }

        if (snake.getHead().y < 0) {
            snake.getHead().y = SnakeSettings.SCREEN_HEIGHT - SnakeSettings.UNIT_SIZE;
        } else if (snake.getHead().y >= SnakeSettings.SCREEN_HEIGHT) {
            snake.getHead().y = 0;
        }
    }
}
