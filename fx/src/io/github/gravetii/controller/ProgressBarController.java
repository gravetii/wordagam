package io.github.gravetii.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class ProgressBarController implements FxController {

  private final GameTimerTask task;

  @FXML private ProgressBar bar;

  public ProgressBarController(GameTimerTask task) {
    this.task = task;
  }

  @FXML
  public void initialize() {
    bar.progressProperty().bind(this.task.progressProperty());
  }
}
