package io.github.gravetii.scene.game;

import io.github.gravetii.db.PreferenceStore;
import io.github.gravetii.game.Game;
import io.github.gravetii.game.GameService;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.menu.MenuBarComponent;
import io.github.gravetii.theme.Theme;
import javafx.application.Platform;
import javafx.stage.Stage;

public class GameScene extends FxScene {

  private final GameGridComponent gridComponent;
  private final GameResultComponent resultComponent;
  private final ProgressBarComponent progressBarComponent;
  private final MenuBarComponent menuBarComponent;

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
    this.showTop(this.menuBarComponent)
        .showCenter(this.gridComponent)
        .showRight(this.resultComponent)
        .showBottom(this.progressBarComponent);
  }

  private void endGame() {
    this.gridComponent.endGame();
    this.showGameEndScene();
    Game.setRunning(false);
  }

  @Override
  protected Theme loadTheme() {
    return this.themes.loadCurrentTheme(true);
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
            FxScene scene = new GameEndScene(this.stage, this.gridComponent);
            scene.show();
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        });
  }

  @Override
  public String title() {
    return "Game #" + PreferenceStore.getGameId();
  }

}
