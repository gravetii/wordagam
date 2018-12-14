package io.github.gravetii.scene;

import io.github.gravetii.App;
import javafx.fxml.FXMLLoader;

public abstract class FxComponent<Controller, Node> {

  private Controller controller;
  private Node node;
  private String fxml;

  protected FxComponent(String fxml) {
    this.fxml = fxml;
  }

  protected abstract Controller createController();

  protected abstract Node createNode() throws Exception;

  protected void create() throws Exception {
    this.controller = this.createController();
    this.node = this.createNode();
  }

  protected javafx.scene.Node loadNode() throws Exception {
    FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/" + this.fxml));
    loader.setController(this.controller);
    return loader.load();
  }

  public Controller getController() {
    return controller;
  }

  public Node getNode() {
    return node;
  }
}
