package pl.gensty.snakeGame.apple.appleBasic;

import pl.gensty.snakeGame.apple.Apple;

import java.awt.*;
import static pl.gensty.snakeGame.GameConfigSnake.*;

public class AppleBasic extends Apple {
    public AppleBasic(Point point) {
        super(point);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(point.x, point.y, UNIT_SIZE, UNIT_SIZE);
    }
}
