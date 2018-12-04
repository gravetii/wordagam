package io.github.gravetii.scene;

import io.github.gravetii.game.Game;
import io.github.gravetii.game.GameService;
import io.github.gravetii.pojo.GameComponent;
import io.github.gravetii.theme.Theme;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class GameScene extends FxScene {

  private Game game;

  public GameScene(Stage stage) {
    super(stage);
    this.game = new GameService().fetch();
  }

  @Override
  protected void build() throws Exception {
    MenuBar menuBar = this.builder.loadMenuBar();
    GameComponent component = this.builder.loadGameComponent(this.game);
    this.showTop(menuBar)
        .showLeft(component.getGamePane())
        .showCenter(component.getPlayVBox())
        //.showRight(component.getResultVBox())
        .showBottom(component.getProgressBar());
    this.applyCurrentTheme();
  }

  @Override
  public void setEventHandlers() {
    this.root.addEventFilter(
        Theme.ChangeEvent.THEME_CHANGE_EVENT_TYPE,
        (event) -> {
          this.applyCurrentTheme();
          event.consume();
        });
  }

  @Override
  public String title() {
    return "New game";
  }
}
