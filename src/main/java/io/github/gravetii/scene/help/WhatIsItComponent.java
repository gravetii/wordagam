package io.github.gravetii.scene.help;

import io.github.gravetii.controller.BaseController;
import io.github.gravetii.scene.FxComponent;

import javafx.scene.control.TextArea;

public class WhatIsItComponent extends FxComponent<BaseController, TextArea> {
  protected WhatIsItComponent() throws Exception {
    super("whatIsIt.fxml");
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
