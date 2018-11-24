package io.github.gravetii.scene;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ThemeListScene extends FxScene {

  public ThemeListScene(Stage stage) {
    super(stage);
  }

  @Override
  protected Scene build() throws Exception {
    GridPane gridPane = this.builder.loadEditThemePane();
    this.showLeft(gridPane);
    return this.builder.build();
  }
}
