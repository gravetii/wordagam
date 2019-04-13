package io.github.gravetii.scene.start;

import io.github.gravetii.scene.FxComponent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class StartPaneComponent extends FxComponent<Void, Pane> {
  private BorderPane root;

  public StartPaneComponent(BorderPane root) throws Exception {
    super("start.fxml");
    this.root = root;
    this.create();
  }

  private static Image fetchSkin() throws Exception {
    return new Image("images/skin/1.jpg");
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
