package io.github.gravetii.scene;

import io.github.gravetii.theme.ThemeService;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class FxScene {

  protected SceneBuilder builder;
  protected Stage stage;
  protected BorderPane root;

  FxScene(Stage stage) {
    this.stage = stage;
    this.root = new BorderPane();
    this.builder = new SceneBuilder(stage, root);
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

  protected abstract void build() throws Exception;

  public abstract String title();

  protected void setEventHandlers() {}

  protected void applyCurrentTheme() {
    String styleSheet = new ThemeService().loadCurrentTheme().getStyleSheet();
    this.root.getStylesheets().clear();
    this.root.getStylesheets().add(styleSheet);
  }

  public void show() throws Exception {
    this.build();
    Scene scene = new Scene(this.root);
    this.stage.setScene(scene);
    this.stage.setTitle(this.title());
    this.stage.show();
    this.setEventHandlers();
  }
}
