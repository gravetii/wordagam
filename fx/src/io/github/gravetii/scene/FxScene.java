package io.github.gravetii.scene;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class FxScene {

  protected SceneBuilder builder;

  private Stage stage;

  private BorderPane root;

  FxScene(Stage stage) {
    this.stage = stage;
    this.root = new BorderPane();
    this.builder = new SceneBuilder(this.stage, this.root);
  }

  protected FxScene showTop(Node node) {
    this.root.setTop(node);
    return this;
  }

  protected FxScene showLeft(Node node) {
    this.root.setLeft(node);
    return this;
  }

  protected FxScene showCenter(Node node) {
    this.root.setCenter(node);
    return this;
  }

  protected FxScene showRight(Node node) {
    this.root.setRight(node);
    return this;
  }

  protected abstract Scene build() throws Exception;

  public void show(String title) throws Exception {
    this.show(title, false);
  }

  public void show(String title, boolean wait) throws Exception {
    this.stage.setScene(this.build());
    this.stage.setTitle(title);
    if (wait) {
      this.stage.showAndWait();
    } else {
      this.stage.show();
    }
  }
}
