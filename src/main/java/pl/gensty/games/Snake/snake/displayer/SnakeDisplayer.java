package pl.gensty.games.Snake.snake.displayer;

import pl.gensty.games.Snake.SnakeSettings;
import pl.gensty.utils.Displayer;
import pl.gensty.games.Snake.snake.Snake;

import java.awt.*;
import java.util.List;

public class SnakeDisplayer implements Displayer<Snake> {
    @Override
    public void display(Graphics g, Snake snake) {
        List<Point> body = snake.getBody();
        for (int i = 0; i < body.size(); i++) {
            g.setColor(i == 0 ? Color.GREEN : new Color(45, 180, 0));
            Point p = body.get(i);
            g.fillRect(p.x, p.y, SnakeSettings.UNIT_SIZE, SnakeSettings.UNIT_SIZE);
        }
    }
}
