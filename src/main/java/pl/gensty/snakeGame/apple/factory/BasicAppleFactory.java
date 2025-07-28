package pl.gensty.snakeGame.apple.factory;

import pl.gensty.snakeGame.apple.Apple;
import pl.gensty.snakeGame.apple.BasicApple;

import java.awt.*;

public class BasicAppleFactory implements AppleFactory {
    @Override
    public Apple createApple(Point point) {
        return new BasicApple(point);
    }
}
