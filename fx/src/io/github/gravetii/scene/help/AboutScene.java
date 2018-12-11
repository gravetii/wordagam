package io.github.gravetii.scene.help;

import io.github.gravetii.App;
import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.Optional;

public class AboutScene extends FxScene {

  public AboutScene(Stage stage) throws Exception {
    super(stage);
    this.build();
  }

  @Override
  protected void build() throws Exception {
    TextArea area = (TextArea) App.loadFxComponent("fxml/about.fxml");
    this.showCenter(area);
  }

  @Override
  protected String title() {
    return "About";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions = new FxDimensions(new Dimension2D(500, 180));
    return Optional.of(dimensions);
  }
}
