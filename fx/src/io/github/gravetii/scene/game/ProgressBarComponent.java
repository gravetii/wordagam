package io.github.gravetii.scene.game;

import io.github.gravetii.controller.GameTimerTask;
import io.github.gravetii.controller.ProgressBarController;
import io.github.gravetii.scene.FxComponent;
import io.github.gravetii.store.Settings;
import io.github.gravetii.util.SingleLatestTaskScheduler;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;

public class ProgressBarComponent extends FxComponent<ProgressBarController, ProgressBar> {

  private GameTimerTask task;

  public ProgressBarComponent(BorderPane root) throws Exception {
    super("progressBar.fxml");
    double gameTime = Settings.getGameTime();
    this.task = new GameTimerTask(root, gameTime);
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
