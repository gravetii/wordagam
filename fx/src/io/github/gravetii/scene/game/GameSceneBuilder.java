package io.github.gravetii.scene.game;

import io.github.gravetii.App;
import io.github.gravetii.controller.*;
import io.github.gravetii.game.Game;
import io.github.gravetii.game.GameService;
import io.github.gravetii.util.GridUnit;
import io.github.gravetii.util.SingleLatestTaskScheduler;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameSceneBuilder {

  private Game game;
  private BorderPane root;
  private GameController gameController;
  private GamePlayController gamePlayController;
  private ProgressBarController progressBarController;
  private MenuBarController menuBarController;

  public GameSceneBuilder(Stage stage, BorderPane root) {
    this.root = root;
    this.game = new GameService().fetch();
    this.gameController = new GameController(this.game);
    this.gamePlayController = new GamePlayController(this.game, this.gameController);
    GameTimerTask timerTask = new GameTimerTask(this.root, 10);
    SingleLatestTaskScheduler.get().submit(timerTask);
    this.progressBarController = new ProgressBarController(timerTask);
    this.menuBarController = new MenuBarController(stage);
  }

  public GridPane loadGamePane() throws Exception {
    GridPane gamePane = (GridPane) App.loadFxComponent("fxml/game.fxml", this.gameController);

    for (int i = 0, c = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        GridUnit unit = this.game.getGrid()[i][j];
        Pane pane = (Pane) gamePane.getChildren().get(c++);
        ImageView imgView = (ImageView) pane.getChildren().get(0);
        imgView.setImage(unit.getImage());
        imgView.fitWidthProperty().bind(pane.widthProperty());
        imgView.fitHeightProperty().bind(pane.heightProperty());
      }
    }

    return gamePane;
  }

  public VBox loadGameResultPane() throws Exception {
    return (VBox) App.loadFxComponent("fxml/gameResult.fxml", this.gamePlayController);
  }

  public ProgressBar loadGameProgressBar() throws Exception {
    return (ProgressBar) App.loadFxComponent("fxml/progressBar.fxml", this.progressBarController);
  }

  public MenuBar loadMenuBar() throws Exception {
    MenuBar menuBar = (MenuBar) App.loadFxComponent("fxml/menuBar.fxml", this.menuBarController);
    menuBar.prefWidthProperty().bind(this.root.widthProperty());
    return menuBar;
  }

  public void endGame() {
    this.gameController.onGameEnd();
    this.gamePlayController.onGameEnd();
  }
}
