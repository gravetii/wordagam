package io.github.gravetii.scene.help;

import io.github.gravetii.App;
import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

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
  protected FxDimensions preferredDimensions() {
    return new FxDimensions(new Dimension2D(500, 180));
  }
}
