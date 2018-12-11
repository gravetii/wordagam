package io.github.gravetii.game;

import io.github.gravetii.dictionary.Dictionary;
import io.github.gravetii.util.Alphabet;
import io.github.gravetii.util.GridPoint;
import io.github.gravetii.util.GridUnit;
import javafx.event.Event;
import javafx.event.EventType;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

  private final GridUnit[][] grid;
  private final GameResult result;
  private final Quality quality;

  public Game(Dictionary dictionary) {
    this.grid = new GridUnit[4][4];
    this.populateGrid();
    GameSolver solver = new GameSolver(grid, dictionary);
    this.result = solver.solve();
    this.quality = this.result.defineQuality();
  }

  public GridUnit[][] getGrid() {
    return grid;
  }

  public GridUnit getGridUnit(GridPoint point) {
    return grid[point.x][point.y];
  }

  private void populateGrid() {
    List<Alphabet> weightedAlphabets = Alphabet.weightedList();
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        int idx = ThreadLocalRandom.current().nextInt(weightedAlphabets.size());
        grid[i][j] = new GridUnit(weightedAlphabets.get(idx), new GridPoint(i, j));
      }
    }
  }

  public Quality getQuality() {
    return this.quality;
  }

  public GameResult result() {
    return this.result;
  }

  public static class EndEvent extends Event {
    public static final EventType<EndEvent> GAME_END_EVENT_EVENT_TYPE =
        new EventType<>(Event.ANY, "game-end");

    public EndEvent() {
      super(GAME_END_EVENT_EVENT_TYPE);
    }
  }

  @Override
  public String toString() {
    return '{' +
            "result=" + result +
            '}';
  }
}
