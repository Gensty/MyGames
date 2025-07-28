package pl.gensty.snakeGame.snake;

import pl.gensty.enums.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static pl.gensty.snakeGame.Settings.*;
import static pl.gensty.enums.Direction.*;

public class Snake {
    private final List<Point> body;
    private Direction direction;

    public Snake() {
        this.body = new ArrayList<>();
        reset();
    }

    private void reset() {
        body.clear();
        int startX = SCREEN_WIDTH / 2;
        int startY = SCREEN_HEIGHT / 2;
        for (int i = 0; i < 6; i++) {
            body.add(new Point(startX - i * UNIT_SIZE, startY));
        }
        direction = RIGHT;
    }

    public void grow() {
        body.add(new Point(body.get(body.size() - 1)));
    }

    public List<Point> getBody() {
        return body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction newDirection) {
        if ((this.direction == LEFT && newDirection != RIGHT) ||
                (this.direction == RIGHT && newDirection != LEFT) ||
                (this.direction == UP && newDirection != DOWN) ||
                (this.direction == DOWN && newDirection != UP)) {
            this.direction = newDirection;
        }
    }

    public Point getHead() {
        return body.get(0);
    }
}
