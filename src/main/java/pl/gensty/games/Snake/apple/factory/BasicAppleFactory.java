package pl.gensty.games.Snake.apple.factory;

import pl.gensty.games.Snake.apple.Apple;
import pl.gensty.games.Snake.apple.BasicApple;

import java.awt.*;

public class BasicAppleFactory implements AppleFactory {
    @Override
    public Apple createApple(Point point) {
        return new BasicApple(point);
    }
}
