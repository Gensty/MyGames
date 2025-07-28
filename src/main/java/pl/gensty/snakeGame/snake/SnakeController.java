package pl.gensty.snakeGame.snake;

import pl.gensty.snakeGame.Settings;
import pl.gensty.snakeGame.apple.Apple;
import pl.gensty.snakeGame.apple.AppleCreator;
import pl.gensty.snakeGame.snake.Snake;
import pl.gensty.snakeGame.snake.SnakeCollisionChecker;
import pl.gensty.snakeGame.snake.SnakeMover;
import pl.gensty.snakeGame.snake.SnakeWrapHandler;

public class SnakeController {
    private final Snake snake;
    private final SnakeMover mover;
    private final SnakeCollisionChecker collisionChecker;
    private final SnakeWrapHandler wrapHandler;
    private final AppleCreator appleCreator;
    private final Settings settings;
    private Apple apple;
    private int score = 0;
    private boolean running = false;


    public SnakeController(Settings settings,
                           Snake snake,
                           SnakeMover mover,
                           SnakeCollisionChecker collisionChecker,
                           SnakeWrapHandler wrapHandler,
                           AppleCreator appleCreator) {
        this.settings = settings;
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

        if (!settings.wrapEnabled() && collisionChecker.isOutOfBounds(snake)) {
            running = false;
        }

        if (settings.wrapEnabled()) {
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
