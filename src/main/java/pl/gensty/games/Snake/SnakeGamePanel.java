package pl.gensty.games.Snake;

import pl.gensty.games.Snake.apple.AppleCreator;
import pl.gensty.games.Snake.apple.ApplePositioner;
import pl.gensty.games.Snake.apple.factory.BasicAppleFactory;
import pl.gensty.games.Snake.apple.factory.GoldenAppleFactory;
import pl.gensty.games.Snake.apple.factory.RandomAppleFactory;
import pl.gensty.games.Snake.snake.Snake;
import pl.gensty.games.Snake.snake.SnakeCollisionChecker;
import pl.gensty.games.Snake.snake.SnakeMover;
import pl.gensty.games.Snake.snake.SnakeWrapHandler;
import pl.gensty.utils.DifficultySelector;
import pl.gensty.utils.enums.Difficulty;
import pl.gensty.games.Snake.keyHandler.SnakeKeyHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGamePanel extends JPanel implements ActionListener {
    private GameRenderer renderer;
    private SnakeController snakeController;
    private SnakeSettings snakeSettings;
    Timer timer;

    public SnakeGamePanel() {
        this.setPreferredSize(new Dimension(SnakeSettings.SCREEN_WIDTH, SnakeSettings.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        initController();
        this.addKeyListener(new SnakeKeyHandler(snakeController.getSnake(), true, this::startGame));

        startGame();
    }

    private void initController() {
        Difficulty difficulty = DifficultySelector.chooseDifficulty();
        snakeSettings = SnakeSettings.from(difficulty);
        Snake snake = new Snake();
        SnakeMover snakeMover = new SnakeMover();
        SnakeCollisionChecker collisionChecker = new SnakeCollisionChecker();
        SnakeWrapHandler wrapHandler = new SnakeWrapHandler();

        RandomAppleFactory appleFactory = new RandomAppleFactory();
        appleFactory.registerFactory(new BasicAppleFactory(), 80);
        appleFactory.registerFactory(new GoldenAppleFactory(), 20);
        AppleCreator appleCreator = new AppleCreator(new ApplePositioner(), appleFactory);

        snakeController = new SnakeController(snakeSettings,
                snake,
                snakeMover,
                collisionChecker,
                wrapHandler,
                appleCreator);

        renderer = new GameRenderer(snakeController);
    }

    public void startGame(){
        if (timer != null) {
            timer.stop();
        }
        snakeController.start();
        timer = new Timer(snakeSettings.getDelay(), this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snakeController.update();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        renderer.render(g);
    }
}
