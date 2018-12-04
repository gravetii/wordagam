package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.pojo.GamePlayResult;
import io.github.gravetii.util.GridPoint;
import io.github.gravetii.util.GridUnit;
import io.github.gravetii.util.Utils;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameController implements FxController {

  private Stage stage;
  private Game game;

  private GamePlayValidator validator;
  private GamePlayStyler styler;
  private Map<String, GamePlayResult> wordToResultMap;

  public GameController(Stage stage, Game game) {
    this.stage = stage;
    this.game = game;
    this.validator = new GamePlayValidator(game);
    this.styler = new GamePlayStyler();
    this.wordToResultMap = new HashMap<>();
  }

  @FXML
  public void onImgViewClick(MouseEvent event) {
    ImageView imgView = (ImageView) event.getSource();
    GridPoint point = Utils.getGridPointFromImageViewLabel(imgView.getId());
    GridUnit unit = game.getGridUnit(point);
    ValidationResult validation = this.validator.validate(unit);
    this.applyStyleAfterValidation(imgView, validation);
  }

  private void applyStyleAfterValidation(ImageView imgView, ValidationResult result) {
    switch (result) {
      case ALL_INVALID:
        this.styler.invalidate();
        break;
      case LAST_INVALID:
        this.styler.forLastInvalidClick(imgView);
        break;
      case ALL_VALID:
        this.styler.forValidClick(imgView);
        break;
      default:
        break;
    }
  }

  public GamePlayResult validateWordOnBtnClick() {
    GamePlayResult result = null;
    String word = this.validator.validate();

    if (word == null) {
      this.styler.forIncorrectWord();
    } else if (this.wordToResultMap.containsKey(word)) {
      this.styler.forRepeatedWord();
    } else {
      int points = this.game.getWordPoints(word);
      List<GridUnit> seq = this.validator.getSeq();
      result = new GamePlayResult(word, points, seq);
      this.wordToResultMap.put(word, result);
      this.styler.forCorrectWord();
    }

    this.validator.reset();
    return result;
  }

  public void revisitWord(String word) {
    this.revisitResult(this.wordToResultMap.get(word));
  }

  public void revisitResult(GamePlayResult result) {
    List<ImageView> imgViews =
        result
            .getSeq()
            .stream()
            .map(
                gridUnit -> {
                  String id = Utils.getImgViewLabelFromGridPoint(gridUnit.getGridPoint());
                  return (ImageView) this.stage.getScene().lookup("#" + id);
                })
            .collect(Collectors.toList());

    this.validator.reset();
    this.styler.revisit(imgViews);
  }
}
