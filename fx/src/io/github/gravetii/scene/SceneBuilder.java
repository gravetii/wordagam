package io.github.gravetii.scene;

import io.github.gravetii.App;
import io.github.gravetii.controller.*;
import io.github.gravetii.game.Game;
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
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class SceneBuilder {

  private Stage stage;

  private BorderPane root;

  SceneBuilder(Stage stage, BorderPane root) {
    this.stage = stage;
    this.root = root;
  }

  private static Image getRandomImage(String dir) throws Exception {
    String fPath = App.class.getResource(dir).getFile();
    Stream<Path> files = Files.list(Paths.get(fPath));
    int count = Math.toIntExact(files.count());
    int r = 1 + ThreadLocalRandom.current().nextInt(count);
    return new Image(App.class.getResourceAsStream(dir + "/" + r + ".jpg"), 0, 0, false, false);
  }

  private Node loadFxComponent(String fxml, FxController controller) throws Exception {
    FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
    loader.setController(controller);
    return loader.load();
  }

  public MenuBar loadMenuBar() throws Exception {
    MenuBar menuBar =
        (MenuBar) loadFxComponent("fxml/menuBar.fxml", new MenuBarController(this.stage));
    menuBar.prefWidthProperty().bind(this.root.widthProperty());
    return menuBar;
  }

  public Pane loadStartPane() throws Exception {
    Pane pane = (Pane) loadFxComponent("fxml/start.fxml", new StartController(this.stage));
    pane.prefHeightProperty().bind(root.heightProperty());
    pane.prefWidthProperty().bind(root.widthProperty());
    ImageView imgView = (ImageView) pane.getChildren().get(0);
    imgView.setImage(SceneBuilder.getRandomImage("skins"));
    imgView.fitHeightProperty().bind(pane.heightProperty());
    imgView.fitWidthProperty().bind(pane.widthProperty());
    return pane;
  }

  private SplitPane loadSplitPane(FxController controller) throws Exception {
    return (SplitPane) loadFxComponent("fxml/grid.fxml", controller);
  }

  private GridPane loadGamePane(Game game, FxController controller) throws Exception {
    GridPane gridPane = (GridPane) loadFxComponent("fxml/game.fxml", controller);
    Image img = getRandomImage("themes");
    BackgroundSize size = new BackgroundSize(100, 100, true, true, true, true);
    BackgroundImage background =
        new BackgroundImage(
            img,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            size);
    gridPane.setBackground(new Background(background));

    for (int i = 0, c = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        String letter = game.getGrid()[i][j].getLetter();
        Pane pane = (Pane) gridPane.getChildren().get(c++);
        ImageView imgView = (ImageView) pane.getChildren().get(0);
        Image image = new Image(App.class.getResourceAsStream("images/" + letter + ".png"));
        imgView.setImage(new Image(App.class.getResourceAsStream("images/" + letter + ".png")));
        imgView.fitHeightProperty().bind(pane.heightProperty());
        imgView.fitWidthProperty().bind(pane.widthProperty());
      }
    }

    return gridPane;
  }

  public GameComponent loadGameComponent(Game game) throws Exception {
    GameController gameController = new GameController(this.stage, game);
    GridController gridController = new GridController(gameController);
    GridPane gridPane = this.loadGamePane(game, gameController);
    SplitPane splitPane = this.loadSplitPane(gridController);
    return new GameComponent(gridPane, splitPane);
  }

  public Scene build() {
    return new Scene(this.root);
  }
}
