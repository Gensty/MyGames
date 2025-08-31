package pl.gensty.games.Snake.apple;

import pl.gensty.games.Snake.snake.Snake;

import java.awt.*;
import java.util.Random;

import static pl.gensty.games.Snake.SnakeSettings.*;

public class ApplePositioner {
    private final Random random = new Random();

    public Point findFreePosition(Snake snake) {
        Point point;
        boolean onSnake;

        int maxCol = SCREEN_WIDTH / UNIT_SIZE;
        int maxRow = SCREEN_HEIGHT / UNIT_SIZE;

        do {
            int appleX = random.nextInt(maxCol) * UNIT_SIZE;
            int appleY = random.nextInt(maxRow) * UNIT_SIZE;

            point = new Point(appleX, appleY);
            onSnake = snake.getBody().contains(point);
        } while (onSnake);

        return point;
    }
}
