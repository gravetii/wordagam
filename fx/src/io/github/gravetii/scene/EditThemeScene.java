package io.github.gravetii.scene;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditThemeScene extends FxScene {

  public EditThemeScene(Stage stage) {
    super(stage);
  }

  @Override
  protected Scene build() throws Exception {
    GridPane gridPane = this.builder.loadEditThemePane();
    this.showCenter(gridPane);
    return this.builder.build();
  }
}
