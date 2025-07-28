package pl.gensty.snakeGame;

import pl.gensty.DifficultySelector;
import pl.gensty.enums.Difficulty;
import pl.gensty.snakeGame.apple.AppleCreator;
import pl.gensty.snakeGame.apple.ApplePositioner;
import pl.gensty.snakeGame.apple.factory.BasicAppleFactory;
import pl.gensty.snakeGame.apple.factory.GoldenAppleFactory;
import pl.gensty.snakeGame.apple.factory.RandomAppleFactory;
import pl.gensty.snakeGame.keyHandler.SnakeKeyHandler;
import pl.gensty.snakeGame.snake.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pl.gensty.snakeGame.Settings.*;

public class GamePanel extends JPanel implements ActionListener {
    private GameRenderer renderer;
    private SnakeController snakeController;
    private Settings settings;
    Timer timer;

    GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        initController();
        this.addKeyListener(new SnakeKeyHandler(snakeController.getSnake(), true, this::startGame));

        startGame();
    }

    private void initController() {
        Difficulty difficulty = DifficultySelector.chooseDifficulty();
        settings = Settings.from(difficulty);
        Snake snake = new Snake();
        SnakeMover snakeMover = new SnakeMover();
        SnakeCollisionChecker collisionChecker = new SnakeCollisionChecker();
        SnakeWrapHandler wrapHandler = new SnakeWrapHandler();

        RandomAppleFactory appleFactory = new RandomAppleFactory();
        appleFactory.registerFactory(new BasicAppleFactory(), 80);
        appleFactory.registerFactory(new GoldenAppleFactory(), 20);
        AppleCreator appleCreator = new AppleCreator(new ApplePositioner(), appleFactory);

        snakeController = new SnakeController(settings,
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
        timer = new Timer(settings.getDelay(), this);
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
