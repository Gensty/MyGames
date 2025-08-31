package pl.gensty.games.Snake.apple.displayer;

import pl.gensty.games.Snake.SnakeSettings;
import pl.gensty.games.Snake.apple.Apple;
import pl.gensty.utils.Displayer;

import java.awt.*;

public class BasicAppleDisplayer implements Displayer<Apple> {
    @Override
    public void display(Graphics g, Apple apple) {
        g.setColor(Color.RED);
        g.fillOval(apple.getPoint().x, apple.getPoint().y, SnakeSettings.UNIT_SIZE, SnakeSettings.UNIT_SIZE);
    }
}
