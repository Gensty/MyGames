package pl.gensty.snakeGame.apple.factory;

import pl.gensty.snakeGame.apple.Apple;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAppleFactory implements AppleFactory{
    private final Random random = new Random();
    private final List<AppleFactory> factories = new ArrayList<>();
    private final List<Integer> odds = new ArrayList<>();

    public void registerFactory(AppleFactory factory, int chance) {
        factories.add(factory);
        odds.add(chance);
    }

    @Override
    public Apple createApple(Point point) {
        int total = odds.stream().mapToInt(Integer::intValue).sum();
        int roll = random.nextInt(total);
        int current = 0;

        for (int i = 0; i < factories.size(); i++) {
            current += odds.get(i);
            if (roll < current) {
                return factories.get(i).createApple(point);
            }
        }

        throw new IllegalStateException("No factory selected");
    }
}
