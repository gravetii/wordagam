package io.github.gravetii.scene.game;

import io.github.gravetii.controller.GameGridController;
import io.github.gravetii.game.Game;
import io.github.gravetii.model.GameStats;
import io.github.gravetii.model.GridUnit;
import io.github.gravetii.scene.FxComponent;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameGridComponent extends FxComponent<GameGridController, GridPane> {
  private final Game game;

  public GameGridComponent(Game game) throws Exception {
    super("gameGrid.fxml");
    this.game = game;
    this.create();
  }

  @Override
  protected GameGridController createController() {
    return new GameGridController(this.game);
  }

  @Override
  protected GridPane createNode() throws Exception {
    GridPane gridPane = this.loadNode();

    int c = 0;
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        GridUnit unit = game.getGrid()[i][j];
        Pane pane = (Pane) gridPane.getChildren().get(c++);
        ImageView imgView = (ImageView) pane.getChildren().get(0);
        imgView.setImage(unit.getImage());
        imgView.fitWidthProperty().bind(pane.widthProperty());
        imgView.fitHeightProperty().bind(pane.heightProperty());
      }
    }

    return gridPane;
  }

  public void endGame() {
    this.getController().endGame();
  }

  public GameStats computeStats() {
    return this.getController().computeStats();
  }
}
