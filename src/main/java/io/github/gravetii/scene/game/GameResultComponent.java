package io.github.gravetii.scene.game;

import io.github.gravetii.controller.GameResultController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.VBox;

public class GameResultComponent extends FxComponent<GameResultController, VBox> {
  private GameGridComponent ref;

  public GameResultComponent(GameGridComponent ref) throws Exception {
    super("gameResult.fxml");
    this.ref = ref;
    this.create();
  }

  @Override
  protected GameResultController createController() {
    return new GameResultController(this.ref.getController());
  }

  @Override
  protected VBox createNode() throws Exception {
    return this.loadNode();
  }
}
