package io.github.gravetii.controller;

import io.github.gravetii.App;
import io.github.gravetii.game.GameService;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.game.GameScene;
import io.github.gravetii.scene.help.AboutScene;
import io.github.gravetii.scene.theme.ChangeThemeScene;
import io.github.gravetii.store.PreferenceStore;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Modality;
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
      scene.show();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  public void exit(ActionEvent event) throws Exception {
    GameService.close();
    this.stage.close();
    PreferenceStore.close();
    Platform.exit();
  }

  @FXML
  public void editTheme(ActionEvent event) {
    try {
      Stage stage = new Stage();
      stage.setResizable(false);
      stage.initOwner(this.stage);
      stage.initModality(Modality.APPLICATION_MODAL);
      App.setThemeDimensions(stage);
      FxScene scene = new ChangeThemeScene(stage);
      scene.show();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  public void showAbout(ActionEvent event) {
    try {
      Stage stage = new Stage();
      stage.setResizable(false);
      stage.initOwner(this.stage);
      stage.initModality(Modality.APPLICATION_MODAL);
      App.setAboutDimensions(stage);
      FxScene scene = new AboutScene(stage);
      scene.show();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
