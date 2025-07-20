package pl.gensty;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoard {
    private final List<Score> scores = new ArrayList<>();

    public void addScore(Score score) {
        scores.add(score);
    }
}
