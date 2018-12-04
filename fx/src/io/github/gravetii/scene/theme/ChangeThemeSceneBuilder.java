package io.github.gravetii.scene.theme;

import io.github.gravetii.App;
import io.github.gravetii.controller.ChangeThemeController;
import io.github.gravetii.controller.ChangeThemeFooterController;
import io.github.gravetii.theme.Theme;
import io.github.gravetii.theme.ThemeService;
import io.github.gravetii.theme.ThemeType;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class ChangeThemeSceneBuilder {

  private ThemeService themes;
  private ChangeThemeFooterController footerController;
  private ChangeThemeController themeController;

  public ChangeThemeSceneBuilder(Stage stage, BorderPane root) {
    this.themes = new ThemeService();
    this.footerController = new ChangeThemeFooterController(stage);
    this.themeController = new ChangeThemeController(stage, footerController);
  }

  public ToolBar loadFooter() throws Exception {
    return (ToolBar) App.loadFxComponent("fxml/changeThemeFooter.fxml", footerController);
  }

  public GridPane loadThemesPane() throws Exception {
    GridPane gridPane = (GridPane) App.loadFxComponent("fxml/changeTheme.fxml", themeController);
    List<ThemeType> allThemes = this.themes.getAll();
    for (int c = 0; c < allThemes.size(); ++c) {
      ThemeType type = allThemes.get(c);
      Theme theme = this.themes.loadTheme(type);
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
