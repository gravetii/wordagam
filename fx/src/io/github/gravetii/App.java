package io.github.gravetii;

import io.github.gravetii.controller.FxController;
import io.github.gravetii.game.GameService;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.store.PreferenceStore;
import io.github.gravetii.util.AppLogger;
import io.github.gravetii.util.Constants;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;

public class App extends Application {

  private Stage stage;

  public static void main(String[] args) {
    launch(args);
  }

  public static Node loadFxComponent(String fxml, FxController controller) throws Exception {
    FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
    loader.setController(controller);
    return loader.load();
  }

  public static Node loadFxComponent(String fxml) throws Exception {
    return loadFxComponent(fxml, null);
  }

  private static void setPrimaryDimensions(Stage stage) {
    stage.setMinWidth(Constants.DEFAULT_SCENE_WIDTH);
    stage.setMinHeight(Constants.DEFAULT_SCENE_HEIGHT);
    stage.setMaxWidth(Constants.MAX_SCENE_WIDTH);
    stage.setMaxHeight(Constants.MAX_SCENE_HEIGHT);
    stage.setWidth(Constants.DEFAULT_SCENE_WIDTH);
    stage.setHeight(Constants.DEFAULT_SCENE_HEIGHT);
  }

  public static void setThemeDimensions(Stage stage) {
    stage.setWidth(Constants.THEME_SCENE_WIDTH);
    stage.setHeight(Constants.THEME_SCENE_HEIGHT);
  }

  public static void setAboutDimensions(Stage stage) {
    stage.setWidth(Constants.ABOUT_SCENE_WIDTH);
    stage.setHeight(Constants.ABOUT_SCENE_HEIGHT);
  }

  @Override
  public void start(Stage stage) throws Exception {
    AppLogger.info(getClass().getCanonicalName(), "Starting application...");
    this.stage = stage;
    this.stage.setOnCloseRequest(
        event -> {
          Platform.exit();
        });

    setPrimaryDimensions(this.stage);
    FxScene scene = new StartScene(stage);
    scene.show();
  }

  @Override
  public void init() {
    GameService.init();
  }

  @Override
  public void stop() throws Exception {
    GameService.close();
    this.stage.close();
    PreferenceStore.close();
  }
}
