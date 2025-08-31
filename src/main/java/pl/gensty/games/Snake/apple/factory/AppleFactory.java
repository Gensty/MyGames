package pl.gensty.games.Snake.apple.factory;

import pl.gensty.games.Snake.apple.Apple;

import java.awt.*;

public interface AppleFactory {
    Apple createApple(Point point);
}
