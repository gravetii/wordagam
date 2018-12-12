package io.github.gravetii.scene.start;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Optional;

public class StartScene extends FxScene {

  private StartSceneBuilder builder;

  public StartScene(Stage stage) {
    super(stage);
    this.builder = new StartSceneBuilder(this.stage, this.root);
  }

  @Override
  protected void build() throws Exception {
    MenuBar menuBar = this.builder.loadMenuBar();
    Pane pane = this.builder.loadStartPane();
    this.showTop(menuBar).showCenter(pane);
  }

  @Override
  public String title() {
    return "-WORDAGAM-";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions =
        new FxDimensions(
            new Dimension2D(960, 630), new Dimension2D(960, 630), new Dimension2D(1100, 732));
    return Optional.of(dimensions);
  }
}
