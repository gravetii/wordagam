package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.util.GridPoint;
import io.github.gravetii.util.GridUnit;
import io.github.gravetii.util.Util;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameController implements FxController {

  private Stage stage;

  private Game game;

  public GameController(Stage stage, Game game) {
    this.stage = stage;
    this.game = game;
  }

  @FXML
  public void onClick(MouseEvent event) {
    ImageView imgView = (ImageView) event.getSource();
    GridPoint point = Util.getGridPointFromImageViewLabel(imgView.getId());
    GridUnit unit = this.game.getGrid()[point.x][point.y];
    System.out.println(imgView);
  }
}
