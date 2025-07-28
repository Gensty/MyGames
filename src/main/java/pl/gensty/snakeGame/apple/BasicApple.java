package pl.gensty.snakeGame.apple;

import java.awt.*;

public class BasicApple extends Apple {
    public BasicApple(Point point) {
        super(point);
    }

    @Override
    public int getScorePoints() {
        return 1;
    }
}
