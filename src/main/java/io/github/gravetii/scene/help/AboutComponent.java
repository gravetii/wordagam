package io.github.gravetii.scene.help;

import io.github.gravetii.controller.BasicController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.control.TextArea;

public class AboutComponent extends FxComponent<BasicController, TextArea> {
  public AboutComponent() throws Exception {
    super("about.fxml");
    this.create();
  }

  @Override
  protected BasicController createController() {
    return new BasicController();
  }

  @Override
  protected TextArea createNode() throws Exception {
    return this.loadNode();
  }
}
