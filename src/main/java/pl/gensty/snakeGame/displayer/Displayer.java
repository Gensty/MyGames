package pl.gensty.snakeGame.displayer;

import java.awt.*;

public interface Displayer<T> {
    void display(Graphics g, T object);
}
