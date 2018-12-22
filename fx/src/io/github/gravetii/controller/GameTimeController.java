package io.github.gravetii.controller;

import io.github.gravetii.pojo.GameTime;
import io.github.gravetii.store.StoreUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GameTimeController implements FxController {

  private Stage stage;

  @FXML private TextField minutesTxtField;
  @FXML private TextField secondsTxtField;
  @FXML private Label validationLbl;
  @FXML private Button okBtn;
  @FXML private Button cancelBtn;

  public GameTimeController(Stage stage) {
    this.stage = stage;
  }

  @FXML
  public void initialize() {
    GameTime gameTime = StoreUtility.getGameTime();
    this.minutesTxtField.setText(Integer.toString(gameTime.getMinutes()));
    this.secondsTxtField.setText(Integer.toString(gameTime.getSeconds()));
  }

  @FXML
  public void ok(ActionEvent event) {
    GameTime gameTime = this.validate();
    if (gameTime != null) {
      StoreUtility.setGameTime(gameTime);
      this.stage.close();
    } else {
      this.validationLbl.setText("Invalid value");
    }
  }

  @FXML
  public void cancel(ActionEvent event) {
    this.stage.close();
  }

  private GameTime validate() {
    String minText = this.minutesTxtField.getText();
    String secText = this.secondsTxtField.getText();
    if (minText.isEmpty()) {
      minText = "0";
    }
    if (secText.isEmpty()) {
      secText = "0";
    }

    System.out.println(minText + ", " + secText);
    if (minText.matches("[0-9]+") && secText.matches("[0-9]+")) {
      try {
        int minutes = Integer.parseInt(minText);
        int seconds = Integer.parseInt(secText);
        if (minutes == 0 && seconds == 0) {
          return null;
        }

        if (seconds < 0 || seconds > 60) {
          return null;
        }

        return new GameTime(minutes, seconds);
      } catch (NumberFormatException e) {
        return null;
      }
    }

    return null;
  }
}
