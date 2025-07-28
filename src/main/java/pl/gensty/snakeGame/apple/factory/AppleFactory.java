package pl.gensty.snakeGame.apple.factory;

import pl.gensty.snakeGame.apple.Apple;

import java.awt.*;

public interface AppleFactory {
    Apple createApple(Point point);
}
