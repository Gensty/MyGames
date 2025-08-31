package pl.gensty.games.Snake.apple.factory;

import pl.gensty.games.Snake.apple.Apple;
import pl.gensty.games.Snake.apple.GoldenApple;

import java.awt.*;

public class GoldenAppleFactory implements AppleFactory {
    @Override
    public Apple createApple(Point point) {
        return new GoldenApple(point);
    }
}
