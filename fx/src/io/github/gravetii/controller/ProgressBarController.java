package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.store.Settings;
import io.github.gravetii.util.AppLogger;
import io.github.gravetii.util.SingleLatestTaskScheduler;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;

public class ProgressBarController implements FxController {

  private final GameTimerTask task;

  @FXML private ProgressBar bar;

  public ProgressBarController(BorderPane root) {
    int gameTime = Settings.getGameTime().getValueInSeconds();
    this.task = new GameTimerTask(root, gameTime);
    SingleLatestTaskScheduler.get().submit(this.task);
  }

  @FXML
  public void initialize() {
    bar.progressProperty().bind(this.task.progressProperty());
  }

  public static class GameTimerTask extends Task<Void> {
    private BorderPane root;
    private int time;

    public GameTimerTask(BorderPane root, int time) {
      this.root = root;
      this.time = time;
    }

    @Override
    protected Void call() {
      double c = this.time;
      try {
        while (c > 0) {
          Thread.sleep(50);
          this.updateProgress(c, this.time);
          c -= 0.05;
        }

        this.updateProgress(Double.MIN_VALUE, this.time);
        this.root.fireEvent(new Game.EndEvent());
      } catch (InterruptedException e) {
        AppLogger.info(this.getClass().getCanonicalName(), "Game stopped in between");
      }

      return null;
    }
  }
}
