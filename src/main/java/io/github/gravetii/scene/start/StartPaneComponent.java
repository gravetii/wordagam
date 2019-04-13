package io.github.gravetii.scene.start;

import io.github.gravetii.scene.FxComponent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class StartPaneComponent extends FxComponent<Void, Pane> {

  private BorderPane root;

  public StartPaneComponent(BorderPane root) throws Exception {
    super("start.fxml");
    this.root = root;
    this.create();
  }

  private static Image fetchSkin() throws Exception {
    String fPath = ClassLoader.getSystemResource("images/skin").getFile();
    Stream<Path> files = Files.list(Paths.get(fPath));
    int count = Math.toIntExact(files.count());
    int r = 1 + ThreadLocalRandom.current().nextInt(count);
    return new Image("images/skin/" + r + ".jpg");
  }

  @Override
  protected Void createController() {
    return null;
  }

  @Override
  protected Pane createNode() throws Exception {
    Pane pane = this.loadNode();
    pane.prefHeightProperty().bind(root.heightProperty());
    pane.prefWidthProperty().bind(root.widthProperty());
    ImageView imgView = (ImageView) pane.getChildren().get(0);
    imgView.setImage(fetchSkin());
    imgView.fitHeightProperty().bind(pane.heightProperty());
    imgView.fitWidthProperty().bind(pane.widthProperty());
    return pane;
  }
}
