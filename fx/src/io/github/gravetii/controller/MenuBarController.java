package io.github.gravetii.controller;

import io.github.gravetii.game.GameService;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.GameScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MenuBarController implements FxController {

  private Stage stage;

  public MenuBarController(Stage stage) {
    this.stage = stage;
  }

  @FXML
  public void newGame(ActionEvent event) {
    try {
      FxScene scene = new GameScene(this.stage);
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
