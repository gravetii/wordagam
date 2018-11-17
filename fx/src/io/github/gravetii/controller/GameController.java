package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.pojo.WordPoint;
import io.github.gravetii.util.GridPoint;
import io.github.gravetii.util.GridUnit;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class GameController implements FxController {

  private Stage stage;
  private Game game;

  private Map<String, Integer> wordsAndPoints;
  private GamePlayValidator validator;

  @FXML
  private ListView<String> lstView;

  public GameController(Stage stage, Game game) {
    this.stage = stage;
    this.game = game;
    this.wordsAndPoints = new HashMap<>();
    this.validator = new GamePlayValidator(game);
  }

  private static GridPoint getGridPointFromImageViewLabel(String label) {
    String[] tokens = label.split("\\$")[1].split("_");
    return new GridPoint(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
  }

  @FXML
  public void onImgViewClick(MouseEvent event) {
    ImageView imgView = (ImageView) event.getSource();
    GridPoint point = getGridPointFromImageViewLabel(imgView.getId());
    GridUnit unit = game.getGridUnit(point);
    this.validator.validateClick(unit);
  }

  public WordPoint validateWordOnBtnClick() {
    String word = this.validator.validateWord();
    this.validator.invalidate();
    if (word == null || this.wordsAndPoints.containsKey(word)) {
      return null;
    }

    int points = this.game.getWordPoints(word);
    this.wordsAndPoints.put(word, points);
    return new WordPoint(word, points);
  }

}
