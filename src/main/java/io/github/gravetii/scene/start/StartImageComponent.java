package io.github.gravetii.scene.start;

import io.github.gravetii.controller.BasicController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class StartImageComponent extends FxComponent<BasicController, Pane> {
  private static final String SKINS_PATH = "images/skin/1.gif";

  private BorderPane root;

  public StartImageComponent(BorderPane root) throws Exception {
    super("startImage.fxml");
    this.root = root;
    this.create();
  }

  @Override
  protected BasicController createController() {
    return new BasicController();
  }

  @Override
  protected Pane createNode() throws Exception {
    Pane pane = this.loadNode();
    pane.prefHeightProperty().bind(root.heightProperty());
    pane.prefWidthProperty().bind(root.widthProperty());
    ImageView imgView = (ImageView) pane.getChildren().get(0);
    Image img = new Image(SKINS_PATH);
    imgView.setImage(img);
    imgView.fitHeightProperty().bind(pane.heightProperty());
    imgView.fitWidthProperty().bind(pane.widthProperty());
    return pane;
  }
}
