package io.github.gravetii.scene.theme;

import io.github.gravetii.controller.ChangeThemeFooterController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

public class ChangeThemeFooterComponent extends FxComponent<ChangeThemeFooterController, ToolBar> {

  private Stage stage;

  public ChangeThemeFooterComponent(Stage stage) throws Exception {
    super("changeThemeFooter.fxml");
    this.stage = stage;
    this.create();
  }

  @Override
  protected ChangeThemeFooterController createController() {
    return new ChangeThemeFooterController(this.stage);
  }

  @Override
  protected ToolBar createNode() throws Exception {
    return (ToolBar) this.loadNode();
  }
}
