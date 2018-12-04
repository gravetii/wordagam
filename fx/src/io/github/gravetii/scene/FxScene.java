package io.github.gravetii.scene;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class FxScene {

  protected Stage stage;
  protected BorderPane root;

  protected FxScene(Stage stage) {
    this.stage = stage;
    this.root = new BorderPane();
  }

  protected void showTop(Node node) {
    this.root.setTop(node);
  }

  protected void showLeft(Node node) {
    this.root.setLeft(node);
  }

  protected void showCenter(Node node) {
    this.root.setCenter(node);
  }

  protected void showRight(Node node) {
    this.root.setRight(node);
  }

  protected void showBottom(Node node) {
    this.root.setBottom(node);
  }

  protected abstract void build() throws Exception;

  protected void setEventHandlers() {}

  protected abstract String title();

  public void show() throws Exception {
    this.build();
    Scene scene = new Scene(this.root);
    this.stage.setScene(scene);
    this.stage.setTitle(this.title());
    this.stage.show();
    this.setEventHandlers();
  }
}
