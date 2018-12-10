package io.github.gravetii.scene.help;

import io.github.gravetii.App;
import io.github.gravetii.scene.FxScene;
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
}
