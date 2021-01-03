package io.github.gravetii.game;

import io.github.gravetii.dictionary.Dictionary;
import io.github.gravetii.model.GridPoint;
import io.github.gravetii.model.GridUnit;
import io.github.gravetii.util.Utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GameSolver {
  private static final int MIN_WORD_LENGTH = 3;

  private final GridUnit[][] grid;
  private final Dictionary dictionary;
  private final GameResult result = new GameResult();

  public GameSolver(GridUnit[][] grid, Dictionary dictionary) {
    this.grid = grid;
    this.dictionary = dictionary;
  }

  private int validate(String word) {
    if (word.length() < MIN_WORD_LENGTH) return 0;
    return this.dictionary.search(word);
  }

  public GameResult solve() {
    for (int i=0;i<4;++i) {
      for (int j=0;j<4;++j) {
        boolean[][] visited = new boolean[4][4];
        for (boolean[] row : visited) Arrays.fill(row, false);
        GridPoint point = grid[i][j].getPoint();
        this.crawl(point, "", new LinkedList<>(), visited);
      }
    }

    result.defineQuality();
    return result;
  }

  private void crawl(GridPoint point, String prefix, List<GridPoint> seq, boolean[][] visited) {
    GridUnit unit = grid[point.x][point.y];
    visited[point.x][point.y] = true;
    String word = prefix + unit.getLetter();
    if (this.dictionary.prefix(word)) {
      seq.add(point);
      int score = this.validate(word);
      if (score > 0) this.result.put(word, score, seq);
      for (GridPoint n: point.getNeighbors()) {
        if (!visited[n.x][n.y]) {
          boolean[][] v = Utils.arrCopy(visited);
          this.crawl(n, word, new LinkedList<>(seq), v);
        }
      }
    }
  }
}
