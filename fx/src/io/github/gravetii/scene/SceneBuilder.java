package io.github.gravetii.scene;

import io.github.gravetii.App;
import io.github.gravetii.controller.GameController;
import io.github.gravetii.controller.GridController;
import io.github.gravetii.controller.MenuBarController;
import io.github.gravetii.controller.StartController;
import io.github.gravetii.game.Game;
import io.github.gravetii.util.AppLogger;
import io.github.gravetii.util.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

class SceneBuilder {

  public BorderPane root;

  private Stage stage;

  SceneBuilder(Stage stage) {
    this.root = new BorderPane();
    this.stage = stage;
  }

  private static Image getRandomImage(String dir) throws Exception {
    String fPath = App.class.getResource(dir).getFile();
    Stream<Path> files = Files.list(Paths.get(fPath));
    int count = Math.toIntExact(files.count());
    int r = 1 + new Random().nextInt(count);
    return new Image(App.class.getResourceAsStream(dir + "/" + r + ".jpg"), 0, 0, false, false);
  }

  MenuBar loadMenuBar() throws Exception {
    FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/menuBar.fxml"));
    loader.setController(new MenuBarController(this.stage));
    MenuBar menuBar = loader.load();
    menuBar.prefWidthProperty().bind(this.root.widthProperty());
    AppLogger.info(getClass().getCanonicalName(), "Loaded menu bar: " + menuBar);
    return menuBar;
  }

  SplitPane loadSplitPane() throws Exception {
    FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/grid.fxml"));
    loader.setController(new GridController(this.stage));
    SplitPane splitPane = loader.load();
    AppLogger.info(getClass().getCanonicalName(), "Loaded split pane: " + splitPane);
    return splitPane;
  }

  Pane loadStartPane() throws Exception {
    FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/start.fxml"));
    loader.setController(new StartController(this.stage));
    Pane pane = loader.load();
    pane.prefHeightProperty().bind(root.heightProperty());
    pane.prefWidthProperty().bind(root.widthProperty());
    ImageView imgView = (ImageView) pane.getChildren().get(0);
    imgView.setImage(SceneBuilder.getRandomImage("skins"));
    imgView.fitHeightProperty().bind(pane.heightProperty());
    imgView.fitWidthProperty().bind(pane.widthProperty());
    return pane;
  }

  GridPane loadGamePane(Game game) throws Exception {
    FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/game.fxml"));
    loader.setController(new GameController(this.stage, game));
    GridPane gridPane = loader.load();
    Image img = getRandomImage("background");
    BackgroundSize size = new BackgroundSize(100, 100, true, true, true, true);
    BackgroundImage background =
        new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT, size);
    gridPane.setBackground(new Background(background));

    for (int i = 0, c = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        String letter = game.getGrid()[i][j].getLetter();
        Pane pane = (Pane) gridPane.getChildren().get(c++);
        ImageView imgView = (ImageView) pane.getChildren().get(0);
        imgView.setImage(new Image(App.class.getResourceAsStream("images/" + letter + ".png")));
        imgView.fitHeightProperty().bind(pane.heightProperty());
        imgView.fitWidthProperty().bind(pane.widthProperty());
      }
    }

    AppLogger.info(getClass().getCanonicalName(), "Loaded grid pane: " + gridPane);
    return gridPane;
  }

  void addNode(Node node) {
    this.root.setCenter(node);
  }

  Scene build() {
    Scene currentScene = this.stage.getScene();
    double width = currentScene == null ? Constants.DEFAULT_SCENE_WIDTH : currentScene.getWidth();
    double height = currentScene == null ? Constants.DEFAULT_SCENE_HEIGHT : currentScene.getHeight();
    return new Scene(this.root, width, height);
  }
}
