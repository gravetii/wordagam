package io.github.gravetii.scene.start;

import io.github.gravetii.App;
import io.github.gravetii.controller.MenuBarController;
import io.github.gravetii.controller.StartController;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class StartSceneBuilder {

  private BorderPane root;
  private MenuBarController menuBarController;
  private StartController startController;

  public StartSceneBuilder(Stage stage, BorderPane root) {
    this.root = root;
    this.menuBarController = new MenuBarController(stage);
    this.startController = new StartController(stage);
  }

  private static Image skin() throws Exception {
    String fPath = App.class.getResource("skins").getFile();
    Stream<Path> files = Files.list(Paths.get(fPath));
    int count = Math.toIntExact(files.count());
    int r = 1 + ThreadLocalRandom.current().nextInt(count);
    return new Image(App.class.getResourceAsStream("skins/" + r + ".jpg"), 0, 0, false, false);
  }

  public MenuBar loadMenuBar() throws Exception {
    MenuBar menuBar = (MenuBar) App.loadFxComponent("menuBar.fxml", this.menuBarController);
    menuBar.prefWidthProperty().bind(this.root.widthProperty());
    return menuBar;
  }

  public Pane loadStartPane() throws Exception {
    Pane pane = (Pane) App.loadFxComponent("start.fxml", this.startController);
    pane.prefHeightProperty().bind(this.root.heightProperty());
    pane.prefWidthProperty().bind(this.root.widthProperty());
    ImageView imgView = (ImageView) pane.getChildren().get(0);
    imgView.setImage(StartSceneBuilder.skin());
    imgView.fitHeightProperty().bind(pane.heightProperty());
    imgView.fitWidthProperty().bind(pane.widthProperty());
    return pane;
  }
}
