package io.github.gravetii.scene.game;

import io.github.gravetii.controller.GameResultController;
import io.github.gravetii.game.Game;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.VBox;

public class GameResultComponent extends FxComponent<GameResultController, VBox> {

  private Game game;
  private GameGridComponent ref;

  public GameResultComponent(Game game, GameGridComponent ref) throws Exception {
    super("gameResult.fxml");
    this.game = game;
    this.ref = ref;
    this.create();
  }

  @Override
  protected GameResultController createController() {
    return new GameResultController(this.game, this.ref.getController());
  }

  @Override
  protected VBox createNode() throws Exception {
    return (VBox) this.loadNode();
  }

  @Override
  protected void onGameEnd() {
    this.getController().onGameEnd();
  }
}
