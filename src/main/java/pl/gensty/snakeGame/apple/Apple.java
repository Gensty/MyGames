package pl.gensty.snakeGame.apple;

import java.awt.*;

public abstract class Apple {
    protected Point point;

    public Apple(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public abstract void render(Graphics g);
}
