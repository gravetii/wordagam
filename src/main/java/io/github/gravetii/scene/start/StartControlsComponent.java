package io.github.gravetii.scene.start;

import io.github.gravetii.scene.FxComponent;
import io.github.gravetii.scene.menu.MenuBarComponent;

import javafx.scene.layout.VBox;

public class StartControlsComponent extends FxComponent<StartControlsController, VBox> {
  private final MenuBarComponent ref;

  public StartControlsComponent(MenuBarComponent ref) throws Exception {
    super("startControls.fxml");
    this.ref = ref;
    this.create();
  }

  @Override
  protected StartControlsController createController() {
    return new StartControlsController(this.ref.getController());
  }

  @Override
  protected VBox createNode() throws Exception {
    return this.loadNode();
  }
}
