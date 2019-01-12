package io.github.gravetii.scene;

import io.github.gravetii.App;
import javafx.fxml.FXMLLoader;

public abstract class FxComponent<C, N> {

  private C controller;
  private N node;
  private String fxml;

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
    FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/" + this.fxml));
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
