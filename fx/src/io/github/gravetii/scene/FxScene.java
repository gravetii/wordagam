package io.github.gravetii.scene;

import io.github.gravetii.theme.Theme;
import io.github.gravetii.theme.ThemeService;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Optional;

public abstract class FxScene {

  protected Stage stage;
  protected BorderPane root;
  private ThemeService themes = new ThemeService();

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

  private void setDimensions() {
    this.preferredDimensions().ifPresent(dimensions -> dimensions.setFor(this.stage));
  }

  private void applyCurrentTheme() {
    String styleSheet = this.themes.loadCurrentTheme().getStyleSheet();
    this.root.getStylesheets().clear();
    this.root.getStylesheets().add(styleSheet);
  }

  protected void setEventHandlers() {
    this.root.addEventHandler(
        Theme.ChangeEvent.THEME_CHANGE_EVENT_TYPE,
        (event -> {
          this.applyCurrentTheme();
          event.consume();
        }));
  }

  public void show() throws Exception {
    this.build();
    this.applyCurrentTheme();
    Scene scene = new Scene(this.root);
    this.stage.setScene(scene);
    this.stage.setTitle(this.title());
    this.setDimensions();
    this.stage.show();
    this.setEventHandlers();
  }
}
