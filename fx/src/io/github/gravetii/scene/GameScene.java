package io.github.gravetii.scene;

import io.github.gravetii.game.Game;
import io.github.gravetii.game.GameService;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameScene extends FxScene {

  private Game game;

  public GameScene(Stage stage) {
    super(stage);
    this.game = new GameService().fetch();
  }

  @Override
  protected Scene build() throws Exception {
    MenuBar menuBar = this.builder.loadMenuBar();
    GridPane gridPane = this.builder.loadGamePane(this.game);
    SplitPane splitPane = this.builder.loadSplitPane();
    this.showTop(menuBar).showCenter(gridPane).showRight(splitPane);
    return this.builder.build();
  }
}
