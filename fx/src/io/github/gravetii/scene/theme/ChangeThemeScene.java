package io.github.gravetii.scene.theme;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;

public class ChangeThemeScene extends FxScene {

  private ChangeThemeSceneBuilder builder;

  public ChangeThemeScene(Stage stage) {
    super(stage);
    this.builder = new ChangeThemeSceneBuilder(this.stage, this.root);
  }

  @Override
  protected void build() throws Exception {
    GridPane themesPane = this.builder.loadThemesPane();
    ToolBar footer = this.builder.loadFooter();
    this.showCenter(themesPane).showBottom(footer);
  }

  @Override
  public String title() {
    return "Change theme";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions =
        new FxDimensions(
            new Dimension2D(800, 550), new Dimension2D(800, 550), new Dimension2D(800, 550));
    return Optional.of(dimensions);
  }
}
