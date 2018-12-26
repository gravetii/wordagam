package io.github.gravetii.controller;

import io.github.gravetii.theme.ThemeService;
import io.github.gravetii.theme.ThemeType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ChangeThemeFooterController implements FxController {

  private ThemeService themes;
  @FXML private Label footerLabel;

  public ChangeThemeFooterController() {
    this.themes = new ThemeService();
  }

  @FXML
  public void initialize() {
    this.footerLabel.setText(
        "Your current theme is set to " + this.themes.getCurrent().getShowableName() + ".");
  }

  public void updateThemeChange(ThemeType type) {
    String text;
    if (ThemeType.RANDOM == type) {
      text = "You have changed the theme to be random. You will see a new theme each time.";
    } else {
      text = "Theme changed to " + type.getShowableName() + ".";
    }

    this.footerLabel.setText(text);
  }
}
