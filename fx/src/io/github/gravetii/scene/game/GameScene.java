package io.github.gravetii.scene.game;

import io.github.gravetii.game.Game;
import io.github.gravetii.game.GameService;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.menu.MenuBarComponent;
import io.github.gravetii.store.StoreUtility;
import io.github.gravetii.theme.Theme;
import javafx.application.Platform;
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
    this.resultComponent = new GameResultComponent(this.gridComponent);
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

  private void endGame() {
    this.gridComponent.endGame();
    this.showGameEndScene();
    StoreUtility.setGameRunning(false);
  }

  @Override
  protected Theme loadTheme() {
    return this.themes.loadNewCurrentTheme();
  }

  @Override
  public void setEventHandlers() {
    super.setEventHandlers();
    this.root.addEventHandler(
        Game.EndEvent.GAME_END_EVENT_EVENT_TYPE,
        (event -> {
          this.endGame();
          event.consume();
        }));
  }

  private void showGameEndScene() {
    Platform.runLater(
        () -> {
          try {
            FxScene scene = new GameEndScene(this.stage, gridComponent);
            scene.show();
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        });
  }

  @Override
  public String title() {
    return "Game #" + StoreUtility.getGameId();
  }
}
