package io.github.gravetii.scene.theme;

import io.github.gravetii.controller.ChangeThemeFooterController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.control.ToolBar;

public class ChangeThemeFooterComponent extends FxComponent<ChangeThemeFooterController, ToolBar> {

  public ChangeThemeFooterComponent() throws Exception {
    super("changeThemeFooter.fxml");
    this.create();
  }

  @Override
  protected ChangeThemeFooterController createController() {
    return new ChangeThemeFooterController();
  }

  @Override
  protected ToolBar createNode() throws Exception {
    return this.loadNode();
  }
}
