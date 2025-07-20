package pl.gensty.snakeGame;

import pl.gensty.enums.Difficulty;

public class SettingsSnake {
    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 25;

    private final int delay;
    private boolean wrapEnabled;

    public SettingsSnake(int delay, boolean wrapEnabled) {
        this.delay = delay;
        this.wrapEnabled = wrapEnabled;
    }


    public static SettingsSnake from(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> new SettingsSnake(75, true);
            case MEDIUM -> new SettingsSnake(60, true);
            case HARD -> new SettingsSnake(50, false);
        };
    }

    public int getDelay() {
        return delay;
    }

    public boolean wrapEnabled() {
        return wrapEnabled;
    }
}
