package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.util.GridPoint;
import io.github.gravetii.util.GridUnit;
import io.github.gravetii.util.Utils;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class GameController implements FxController {

  private Stage stage;
  private Game game;

  private Map<String, Integer> wordsAndPoints;
  private GamePlayValidator validator;
  private GamePlayStyler styler;

  public GameController(Stage stage, Game game) {
    this.stage = stage;
    this.game = game;
    this.wordsAndPoints = new HashMap<>();
    this.validator = new GamePlayValidator(game);
    this.styler = new GamePlayStyler();
  }

  @FXML
  public void onImgViewClick(MouseEvent event) {
    ImageView imgView = (ImageView) event.getSource();
    this.styler.apply(imgView);
    GridPoint point = Utils.getGridPointFromImageViewLabel(imgView.getId());
    GridUnit unit = game.getGridUnit(point);
    if (!this.validator.validateClick(unit)) {
      this.styler.reset();
    }
  }

  public Pair<String, Integer> validateWordOnBtnClick() {
    String word = this.validator.validateWord();
    this.validator.invalidate();
    this.styler.reset();

    if (word == null || this.wordsAndPoints.containsKey(word)) {
      return null;
    }

    int points = this.game.getWordPoints(word);
    this.wordsAndPoints.put(word, points);
    return new Pair<>(word, points);
  }

}
