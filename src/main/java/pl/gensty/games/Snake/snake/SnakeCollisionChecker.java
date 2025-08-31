package pl.gensty.games.Snake.snake;

import pl.gensty.games.Snake.SnakeSettings;

import java.awt.*;

public class SnakeCollisionChecker {
    public SnakeCollisionChecker() {
    }

    public boolean checkSelfCollision(Snake snake) {
        Point head = snake.getHead();
        return snake.getBody().subList(1, snake.getBody().size()).contains(head);
    }

    public boolean isOutOfBounds(Snake snake) {
        Point head = snake.getHead();
        return head.x < 0 || head.x >= SnakeSettings.SCREEN_WIDTH || head.y < 0 || head.y >= SnakeSettings.SCREEN_HEIGHT;
    }
}
