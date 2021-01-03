package io.github.gravetii.scene.theme;

import io.github.gravetii.controller.ChangeThemeController;
import io.github.gravetii.scene.FxComponent;
import io.github.gravetii.theme.Theme;
import io.github.gravetii.theme.ThemeService;
import io.github.gravetii.theme.ThemeType;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChangeThemeComponent extends FxComponent<ChangeThemeController, GridPane> {
  private final Stage stage;
  private final ChangeThemeFooterComponent ref;
  private final ThemeService service = new ThemeService();

  public ChangeThemeComponent(Stage stage, ChangeThemeFooterComponent ref) throws Exception {
    super("changeTheme.fxml");
    this.stage = stage;
    this.ref = ref;
    this.create();
  }

  @Override
  protected ChangeThemeController createController() {
    return new ChangeThemeController(this.stage, this.ref.getController());
  }

  @Override
  protected GridPane createNode() throws Exception {
    GridPane gridPane = this.loadNode();

    for (int c = 0; c < service.getAll().size(); ++c) {
      ThemeType type = service.getAll().get(c);
      Theme theme = service.loadTheme(type);
      BorderPane borderPane = (BorderPane) gridPane.getChildren().get(c);
      Pane pane = (Pane) borderPane.getCenter();
      ImageView imgView = (ImageView) pane.getChildren().get(0);
      imgView.fitWidthProperty().bind(pane.widthProperty());
      imgView.fitHeightProperty().bind(pane.heightProperty());
      imgView.setImage(theme.getImage());
      Label label = (Label) borderPane.getBottom();
      label.setText(type.getShowableName());
      borderPane.setBottom(label);
    }

    return gridPane;
  }
}
