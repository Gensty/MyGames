package pl.gensty.snakeGame.displayer;

import pl.gensty.snakeGame.apple.Apple;

import java.awt.*;

import static pl.gensty.snakeGame.SettingsSnake.*;

public class DisplayerApple implements Displayer<Apple> {
    @Override
    public void display(Graphics g, Apple apple) {
        g.setColor(Color.RED);
        g.fillOval(apple.getPoint().x, apple.getPoint().y, UNIT_SIZE, UNIT_SIZE);
    }
}
