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
  private GameGridController gameGridController;
  private GameResultController gameResultController;
  private ProgressBarController progressBarController;
  private MenuBarController menuBarController;

  public GameSceneBuilder(Stage stage, BorderPane root) {
    this.root = root;
    this.game = new GameService().fetch();
    this.gameGridController = new GameGridController(this.game);
    this.gameResultController = new GameResultController(this.game, this.gameGridController);
    GameTimerTask timerTask = new GameTimerTask(this.root, 10);
    SingleLatestTaskScheduler.get().submit(timerTask);
    this.progressBarController = new ProgressBarController(timerTask);
    this.menuBarController = new MenuBarController(stage);
  }

  public GridPane loadGridPane() throws Exception {
    GridPane gridPane = (GridPane) App.loadFxComponent("gameGrid.fxml", this.gameGridController);

    for (int i = 0, c = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        GridUnit unit = this.game.getGrid()[i][j];
        Pane pane = (Pane) gridPane.getChildren().get(c++);
        ImageView imgView = (ImageView) pane.getChildren().get(0);
        imgView.setImage(unit.getImage());
        imgView.fitWidthProperty().bind(pane.widthProperty());
        imgView.fitHeightProperty().bind(pane.heightProperty());
      }
    }

    return gridPane;
  }

  public VBox loadGameResultPane() throws Exception {
    return (VBox) App.loadFxComponent("gameResult.fxml", this.gameResultController);
  }

  public ProgressBar loadGameProgressBar() throws Exception {
    return (ProgressBar) App.loadFxComponent("progressBar.fxml", this.progressBarController);
  }

  public MenuBar loadMenuBar() throws Exception {
    MenuBar menuBar = (MenuBar) App.loadFxComponent("menuBar.fxml", this.menuBarController);
    menuBar.prefWidthProperty().bind(this.root.widthProperty());
    return menuBar;
  }

  public void endGame() {
    this.gameGridController.onGameEnd();
    this.gameResultController.onGameEnd();
  }
}
