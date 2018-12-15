package io.github.gravetii.scene.settings;

import io.github.gravetii.controller.SetGameTimeController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SetGameTimeComponent extends FxComponent<SetGameTimeController, AnchorPane> {

  private Stage stage;

  protected SetGameTimeComponent(Stage stage) throws Exception {
    super("setGameTime.fxml");
    this.stage = stage;
    this.create();
  }

  @Override
  protected SetGameTimeController createController() {
    return new SetGameTimeController(this.stage);
  }

  @Override
  protected AnchorPane createNode() throws Exception {
    return (AnchorPane) this.loadNode();
  }
}
