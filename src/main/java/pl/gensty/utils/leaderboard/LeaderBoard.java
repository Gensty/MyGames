package pl.gensty.utils.leaderboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class LeaderBoard {
    private final List<Score> scores = new ArrayList<>();
    private static final int MAX_ENTRIES = 10;

    public void addScore(Score score) {
        scores.add(score);
        scores.sort(Comparator.comparingInt(Score::score).reversed());

        if(scores.size() > MAX_ENTRIES) {
            scores.remove(scores.size() - 1);
        }
    }

    public List<Score> getScores() {
        return Collections.unmodifiableList(scores);
    }

    public abstract void save();

    public abstract void load();
}
