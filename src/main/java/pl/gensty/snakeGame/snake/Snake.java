package pl.gensty.snakeGame.snake;

import pl.gensty.enums.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static pl.gensty.snakeGame.GameConfigSnake.*;
import static pl.gensty.enums.Direction.*;

public class Snake {
    private final List<Point> body;
    private Direction direction;
    private int size = 6;

    public Snake() {

        this.body = new ArrayList<>();
        reset();
    }

    public void reset() {
        body.clear();
        int startX = SCREEN_WIDTH / 2;
        int startY = SCREEN_HEIGHT / 2;
        for (int i = 0; i < size; i++) {
            body.add(new Point(startX - i * UNIT_SIZE, startY));
        }
        direction = RIGHT;
    }

    public void move() {
        for (int i = body.size() - 1; i > 0; i--) {
            body.set(i, new Point(body.get(i - 1)));
        }

        Point head = getHead();
        switch (direction) {
            case UP -> head.translate(0, -UNIT_SIZE);
            case DOWN -> head.translate(0, UNIT_SIZE);
            case LEFT -> head.translate(-UNIT_SIZE, 0);
            case RIGHT -> head.translate(UNIT_SIZE, 0);
        }

        body.set(0, head);
    }

    public void grow() {
        body.add(new Point(body.get(body.size() - 1)));
        size++;
    }

    public void setDirection(Direction newDirection) {
        if ((this.direction == LEFT && newDirection != RIGHT) ||
                (this.direction == RIGHT && newDirection != LEFT) ||
                (this.direction == UP && newDirection != DOWN) ||
                (this.direction == DOWN && newDirection != UP)) {
            this.direction = newDirection;
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < body.size(); i++) {
            g.setColor(i == 0 ? Color.GREEN : new Color(45, 180, 0));
            Point p = body.get(i);
            g.fillRect(p.x, p.y, UNIT_SIZE, UNIT_SIZE);
        }
    }

    public boolean checkSelfCollision() {
        Point head = getHead();
        return body.subList(1, body.size()).contains(head);
    }

    public boolean isOutOfBounds() {
        Point head = getHead();
        return head.x < 0 || head.x >= SCREEN_WIDTH || head.y < 0 || head.y >= SCREEN_HEIGHT;
    }

    public List<Point> getBody() {
        return body;
    }

    public int getSize() {
        return size;
    }

    public Point getHead() {
        return body.get(0);
    }
}
