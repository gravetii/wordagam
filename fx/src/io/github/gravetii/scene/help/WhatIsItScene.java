package io.github.gravetii.scene.help;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

import java.util.Optional;

public class WhatIsItScene extends FxScene {

  private WhatIsItComponent component;

  public WhatIsItScene(Stage stage) throws Exception {
    super(stage);
    this.component = new WhatIsItComponent();
  }

  @Override
  protected void build() throws Exception {
    this.showCenter(this.component.getNode());
  }

  @Override
  protected String title() {
    return "What is it?";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions = new FxDimensions(new Dimension2D(500, 350));
    return Optional.of(dimensions);
  }
}
