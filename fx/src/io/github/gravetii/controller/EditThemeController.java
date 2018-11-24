package io.github.gravetii.controller;

import io.github.gravetii.themes.Theme;
import io.github.gravetii.themes.ThemeService;
import io.github.gravetii.themes.ThemeType;
import io.github.gravetii.util.Utils;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditThemeController implements FxController {

  private Stage stage;

  private ThemeService themes;

  public EditThemeController(Stage stage) {
    this.stage = stage;
    this.themes = new ThemeService();
  }

  @FXML
  public void onImgViewClick(MouseEvent event) throws Exception {
    ImageView imgView = (ImageView) event.getSource();
    int idx = Utils.getImageViewIndexFromLabel(imgView.getId());
    if (idx >= themes.getAll().size()) {
      return;
    }

    Theme theme = themes.getAll().get(idx);
    ThemeType type = theme.getType();
    Theme.setCurrent(type);
    System.out.println("Set current theme to " + type);
  }
}
