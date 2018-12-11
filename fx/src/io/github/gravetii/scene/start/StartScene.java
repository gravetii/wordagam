package io.github.gravetii.scene.start;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartScene extends FxScene {

  private StartSceneBuilder builder;

  public StartScene(Stage stage) throws Exception {
    super(stage);
    this.builder = new StartSceneBuilder(this.stage, this.root);
    this.build();
  }

  @Override
  protected void build() throws Exception {
    MenuBar menuBar = this.builder.loadMenuBar();
    Pane pane = this.builder.loadStartPane();
    this.showTop(menuBar);
    this.showCenter(pane);
  }

  @Override
  public String title() {
    return "W-O-R-D-A-G-A-M";
  }

  @Override
  protected FxDimensions preferredDimensions() {
    return new FxDimensions(
        new Dimension2D(960, 630), new Dimension2D(960, 630), new Dimension2D(1100, 732));
  }
}
