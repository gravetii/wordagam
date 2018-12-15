package io.github.gravetii.scene.about;

import io.github.gravetii.scene.FxComponent;
import javafx.scene.control.TextArea;

public class AboutComponent extends FxComponent<Void, TextArea> {

  public AboutComponent() throws Exception {
    super("about.fxml");
    this.create();
  }

  @Override
  protected Void createController() {
    return null;
  }

  @Override
  protected TextArea createNode() throws Exception {
    return (TextArea) this.loadNode();
  }
}
