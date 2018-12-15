package io.github.gravetii.scene.settings;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

import java.util.Optional;

public class SetGameTimeScene extends FxScene {

  private SetGameTimeComponent component;

  public SetGameTimeScene(Stage stage) throws Exception {
    super(stage);
    this.component = new SetGameTimeComponent(stage);
  }

  @Override
  protected void build() throws Exception {
    this.showCenter(this.component.getNode());
  }

  @Override
  protected String title() {
    return "Configure game time";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions = new FxDimensions(new Dimension2D(500, 180));
    return Optional.of(dimensions);
  }
}
