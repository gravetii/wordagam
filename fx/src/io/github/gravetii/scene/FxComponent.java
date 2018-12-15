package io.github.gravetii.scene;

import io.github.gravetii.App;
import io.github.gravetii.game.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

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
    this.setEventFilters();
  }

  protected Parent loadNode() throws Exception {
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

  protected void onGameEnd() {}

  private void setEventFilters() {
    Parent parent = (Parent) node;
    parent.addEventFilter(
        Game.EndEvent.GAME_END_EVENT_EVENT_TYPE,
        (event -> {
          this.onGameEnd();
          event.consume();
        }));
  }
}
