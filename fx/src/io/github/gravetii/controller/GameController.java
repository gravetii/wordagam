package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.util.GridPoint;
import io.github.gravetii.util.GridUnit;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class GameController implements FxController {

  private Stage stage;
  private Game game;

  private Set<String> userWords;
  private GamePlayValidator validator;

  public GameController(Stage stage, Game game) {
    this.stage = stage;
    this.game = game;
    this.userWords = new HashSet<>();
    this.validator = new GamePlayValidator(game);
  }

  private static GridPoint getGridPointFromImageViewLabel(String label) {
    String[] tokens = label.split("\\$")[1].split("_");
    return new GridPoint(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
  }

  @FXML
  public void onClick(MouseEvent event) {
    ImageView imgView = (ImageView) event.getSource();
    GridPoint point = getGridPointFromImageViewLabel(imgView.getId());
    GridUnit unit = game.getGridUnit(point);
    boolean isValid = this.validator.validateClick(unit);
  }

}
