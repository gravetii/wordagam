package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class GameController implements FxController {

  private Stage stage;

  private Game game;

  public GameController(Stage stage, Game game) {
    this.stage = stage;
    this.game = game;
  }

  @FXML
  public void onClick(MouseEvent event) {
    System.out.println("Mouse click");
    ImageView imgView = (ImageView) event.getSource();
    System.out.println(imgView);
  }
}
