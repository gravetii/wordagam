package io.github.gravetii.scene;

import io.github.gravetii.pojo.ChangeThemeComponent;
import javafx.stage.Stage;

public class ChangeThemeScene extends FxScene {

  public ChangeThemeScene(Stage stage) {
    super(stage);
  }

  @Override
  protected void build() throws Exception {
    ChangeThemeComponent component = this.builder.loadChangeThemeComponent();
    this.showCenter(component.getThemesPane()).showBottom(component.getFooter());
  }

  @Override
  public String title() {
    return "Change theme";
  }
}
