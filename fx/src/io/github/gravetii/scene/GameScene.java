package io.github.gravetii.scene;

import io.github.gravetii.game.Game;
import io.github.gravetii.game.GameService;
import io.github.gravetii.pojo.GameComponent;
import io.github.gravetii.theme.Theme;
import io.github.gravetii.theme.ThemeService;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameScene extends FxScene {

  private Game game;
  private ThemeService themes;

  public GameScene(Stage stage) {
    super(stage);
    this.game = new GameService().fetch();
    this.themes = new ThemeService();
  }

  @Override
  protected Scene build() throws Exception {
    MenuBar menuBar = this.builder.loadMenuBar();
    GameComponent component = this.builder.loadGameComponent(this.game);
    this.showTop(menuBar).showCenter(component.getGridPane()).showRight(component.getvBox());
    return this.builder.build();
  }

  @Override
  protected void addEventFilters() {
    this.root.addEventFilter(
        Theme.ChangeEvent.THEME_CHANGE_EVENT_TYPE,
        (event) -> {
          GridPane gamePane = (GridPane) root.getCenter();
          gamePane.setBackground(this.themes.loadCurrentTheme().getGameGridBackground());
          event.consume();
        });
  }
}
