package io.github.gravetii.controller;

import io.github.gravetii.game.GameService;
import io.github.gravetii.scene.EditThemeScene;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.GameScene;
import io.github.gravetii.store.PreferenceStore;
import io.github.gravetii.util.Utils;
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
      scene.show("New game");
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
      Utils.setThemeDimensions(stage);
      FxScene scene = new EditThemeScene(stage);
      scene.show("Change theme");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
