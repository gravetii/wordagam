package io.github.gravetii.scene;

import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartScene extends FxScene {

  public StartScene(Stage stage) {
    super(stage);
  }

  @Override
  protected void build() throws Exception {
    MenuBar menuBar = this.builder.loadMenuBar();
    Pane pane = this.builder.loadStartPane();
    this.showTop(menuBar).showCenter(pane);
  }

  @Override
  public String title() {
    return "W-O-R-D-A-G-A-M";
  }
}
