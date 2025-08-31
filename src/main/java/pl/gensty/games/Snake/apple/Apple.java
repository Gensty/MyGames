package pl.gensty.games.Snake.apple;

import java.awt.*;

public abstract class Apple {
    protected Point point;

    public Apple(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public int getScorePoints() {
        return 0;
    }
}
