package pl.gensty.snakeGame.apple.factory;

import pl.gensty.snakeGame.apple.Apple;
import pl.gensty.snakeGame.apple.GoldenApple;

import java.awt.*;

public class GoldenAppleFactory implements AppleFactory {
    @Override
    public Apple createApple(Point point) {
        return new GoldenApple(point);
    }
}
