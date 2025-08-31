package pl.gensty.games.Snake;

import pl.gensty.games.Snake.apple.Apple;
import pl.gensty.games.Snake.apple.AppleCreator;
import pl.gensty.games.Snake.snake.Snake;
import pl.gensty.games.Snake.snake.SnakeCollisionChecker;
import pl.gensty.games.Snake.snake.SnakeMover;
import pl.gensty.games.Snake.snake.SnakeWrapHandler;

public class SnakeController {
    private final Snake snake;
    private final SnakeMover mover;
    private final SnakeCollisionChecker collisionChecker;
    private final SnakeWrapHandler wrapHandler;
    private final AppleCreator appleCreator;
    private final SnakeSettings snakeSettings;
    private Apple apple;
    private int score = 0;
    private boolean running = false;

    public SnakeController(SnakeSettings snakeSettings,
                           Snake snake,
                           SnakeMover mover,
                           SnakeCollisionChecker collisionChecker,
                           SnakeWrapHandler wrapHandler,
                           AppleCreator appleCreator) {
        this.snakeSettings = snakeSettings;
        this.snake = snake;
        this.mover = mover;
        this.collisionChecker = collisionChecker;
        this.wrapHandler = wrapHandler;
        this.appleCreator = appleCreator;
    }

    public void start() {
        apple = appleCreator.createApple(snake);
        running = true;
    }

    public void update() {
        if (!running) return;

        mover.move(snake);

        if (collisionChecker.checkSelfCollision(snake)) {
            running = false;
        }

        if (!snakeSettings.wrapEnabled() && collisionChecker.isOutOfBounds(snake)) {
            running = false;
        }

        if (snakeSettings.wrapEnabled()) {
            wrapHandler.applyWrap(snake);
        }

        if (isAppleEaten()) {
            snake.grow();
            score += apple.getScorePoints();
            apple = appleCreator.createApple(snake);
        }
    }

    private boolean isAppleEaten() {
        return snake.getHead().equals(apple.getPoint());
    }

    public Snake getSnake() {
        return snake;
    }

    public Apple getApple() {
        return apple;
    }

    public int getScore() {
        return score;
    }

    public boolean isRunning() {
        return running;
    }
}
