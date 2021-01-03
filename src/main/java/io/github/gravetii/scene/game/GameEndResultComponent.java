package io.github.gravetii.scene.game;

import io.github.gravetii.controller.GameEndResultController;
import io.github.gravetii.model.GameStats;
import io.github.gravetii.scene.FxComponent;

import javafx.scene.layout.VBox;

public class GameEndResultComponent extends FxComponent<GameEndResultController, VBox> {
  private final GameGridComponent ref;

  public GameEndResultComponent(GameGridComponent ref) throws Exception {
    super("gameEndResult.fxml");
    this.ref = ref;
    this.create();
    this.updateStats();
  }

  @Override
  protected GameEndResultController createController() {
    return new GameEndResultController(this.ref.getController());
  }

  @Override
  protected VBox createNode() throws Exception {
    return this.loadNode();
  }

  private void updateStats() {
    GameStats stats = this.ref.computeStats();
    this.getController().updateText(stats.toString());
  }
}
