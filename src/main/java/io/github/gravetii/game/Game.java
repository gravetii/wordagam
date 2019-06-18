package io.github.gravetii.game;

import io.github.gravetii.dictionary.Dictionary;
import io.github.gravetii.util.Alphabet;
import io.github.gravetii.util.GridPoint;
import io.github.gravetii.util.GridUnit;
import javafx.event.Event;
import javafx.event.EventType;

public class Game {
  // Is an instance of the game running right now?
  private static boolean IS_INSTANCE_RUNNING = false;

  private final GridUnit[][] grid;
  private final GameResult result;

  public Game(Dictionary dictionary) {
    this.grid = new GridUnit[4][4];
    this.populateGrid();
    GameSolver solver = new GameSolver(grid, dictionary);
    this.result = solver.solve();
  }

  public GridUnit[][] getGrid() {
    return grid;
  }

  public GridUnit getGridUnit(GridPoint point) {
    return grid[point.x][point.y];
  }

  private void populateGrid() {
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        grid[i][j] = new GridUnit(Alphabet.newRandom(), new GridPoint(i, j));
      }
    }
  }

  public Quality getQuality() {
    return this.result.getQuality();
  }

  public GameResult result() {
    return this.result;
  }

  @Override
  public String toString() {
    return '{' + "result=" + result + '}';
  }

  public static class EndEvent extends Event {
    public static final EventType<EndEvent> GAME_END_EVENT_EVENT_TYPE =
        new EventType<>(Event.ANY, "game-end");

    public EndEvent() {
      super(GAME_END_EVENT_EVENT_TYPE);
    }
  }

  public static void setRunning(boolean value) {
    IS_INSTANCE_RUNNING = value;
  }

  public static boolean isRunning() {
    return IS_INSTANCE_RUNNING;
  }
}
