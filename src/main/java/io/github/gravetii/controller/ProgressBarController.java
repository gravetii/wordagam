package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.db.PreferenceStore;
import io.github.gravetii.util.AppLogger;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProgressBarController implements FxController {
  private static final ExecutorService EXECUTOR =
      Executors.newSingleThreadExecutor(
          (r) -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
          });
  private static Future currentTask;
  private final GameTimerTask task;
  @FXML private ProgressBar bar;

  public ProgressBarController(BorderPane root) {
    int gameTime = PreferenceStore.getGameTime().getValueInSeconds();
    this.task = new GameTimerTask(root, gameTime);
    this.submit();
  }

  private void submit() {
    if (currentTask != null) {
      currentTask.cancel(true);
    }

    currentTask = EXECUTOR.submit(this.task);
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
        AppLogger.fine(this.getClass().getCanonicalName(), "Game stopped in between...");
      }

      return null;
    }
  }
}
