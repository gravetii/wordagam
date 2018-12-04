package io.github.gravetii.controller;

import io.github.gravetii.util.SingleLatestTaskScheduler;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class ProgressBarController implements FxController {

  private final int gameTime;

  @FXML private ProgressBar bar;

  public ProgressBarController(int gameTime) {
    this.gameTime = gameTime;
  }

  @FXML
  public void initialize() {
    GameTimerTask task = new GameTimerTask(this.gameTime);
    bar.progressProperty().bind(task.progressProperty());
    SingleLatestTaskScheduler.get().submit(task);
  }

  private static class GameTimerTask extends Task<Integer> {

    private int gameTime;

    public GameTimerTask(int gameTime) {
      this.gameTime = gameTime;
    }

    @Override
    protected Integer call() throws Exception {
      try {
        double c = this.gameTime;
        while (c >= 0) {
          Thread.sleep(50);
          this.updateProgress(c, this.gameTime);
          c -= 0.05;
        }

        return gameTime;
      } catch (InterruptedException e) {
        return null;
      }
    }
  }
}
