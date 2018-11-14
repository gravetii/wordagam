package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.game.GameService;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.GameScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MenuBarController implements FxController {

  private Stage stage;

  private GameService service;

  public MenuBarController(Stage stage) {
    this.stage = stage;
    this.service = new GameService();
  }

  @FXML
  public void newGame(ActionEvent event) {
    try {
      Game game = service.fetch();
      FxScene scene = new GameScene(this.stage, game);
      scene.show("New game");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  public void exit(ActionEvent event) {
    GameService.close();
    this.stage.close();
  }
}
