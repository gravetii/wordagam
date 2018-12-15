package io.github.gravetii.controller;

import io.github.gravetii.store.Settings;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SetGameTimeController implements FxController {

  private Stage stage;

  @FXML private Slider slider;
  @FXML private TextField textField;
  @FXML private Button okButton;

  public SetGameTimeController(Stage stage) {
    this.stage = stage;
  }

  @FXML
  public void initialize() {
    this.textField.textProperty().bind(Bindings.format("%.1f", this.slider.valueProperty()));
    double time = Settings.getGameTime();
    this.slider.setValue(time);
  }

  @FXML
  public void onBtnClick() {
    Settings.setGameTime(this.slider.getValue());
    stage.close();
  }
}
