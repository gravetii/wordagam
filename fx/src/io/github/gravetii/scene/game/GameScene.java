package io.github.gravetii.scene.game;

import io.github.gravetii.game.Game;
import io.github.gravetii.game.GameService;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.menu.MenuBarComponent;
import javafx.stage.Stage;

public class GameScene extends FxScene {

  private GameGridComponent gridComponent;
  private GameResultComponent resultComponent;
  private ProgressBarComponent progressBarComponent;
  private MenuBarComponent menuBarComponent;

  public GameScene(Stage stage) throws Exception {
    super(stage);
    Game game = new GameService().fetch();
    this.gridComponent = new GameGridComponent(game);
    this.resultComponent = new GameResultComponent(game, this.gridComponent);
    this.progressBarComponent = new ProgressBarComponent(root);
    this.menuBarComponent = new MenuBarComponent(stage, root);
  }

  @Override
  protected void build() {
    this.showTop(this.menuBarComponent.getNode())
        .showCenter(this.gridComponent.getNode())
        .showRight(this.resultComponent.getNode())
        .showBottom(this.progressBarComponent.getNode());
  }

  @Override
  public String title() {
    return "New game";
  }
}
