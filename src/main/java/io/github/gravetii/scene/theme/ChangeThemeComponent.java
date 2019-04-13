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

import java.util.List;

public class ChangeThemeComponent extends FxComponent<ChangeThemeController, GridPane> {
  private Stage stage;
  private ChangeThemeFooterComponent ref;

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
    ThemeService themes = new ThemeService();
    GridPane gridPane = this.loadNode();

    List<ThemeType> allThemes = themes.getAll();
    for (int c = 0; c < allThemes.size(); ++c) {
      ThemeType type = allThemes.get(c);
      Theme theme = themes.loadTheme(type);
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
