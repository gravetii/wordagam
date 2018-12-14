package io.github.gravetii.scene.game;

import io.github.gravetii.game.Game;
import io.github.gravetii.game.GameService;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.menu.MenuBarComponent;
import io.github.gravetii.theme.Theme;
import io.github.gravetii.theme.ThemeService;
import javafx.stage.Stage;

public class GameScene extends FxScene {

  private GameGridComponent gridComponent;
  private GameResultComponent resultComponent;
  private ProgressBarComponent progressBarComponent;
  private MenuBarComponent menuBarComponent;

  private ThemeService themes = new ThemeService();

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
    this.applyCurrentTheme();
  }

  private void applyCurrentTheme() {
    String styleSheet = this.themes.loadCurrentTheme().getStyleSheet();
    this.root.getStylesheets().clear();
    this.root.getStylesheets().add(styleSheet);
  }

  @Override
  public void setEventHandlers() {
    this.root.addEventHandler(
        Theme.ChangeEvent.THEME_CHANGE_EVENT_TYPE,
        (event) -> {
          this.applyCurrentTheme();
          event.consume();
        });
    this.root.addEventHandler(
        Game.EndEvent.GAME_END_EVENT_EVENT_TYPE,
        (event) -> {
          // this.endGame();
          event.consume();
        });
  }

  @Override
  public String title() {
    return "New game";
  }
}
