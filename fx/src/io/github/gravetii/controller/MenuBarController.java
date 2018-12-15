package io.github.gravetii.controller;

import io.github.gravetii.game.GameService;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.game.GameScene;
import io.github.gravetii.scene.help.AboutScene;
import io.github.gravetii.scene.help.WhatIsItScene;
import io.github.gravetii.scene.settings.SetGameTimeScene;
import io.github.gravetii.scene.theme.ChangeThemeScene;
import io.github.gravetii.store.Settings;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuBarController implements FxController {

  private Stage stage;
  @FXML private MenuItem gameTimeMenuItem;

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

  @FXML
  public void newGame(ActionEvent event) {
    try {
      FxScene scene = new GameScene(this.stage);
      scene.show();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  public void setGameTime(ActionEvent event) {
    try {
      Stage stage = this.newModalWindow();
      FxScene scene = new SetGameTimeScene(stage);
      scene.show();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  public void exit(ActionEvent event) throws Exception {
    GameService.close();
    this.stage.close();
    Settings.close();
    Platform.exit();
  }

  @FXML
  public void editTheme(ActionEvent event) {
    try {
      Stage stage = this.newModalWindow();
      FxScene scene = new ChangeThemeScene(stage);
      scene.show();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  public void showAbout(ActionEvent event) {
    try {
      Stage stage = this.newModalWindow();
      FxScene scene = new AboutScene(stage);
      scene.show();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  public void whatIsIt(ActionEvent event) {
    try {
      Stage stage = this.newModalWindow();
      FxScene scene = new WhatIsItScene(stage);
      scene.show();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
