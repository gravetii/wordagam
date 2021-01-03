package io.github.gravetii.scene.game;

import io.github.gravetii.controller.ProgressBarController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;

public class ProgressBarComponent extends FxComponent<ProgressBarController, ProgressBar> {
  private final BorderPane root;

  public ProgressBarComponent(BorderPane root) throws Exception {
    super("progressBar.fxml");
    this.root = root;
    this.create();
  }

  @Override
  protected ProgressBarController createController() {
    return new ProgressBarController(this.root);
  }

  @Override
  protected ProgressBar createNode() throws Exception {
    return this.loadNode();
  }
}
