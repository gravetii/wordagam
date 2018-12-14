package io.github.gravetii.scene.game;

import io.github.gravetii.controller.GameGridController;
import io.github.gravetii.game.Game;
import io.github.gravetii.scene.FxComponent;
import io.github.gravetii.util.GridUnit;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameGridComponent extends FxComponent<GameGridController, GridPane> {

  private Game game;

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
    GridPane gridPane = (GridPane) this.loadNode();

    for (int i = 0, c = 0; i < 4; ++i) {
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
}
