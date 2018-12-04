package io.github.gravetii.game;

import io.github.gravetii.dictionary.Dictionary;
import io.github.gravetii.pojo.GamePlayResult;
import io.github.gravetii.util.*;
import javafx.event.Event;
import javafx.event.EventType;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static io.github.gravetii.util.Constants.MIN_WORDS_COUNT;

public class Game {

  private io.github.gravetii.dictionary.Dictionary dictionary;

  private GridUnit[][] grid;

  private Map<String, Integer> wordPoints;
  private int totalPoints;
  private Quality quality;
  private Map<String, GamePlayResult> wordToResultMap;

  public Game(Dictionary dictionary) {
    this.dictionary = dictionary;
    this.grid = new GridUnit[4][4];
    this.wordPoints = new HashMap<>();
    this.wordPoints.put("", 0);
    this.totalPoints = 0;
    this.wordToResultMap = new LinkedHashMap<>();
    this.create();
    this.crawl();
    this.quality = assignQuality();
  }

  public boolean exists(String word) {
    return this.wordToResultMap.containsKey(word);
  }

  public int getTotalPoints() {
    return this.totalPoints;
  }

  public int getWordPoints(String word) {
    return this.wordPoints.get(word);
  }

  public GridUnit[][] getGrid() {
    return grid;
  }

  public GridUnit getGridUnit(GridPoint point) {
    return grid[point.x][point.y];
  }

  public Map<String, GamePlayResult> getResult() {
    return this.wordToResultMap;
  }

  private void create() {
    List<Alphabet> weightedAlphabets = Alphabet.getWeightedAlphabetsAsList();
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        int idx = ThreadLocalRandom.current().nextInt(weightedAlphabets.size());
        grid[i][j] = new GridUnit(weightedAlphabets.get(idx), new GridPoint(i, j));
      }
    }
  }

  private boolean isValidWord(String word) {
    return word.length() >= Constants.MIN_WORD_LENGTH
        && this.dictionary.search(word)
        && !this.wordToResultMap.containsKey(word);
  }

  private void crawl(GridPoint point, String prefix, List<GridUnit> seq, boolean[][] visited) {
    GridUnit unit = grid[point.x][point.y];
    visited[point.x][point.y] = true;

    String word = prefix + unit.getLetter();
    seq.add(unit);

    if (!this.dictionary.prefix(word)) {
      return;
    }

    int points = this.wordPoints.get(prefix) + unit.getPoints();
    this.wordPoints.put(word, points);

    if (this.isValidWord(word)) {
      GamePlayResult result = new GamePlayResult(word, points, seq);
      this.wordToResultMap.put(word, result);
      this.totalPoints += this.wordPoints.get(word);
    }

    for (GridPoint n : point.getNeighbors()) {
      if (!visited[n.x][n.y]) {
        boolean[][] v = Utils.arrCopy(visited);
        this.crawl(n, word, new LinkedList<>(seq), v);
      }
    }
  }

  private void crawl() {
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        boolean[][] visited = new boolean[4][4];
        for (boolean[] row : visited) {
          Arrays.fill(row, false);
        }

        this.crawl(new GridPoint(i, j), "", new LinkedList<>(), visited);
      }
    }
  }

  private Quality assignQuality() {
    int sz = this.wordToResultMap.size();
    return sz >= MIN_WORDS_COUNT ? Quality.HIGH : Quality.LOW;
  }

  public Quality getQuality() {
    return quality;
  }

  @Override
  public String toString() {
    return "Game{"
        + "totalPoints="
        + totalPoints
        + ", count="
        + wordToResultMap.size()
        + ", allWords="
        + wordToResultMap.keySet()
        + ", quality="
        + quality
        + '}';
  }

  public static class EndEvent extends Event {
    public static final EventType<EndEvent> GAME_END_EVENT_EVENT_TYPE =
        new EventType<>(Event.ANY, "game-end");

    public EndEvent() {
      super(GAME_END_EVENT_EVENT_TYPE);
    }
  }
}
