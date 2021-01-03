package io.github.gravetii.scene;

import io.github.gravetii.controller.FxController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public abstract class FxComponent<C extends FxController, N extends Node> {
  private final String fxml;

  private C controller;
  private N node;

  protected FxComponent(String fxml) {
    this.fxml = fxml;
  }

  protected abstract C createController();

  protected abstract N createNode() throws Exception;

  protected void create() throws Exception {
    this.controller = this.createController();
    this.node = this.createNode();
  }

  protected N loadNode() throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + this.fxml));
    loader.setController(this.controller);
    return loader.load();
  }

  public C getController() {
    return controller;
  }

  public N getNode() {
    return node;
  }
}
