package io.github.gravetii.scene;

import io.github.gravetii.game.Game;
import io.github.gravetii.game.GameService;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
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
    GameComponent component = this.builder.loadGameComponent(this.game);
    this.showTop(menuBar).showCenter(component.getGridPane()).showRight(component.getSplitPane());
    return this.builder.build();
  }
}
