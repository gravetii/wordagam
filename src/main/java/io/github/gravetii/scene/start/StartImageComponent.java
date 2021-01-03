package io.github.gravetii.scene.start;

import io.github.gravetii.controller.BaseController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class StartImageComponent extends FxComponent<BaseController, Pane> {
  private static final String SKIN_PATH = "/images/1.gif";

  private final BorderPane root;

  public StartImageComponent(BorderPane root) throws Exception {
    super("startImage.fxml");
    this.root = root;
    this.create();
  }

  @Override
  protected BaseController createController() {
    return new BaseController();
  }

  @Override
  protected Pane createNode() throws Exception {
    Pane pane = this.loadNode();
    pane.prefHeightProperty().bind(root.heightProperty());
    pane.prefWidthProperty().bind(root.widthProperty());
    ImageView imgView = (ImageView) pane.getChildren().get(0);
    Image img = new Image(getClass().getResource(SKIN_PATH).toExternalForm());
    imgView.setImage(img);
    imgView.fitHeightProperty().bind(pane.heightProperty());
    imgView.fitWidthProperty().bind(pane.widthProperty());
    return pane;
  }
}
