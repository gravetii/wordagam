package io.github.gravetii.scene.game;

import io.github.gravetii.controller.GameTimerTask;
import io.github.gravetii.controller.ProgressBarController;
import io.github.gravetii.scene.FxComponent;
import io.github.gravetii.util.SingleLatestTaskScheduler;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;

public class ProgressBarComponent extends FxComponent<ProgressBarController, ProgressBar> {

  private static final int GAME_TIME_SECONDS = 5;

  private GameTimerTask task;

  public ProgressBarComponent(BorderPane root) throws Exception {
    super("progressBar.fxml");
    this.task = new GameTimerTask(root, GAME_TIME_SECONDS);
    SingleLatestTaskScheduler.get().submit(task);
    this.create();
  }

  @Override
  protected ProgressBarController createController() {
    return new ProgressBarController(this.task);
  }

  @Override
  protected ProgressBar createNode() throws Exception {
    return (ProgressBar) this.loadNode();
  }
}
