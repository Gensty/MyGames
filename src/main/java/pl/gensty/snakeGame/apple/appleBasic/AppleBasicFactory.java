package pl.gensty.snakeGame.apple.appleBasic;

import pl.gensty.snakeGame.apple.Apple;
import pl.gensty.snakeGame.apple.AppleFactory;

import java.awt.*;

public class AppleBasicFactory implements AppleFactory {
    @Override
    public Apple createApple(Point point) {
        return new AppleBasic(point);
    }
}
