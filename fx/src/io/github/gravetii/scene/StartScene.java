package io.github.gravetii.scene;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartScene extends FxScene {

  public StartScene(Stage stage) {
    super(stage);
  }

  @Override
  protected Scene build() throws Exception {
    MenuBar menuBar = this.builder.loadMenuBar();
    this.builder.root.setTop(menuBar);
    Pane pane = this.builder.loadStartPane();
    this.builder.addNode(pane);
    return this.builder.build();
  }
}
