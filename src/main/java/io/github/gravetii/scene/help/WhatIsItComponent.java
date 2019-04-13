package io.github.gravetii.scene.help;

import io.github.gravetii.scene.FxComponent;
import javafx.scene.control.TextArea;

public class WhatIsItComponent extends FxComponent<Void, TextArea> {

  protected WhatIsItComponent() throws Exception {
    super("whatIsIt.fxml");
    this.create();
  }

  @Override
  protected Void createController() {
    return null;
  }

  @Override
  protected TextArea createNode() throws Exception {
    return this.loadNode();
  }
}
