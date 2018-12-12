package io.github.gravetii.scene;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Optional;

public abstract class FxScene {

  protected Stage stage;
  protected BorderPane root;

  protected FxScene(Stage stage) {
    this.stage = stage;
    this.root = new BorderPane();
  }

  public FxScene showTop(Node node) {
    this.root.setTop(node);
    return this;
  }

  public FxScene showLeft(Node node) {
    this.root.setLeft(node);
    return this;
  }

  public FxScene showCenter(Node node) {
    this.root.setCenter(node);
    return this;
  }

  public FxScene showRight(Node node) {
    this.root.setRight(node);
    return this;
  }

  public FxScene showBottom(Node node) {
    this.root.setBottom(node);
    return this;
  }

  protected abstract void build() throws Exception;

  protected abstract String title();

  protected Optional<FxDimensions> preferredDimensions() {
    return Optional.empty();
  }

  protected void setEventHandlers() {}

  private void setDimensions() {
    this.preferredDimensions().ifPresent(dimensions -> dimensions.setFor(this.stage));
  }

  public void show() throws Exception {
    this.build();
    Scene scene = new Scene(this.root);
    this.stage.setScene(scene);
    this.stage.setTitle(this.title());
    this.setDimensions();
    this.stage.show();
    this.setEventHandlers();
  }
}
