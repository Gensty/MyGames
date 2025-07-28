package pl.gensty.snakeGame.apple.displayer;

import pl.gensty.Displayer;
import pl.gensty.snakeGame.apple.Apple;

import java.awt.*;

import static pl.gensty.snakeGame.Settings.UNIT_SIZE;

public class GoldenAppleDisplayer implements Displayer<Apple> {
    @Override
    public void display(Graphics g, Apple apple) {
        g.setColor(Color.ORANGE);
        g.fillOval(apple.getPoint().x, apple.getPoint().y, UNIT_SIZE, UNIT_SIZE);
    }
}
