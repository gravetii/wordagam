package io.github.gravetii.scene;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChangeThemeScene extends FxScene {

  public ChangeThemeScene(Stage stage) {
    super(stage);
  }

  @Override
  protected void build() throws Exception {
    GridPane gridPane = this.builder.loadChangeThemePane();
    this.showCenter(gridPane);
  }

  @Override
  public String title() {
    return "Change theme";
  }
}
