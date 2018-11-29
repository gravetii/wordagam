package io.github.gravetii.pojo;

import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;

public class ChangeThemeComponent {

  private GridPane themesPane;

  private ToolBar footer;

  public ChangeThemeComponent(GridPane themesPane, ToolBar footer) {
    this.themesPane = themesPane;
    this.footer = footer;
  }

  public GridPane getThemesPane() {
    return themesPane;
  }

  public ToolBar getFooter() {
    return footer;
  }
}
