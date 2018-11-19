package io.github.gravetii;

import io.github.gravetii.game.GameService;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.StartScene;
import io.github.gravetii.util.AppLogger;
import io.github.gravetii.util.Constants;
import javafx.application.Application;
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
    this.setDimensions();
    FxScene scene = new StartScene(stage);
    scene.show("WORDAGAM!");
  }

  @Override
  public void init() throws Exception {
    GameService.init();
  }

  private void setDimensions() {
    this.stage.setMinWidth(Constants.DEFAULT_SCENE_WIDTH);
    this.stage.setMinHeight(Constants.DEFAULT_SCENE_HEIGHT);
    this.stage.setMaxWidth(Constants.MAX_SCENE_WIDTH);
    this.stage.setMaxHeight(Constants.MAX_SCENE_HEIGHT);
    this.stage.setWidth(Constants.DEFAULT_SCENE_WIDTH);
    this.stage.setHeight(Constants.DEFAULT_SCENE_HEIGHT);
  }

  @Override
  public void stop() {
    AppLogger.info(getClass().getCanonicalName(), "Stopping application...");
    GameService.close();
    this.stage.close();
  }
}
