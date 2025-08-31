package pl.gensty.games.Snake.snake;

import pl.gensty.games.Snake.SnakeSettings;

import java.awt.Point;
import java.util.List;

public class SnakeMover {
    public SnakeMover(){}

    public void move(Snake snake) {
        List<Point> body = snake.getBody();

        for (int i = body.size() - 1; i > 0; i--) {
            body.set(i, new Point(body.get(i - 1)));
        }

        Point head = snake.getHead();
        switch (snake.getDirection()) {
            case UP -> head.translate(0, -SnakeSettings.UNIT_SIZE);
            case DOWN -> head.translate(0, SnakeSettings.UNIT_SIZE);
            case LEFT -> head.translate(-SnakeSettings.UNIT_SIZE, 0);
            case RIGHT -> head.translate(SnakeSettings.UNIT_SIZE, 0);
        }

        body.set(0, head);
    }
}
