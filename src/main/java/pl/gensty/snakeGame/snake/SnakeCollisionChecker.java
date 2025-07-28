package pl.gensty.snakeGame.snake;

import java.awt.*;

import static pl.gensty.snakeGame.Settings.*;

public class SnakeCollisionChecker {
    public SnakeCollisionChecker() {
    }

    public boolean checkSelfCollision(Snake snake) {
        Point head = snake.getHead();
        return snake.getBody().subList(1, snake.getBody().size()).contains(head);
    }

    public boolean isOutOfBounds(Snake snake) {
        Point head = snake.getHead();
        return head.x < 0 || head.x >= SCREEN_WIDTH || head.y < 0 || head.y >= SCREEN_HEIGHT;
    }
}
