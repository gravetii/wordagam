package io.github.gravetii.controller;

import io.github.gravetii.App;
import io.github.gravetii.db.PreferenceStore;
import io.github.gravetii.game.Game;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.game.GameScene;
import io.github.gravetii.scene.help.AboutScene;
import io.github.gravetii.scene.help.WhatIsItScene;
import io.github.gravetii.scene.settings.GameTimeScene;
import io.github.gravetii.scene.theme.ChangeThemeScene;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class MenuBarController implements FxController {
  private final Stage stage;

  public MenuBarController(Stage stage) {
    this.stage = stage;
  }

  private Stage newModalWindow() {
    Stage stage = new Stage();
    stage.setResizable(false);
    stage.initOwner(this.stage);
    stage.initModality(Modality.APPLICATION_MODAL);
    return stage;
  }

  private boolean showQuitGameAlert() {
    Alert alert =
        new Alert(
            Alert.AlertType.CONFIRMATION, "Quit current game?", ButtonType.NO, ButtonType.YES);
    alert.setHeaderText("");
    alert.setTitle("Quit?");
    alert.initOwner(this.stage);
    Optional<ButtonType> type = alert.showAndWait();
    return type.isPresent() && type.get() == ButtonType.YES;
  }

  @FXML
  public void newGame() throws Exception {
    if (!Game.isRunning() || this.showQuitGameAlert()) {
      FxScene scene = new GameScene(this.stage);
      scene.show();
      Game.setRunning(true);
    }
  }

  @FXML
  public void setGameTime() throws Exception {
    Stage stage = this.newModalWindow();
    FxScene scene = new GameTimeScene(stage);
    scene.show();
  }

  @FXML
  public void resetGameCounter() {
    PreferenceStore.resetGameId();
  }

  @FXML
  public void exit() {
    App.close(this.stage);
  }

  @FXML
  public void editTheme() throws Exception {
    Stage stage = this.newModalWindow();
    FxScene scene = new ChangeThemeScene(stage);
    scene.show();
  }

  @FXML
  public void showAbout() throws Exception {
    Stage stage = this.newModalWindow();
    FxScene scene = new AboutScene(stage);
    scene.show();
  }

  @FXML
  public void whatIsIt() throws Exception {
    Stage stage = this.newModalWindow();
    FxScene scene = new WhatIsItScene(stage);
    scene.show();
  }
}
