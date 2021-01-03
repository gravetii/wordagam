package io.github.gravetii.scene.help;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

import java.util.Optional;

public class AboutScene extends FxScene {
  private final AboutComponent component;

  public AboutScene(Stage stage) throws Exception {
    super(stage);
    this.component = new AboutComponent();
  }

  @Override
  protected void build() {
    this.showCenter(this.component);
  }

  @Override
  protected String title() {
    return "About";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions = new FxDimensions(new Dimension2D(500, 200));
    return Optional.of(dimensions);
  }
}
