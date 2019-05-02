package io.github.gravetii.scene.start;

import io.github.gravetii.controller.BasicController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class StartPaneComponent extends FxComponent<BasicController, Pane> {
  private BorderPane root;

  public StartPaneComponent(BorderPane root) throws Exception {
    super("start.fxml");
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
    imgView.setImage(new Image("images/skin/1.jpg"));
    imgView.fitHeightProperty().bind(pane.heightProperty());
    imgView.fitWidthProperty().bind(pane.widthProperty());
    return pane;
  }
}
