package pl.gensty.snakeGame.apple;

import pl.gensty.snakeGame.apple.factory.AppleFactory;
import pl.gensty.snakeGame.snake.Snake;

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
