package io.github.gravetii.game;

import io.github.gravetii.service.WordService;
import io.github.gravetii.util.Alphabet;
import io.github.gravetii.util.GridPoint;
import io.github.gravetii.util.GridUnit;
import io.github.gravetii.util.Constants;

import java.util.*;

import static io.github.gravetii.util.Constants.WORDS_COUNT_HIGH;
import static io.github.gravetii.util.Constants.WORDS_COUNT_LOW;

public class Game {

    private WordService service;

    private GridUnit[][] grid;

    private Map<String, Integer> wordPoints;
    private int totalPoints;
    private Set<String> allWords;
    private Quality quality;

    Game() {
        this.grid = new GridUnit[4][4];
        this.service = new WordService();
        this.wordPoints = new HashMap<>();
        this.wordPoints.put("", 0);
        this.totalPoints = 0;
        this.allWords = new HashSet<>();
        this.create();
        this.crawl();
        this.quality = assignQuality();
    }

    public Set<String> getAllWords() {
        return this.allWords;
    }

    public int getTotalPoints() {
        return this.totalPoints;
    }

    private void create() {
        for (int i=0;i<4;++i) {
            for (int j=0;j<4;++j) {
                GridUnit unit = assignRandomGridUnit();
                grid[i][j] = unit;
            }
        }
    }

    private boolean isValidWord(String word) {
        return word.length() >= Constants.MIN_WORD_LENGTH &&
                service.search(word) && !allWords.contains(word);
    }

    private void crawl(GridPoint point, String prefix, boolean[][] visited) {
        int x = point.x; int y = point.y;
        GridUnit unit = grid[x][y];
        visited[x][y] = true;

        String word = prefix + unit.getLetter();
        if (!service.prefix(word)) {
            return;
        }

        int points = this.wordPoints.get(prefix) + unit.getPoints();
        this.wordPoints.put(word, points);
        if (isValidWord(word)) {
            this.allWords.add(word);
            this.totalPoints += wordPoints.get(word);
        }

        for (GridPoint n: getNeighbors(point)) {
            if (!visited[n.x][n.y]) {
                boolean [][] v = visited.clone();
                this.crawl(n, word, v);
            }
        }
    }

    private void crawl() {
        for (int i=0;i<4;++i) {
            for (int j=0;j<4;++j) {
                boolean visited[][] = new boolean[4][4];
                for (boolean[] row: visited) {
                    Arrays.fill(row, false);
                }

                this.crawl(new GridPoint(i, j), "", visited);
            }
        }
    }

    private Quality assignQuality() {
        int sz = allWords.size();
        Quality q;

        if (sz >= WORDS_COUNT_HIGH) {
            q = Quality.HIGH;
        }
        else if (sz <= WORDS_COUNT_LOW) {
            q = Quality.LOW;
        }
        else {
            q = Quality.MEDIUM;
        }

        return q;
    }

    public Quality getQuality() {
        return quality;
    }

    private List<GridPoint> getNeighbors(GridPoint point) {
        int x = point.x; int y = point.y;
        int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
        int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};
        List<GridPoint> neighbors = new ArrayList<>(8);
        for (int i=0;i<dx.length;++i) {
            GridPoint n = new GridPoint(x + dx[i], y + dy[i]);
            if (n.isValid()) {
                neighbors.add(n);
            }
        }

        return neighbors;
    }

    private GridUnit assignRandomGridUnit() {
        int bound = Alphabet.ALL.size();
        int idx = new Random().nextInt(bound);
        return Alphabet.ALL.get(idx);
    }

    @Override
    public String toString() {
        return "Game{" +
                "service=" + service +
                ", grid=" + Arrays.toString(grid) +
                ", wordPoints=" + wordPoints +
                ", totalPoints=" + totalPoints +
                ", allWords=" + allWords +
                ", quality=" + quality +
                '}';
    }
}
