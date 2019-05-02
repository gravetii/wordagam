package io.github.gravetii.scene.game;

import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.menu.MenuBarComponent;
import javafx.stage.Stage;

public class GameEndScene extends FxScene {
  private MenuBarComponent menuBarComponent;
  private GameGridComponent gameGridComponent;
  private GameEndResultComponent gameEndResultComponent;

  protected GameEndScene(Stage stage, GameGridComponent gameGridComponent) throws Exception {
    super(stage);
    this.menuBarComponent = new MenuBarComponent(stage, this.root);
    this.gameGridComponent = gameGridComponent;
    this.gameEndResultComponent = new GameEndResultComponent(this.gameGridComponent);
  }

  @Override
  protected void build() {
    this.showTop(this.menuBarComponent)
        .showCenter(this.gameGridComponent)
        .showRight(this.gameEndResultComponent);
  }

  @Override
  protected String title() {
    return "Game Over";
  }
}
