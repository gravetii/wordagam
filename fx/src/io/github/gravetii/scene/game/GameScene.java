package io.github.gravetii.scene.game;

import io.github.gravetii.game.Game;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.theme.Theme;
import io.github.gravetii.theme.ThemeService;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameScene extends FxScene {

  private GameSceneBuilder builder;
  private ThemeService themes;

  public GameScene(Stage stage) throws Exception {
    super(stage);
    this.themes = new ThemeService();
    this.builder = new GameSceneBuilder(this.stage, this.root);
    this.build();
  }

  @Override
  protected void build() throws Exception {
    GridPane gamePane = this.builder.loadGamePane();
    BorderPane gamePlayPane = this.builder.loadGamePlayPane();
    ProgressBar progressBar = this.builder.loadGameProgressBar();
    MenuBar menuBar = this.builder.loadMenuBar();
    this.showTop(menuBar);
    this.showLeft(gamePane);
    this.showCenter(gamePlayPane);
    this.showBottom(progressBar);
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
    this.root.addEventFilter(
        Theme.ChangeEvent.THEME_CHANGE_EVENT_TYPE,
        (event) -> {
          this.applyCurrentTheme();
          event.consume();
        });
    this.root.addEventFilter(
        Game.EndEvent.GAME_END_EVENT_EVENT_TYPE,
        (event -> {
          this.endGame();
          event.consume();
        }));
  }

  @Override
  public String title() {
    return "New game";
  }
}
