package pl.gensty.games.Snake.apple;

import pl.gensty.games.Snake.apple.factory.AppleFactory;
import pl.gensty.games.Snake.snake.Snake;

import java.awt.*;

public class AppleCreator {
    private final ApplePositioner applePositioner;
    private final AppleFactory appleFactory;

    public AppleCreator(ApplePositioner applePositioner, AppleFactory appleFactory) {
        this.applePositioner = applePositioner;
        this.appleFactory = appleFactory;
    }

    public Apple createApple(Snake snake) {
        Point point = applePositioner.findFreePosition(snake);
        return appleFactory.createApple(point);
    }

}
