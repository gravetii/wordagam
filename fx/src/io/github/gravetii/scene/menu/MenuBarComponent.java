package io.github.gravetii.scene.menu;

import io.github.gravetii.controller.MenuBarController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuBarComponent extends FxComponent<MenuBarController, MenuBar> {

  private Stage stage;
  private BorderPane root;

  public MenuBarComponent(Stage stage, BorderPane root) throws Exception {
    super("menuBar.fxml");
    this.stage = stage;
    this.root = root;
    this.create();
  }

  @Override
  protected MenuBarController createController() {
    return new MenuBarController(this.stage);
  }

  @Override
  protected MenuBar createNode() throws Exception {
    MenuBar menuBar = this.loadNode();
    menuBar.prefWidthProperty().bind(root.widthProperty());
    return menuBar;
  }
}
