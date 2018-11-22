package io.github.gravetii;

import io.github.gravetii.game.GameService;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.StartScene;
import io.github.gravetii.util.AppLogger;
import io.github.gravetii.util.Utils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class App extends Application {

  private Stage stage;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    AppLogger.info(getClass().getCanonicalName(), "Starting application...");
    this.stage = stage;
    this.stage.setOnCloseRequest(
        event -> {
          Platform.exit();
        });

    Utils.setPrimaryDimensions(this.stage);
    FxScene scene = new StartScene(stage);
    scene.show("WORDAGAM!");
  }

  @Override
  public void init() throws Exception {
    GameService.init();
  }

  @Override
  public void stop() {
    GameService.close();
    this.stage.close();
  }
}
