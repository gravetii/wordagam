package io.github.gravetii.scene.game;

import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.menu.MenuBarComponent;
import javafx.stage.Stage;

public class GameEndScene extends FxScene {
  private final MenuBarComponent menuBarComponent;
  private final GameGridComponent gameGridComponent;
  private final GameEndResultComponent gameEndResultComponent;

  protected GameEndScene(Stage stage, GameGridComponent gameGridComponent) throws Exception {
    super(stage);
    this.gameGridComponent = gameGridComponent;
    this.menuBarComponent = new MenuBarComponent(stage, this.root);
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
