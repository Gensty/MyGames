package pl.gensty.games.Snake.apple.displayer;

import pl.gensty.utils.Displayer;
import pl.gensty.games.Snake.apple.Apple;
import pl.gensty.games.Snake.apple.BasicApple;
import pl.gensty.games.Snake.apple.GoldenApple;

import java.awt.*;
import java.util.Map;

public class AppleDisplayer implements Displayer<Apple> {
    private final Map<Class<? extends Apple>, Displayer<? extends Apple>> dispatchMap = Map.of(
            BasicApple.class, new BasicAppleDisplayer(),
            GoldenApple.class, new GoldenAppleDisplayer()
    );

    @Override
    public void display(Graphics g, Apple apple) {
        Displayer displayer = dispatchMap.get(apple.getClass());

        if (displayer != null) {
            displayer.display(g, apple);
        }
    }
}
