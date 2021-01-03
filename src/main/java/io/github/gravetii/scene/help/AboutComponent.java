package io.github.gravetii.scene.help;

import io.github.gravetii.controller.BaseController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.control.TextArea;

public class AboutComponent extends FxComponent<BaseController, TextArea> {
  public AboutComponent() throws Exception {
    super("about.fxml");
    this.create();
  }

  @Override
  protected BaseController createController() {
    return new BaseController();
  }

  @Override
  protected TextArea createNode() throws Exception {
    return this.loadNode();
  }
}
