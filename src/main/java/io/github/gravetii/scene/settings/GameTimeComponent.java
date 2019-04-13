package io.github.gravetii.scene.settings;

import io.github.gravetii.controller.GameTimeController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameTimeComponent extends FxComponent<GameTimeController, AnchorPane> {
  private Stage stage;

  public GameTimeComponent(Stage stage) throws Exception {
    super("gameTime.fxml");
    this.stage = stage;
    this.create();
  }

  @Override
  protected GameTimeController createController() {
    return new GameTimeController(this.stage);
  }

  @Override
  protected AnchorPane createNode() throws Exception {
    return this.loadNode();
  }
}
