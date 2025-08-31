package pl.gensty.games.Snake;

import pl.gensty.utils.Screen;
import pl.gensty.utils.enums.Difficulty;

public class SnakeSettings {
    public static final int SCREEN_WIDTH = Screen.WIDTH;
    public static final int SCREEN_HEIGHT = Screen.HEIGHT;
    public static final int UNIT_SIZE = 25;

    private final int delay;
    private boolean wrapEnabled;

    public SnakeSettings(int delay, boolean wrapEnabled) {
        this.delay = delay;
        this.wrapEnabled = wrapEnabled;
    }


    public static SnakeSettings from(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> new SnakeSettings(75, true);
            case MEDIUM -> new SnakeSettings(60, true);
            case HARD -> new SnakeSettings(50, false);
        };
    }

    public int getDelay() {
        return delay;
    }

    public boolean wrapEnabled() {
        return wrapEnabled;
    }
}
