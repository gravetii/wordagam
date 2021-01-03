package io.github.gravetii.scene.help;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;

import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

import java.util.Optional;

public class WhatIsItScene extends FxScene {
  private final WhatIsItComponent component;

  public WhatIsItScene(Stage stage) throws Exception {
    super(stage);
    this.component = new WhatIsItComponent();
  }

  @Override
  protected void build() {
    this.showCenter(this.component);
  }

  @Override
  protected String title() {
    return "What is it?";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions = new FxDimensions(new Dimension2D(550, 400));
    return Optional.of(dimensions);
  }
}
