package io.github.gravetii.scene.game;

import io.github.gravetii.game.Game;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.theme.Theme;
import io.github.gravetii.theme.ThemeService;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameScene extends FxScene {

  private GameSceneBuilder builder;
  private ThemeService themes;

  public GameScene(Stage stage) {
    super(stage);
    this.themes = new ThemeService();
    this.builder = new GameSceneBuilder(this.stage, this.root);
  }

  @Override
  protected void build() throws Exception {
    GridPane gameGridPane = this.builder.loadGridPane();
    VBox gameResultPane = this.builder.loadGameResultPane();
    ProgressBar progressBar = this.builder.loadGameProgressBar();
    MenuBar menuBar = this.builder.loadMenuBar();
    this.showTop(menuBar)
        .showCenter(gameGridPane)
        .showRight(gameResultPane)
        .showBottom(progressBar);
    this.applyCurrentTheme();
  }

  private void applyCurrentTheme() {
    String styleSheet = this.themes.loadCurrentTheme().getStyleSheet();
    this.root.getStylesheets().clear();
    this.root.getStylesheets().add(styleSheet);
  }

  private void endGame() {
    this.builder.endGame();
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
          this.endGame();
          event.consume();
        });
  }

  @Override
  public String title() {
    return "New game";
  }
}
