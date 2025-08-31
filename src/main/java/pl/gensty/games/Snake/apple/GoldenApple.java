package pl.gensty.games.Snake.apple;

import java.awt.*;

public class GoldenApple extends Apple {
    public GoldenApple(Point point) {
        super(point);
    }

    @Override
    public int getScorePoints() {
        return 2;
    }
}
