package io.github.gravetii.scene;

import io.github.gravetii.theme.ThemeService;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditThemeScene extends FxScene {

  public EditThemeScene(Stage stage) {
    super(stage);
    Stage parent = (Stage) stage.getOwner();
    stage.setOnCloseRequest(
        event -> {
          GridPane gridPane = (GridPane) parent.getScene().lookup("#gridPane");
          if (gridPane != null) {
            gridPane.setBackground(new ThemeService().current().getGameGridBackground());
          }
        });
  }

  @Override
  protected Scene build() throws Exception {
    GridPane gridPane = this.builder.loadEditThemePane();
    this.showCenter(gridPane);
    return this.builder.build();
  }
}
