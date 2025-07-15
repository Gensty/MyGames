package pl.gensty.snakeGame.displayer;

import pl.gensty.snakeGame.snake.Snake;

import java.awt.*;
import java.util.List;

import static pl.gensty.snakeGame.SettingsSnake.*;

public class DisplayerSnake implements Displayer<Snake> {
    @Override
    public void display(Graphics g, Snake snake) {
        List<Point> body = snake.getBody();
        for (int i = 0; i < body.size(); i++) {
            g.setColor(i == 0 ? Color.GREEN : new Color(45, 180, 0));
            Point p = body.get(i);
            g.fillRect(p.x, p.y, UNIT_SIZE, UNIT_SIZE);
        }
    }
}
