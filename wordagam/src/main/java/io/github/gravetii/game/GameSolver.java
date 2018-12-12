package io.github.gravetii.game;

import io.github.gravetii.dictionary.Dictionary;
import io.github.gravetii.pojo.WordResult;
import io.github.gravetii.util.GridPoint;
import io.github.gravetii.util.GridUnit;
import io.github.gravetii.util.Utils;

import java.util.*;

public class GameSolver {

  private static final int MIN_WORD_LENGTH = 3;

  private GridUnit[][] grid;
  private Dictionary dictionary;

  private Map<String, Integer> wordPoints = new HashMap<>();
  private Map<String, WordResult> result = new HashMap<>();
  private int totalScore = 0;

  public GameSolver(GridUnit[][] grid, Dictionary dictionary) {
    this.grid = grid;
    this.dictionary = dictionary;
    this.wordPoints.put("", 0);
  }

  private boolean isValidWord(String word) {
    return word.length() >= MIN_WORD_LENGTH
        && this.dictionary.contains(word)
        && !this.result.containsKey(word);
  }

  public GameResult solve() {
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        boolean[][] visited = new boolean[4][4];
        for (boolean[] row : visited) {
          Arrays.fill(row, false);
        }

        GridPoint point = grid[i][j].getPoint();
        this.solve(point, "", new LinkedList<>(), visited);
      }
    }

    return new GameResult(this.result, this.totalScore);
  }

  private void solve(GridPoint point, String prefix, List<GridPoint> seq, boolean[][] visited) {
    GridUnit unit = grid[point.x][point.y];
    visited[point.x][point.y] = true;

    String word = prefix + unit.getLetter();
    seq.add(point);

    if (!this.dictionary.prefix(word)) {
      return;
    }

    int score = this.wordPoints.get(prefix) + unit.getScore();
    this.wordPoints.put(word, score);

    if (this.isValidWord(word)) {
      this.result.put(word, new WordResult(word, score, seq));
      this.totalScore += this.wordPoints.get(word);
    }

    for (GridPoint n : point.getNeighbors()) {
      if (!visited[n.x][n.y]) {
        boolean[][] v = Utils.arrCopy(visited);
        this.solve(n, word, new LinkedList<>(seq), v);
      }
    }
  }
}
