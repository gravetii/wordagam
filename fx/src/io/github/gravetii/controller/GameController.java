package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.pojo.GamePlayResult;
import io.github.gravetii.util.GridPoint;
import io.github.gravetii.util.GridUnit;
import io.github.gravetii.util.Utils;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameController implements FxController {

  private Game game;

  private Map<String, ImageView> imgViewMap;
  private GamePlayValidator validator;
  private GamePlayStyler styler;
  private Map<String, GamePlayResult> wordToResultMap;

  @FXML private ImageView imgView$0_0;
  @FXML private ImageView imgView$0_1;
  @FXML private ImageView imgView$0_2;
  @FXML private ImageView imgView$0_3;
  @FXML private ImageView imgView$1_0;
  @FXML private ImageView imgView$1_1;
  @FXML private ImageView imgView$1_2;
  @FXML private ImageView imgView$1_3;
  @FXML private ImageView imgView$2_0;
  @FXML private ImageView imgView$2_1;
  @FXML private ImageView imgView$2_2;
  @FXML private ImageView imgView$2_3;
  @FXML private ImageView imgView$3_0;
  @FXML private ImageView imgView$3_1;
  @FXML private ImageView imgView$3_2;
  @FXML private ImageView imgView$3_3;

  public GameController(Game game) {
    this.game = game;
    this.imgViewMap = new HashMap<>();
    this.validator = new GamePlayValidator(game);
    this.styler = new GamePlayStyler();
    this.wordToResultMap = new HashMap<>();
  }

  @FXML
  public void initialize() {
    this.imgViewMap.put("imgView$0_0", this.imgView$0_0);
    this.imgViewMap.put("imgView$0_1", this.imgView$0_1);
    this.imgViewMap.put("imgView$0_2", this.imgView$0_2);
    this.imgViewMap.put("imgView$0_3", this.imgView$0_3);
    this.imgViewMap.put("imgView$1_0", this.imgView$1_0);
    this.imgViewMap.put("imgView$1_1", this.imgView$1_1);
    this.imgViewMap.put("imgView$1_2", this.imgView$1_2);
    this.imgViewMap.put("imgView$1_3", this.imgView$1_3);
    this.imgViewMap.put("imgView$2_0", this.imgView$2_0);
    this.imgViewMap.put("imgView$2_1", this.imgView$2_1);
    this.imgViewMap.put("imgView$2_2", this.imgView$2_2);
    this.imgViewMap.put("imgView$2_3", this.imgView$2_3);
    this.imgViewMap.put("imgView$3_0", this.imgView$3_0);
    this.imgViewMap.put("imgView$3_1", this.imgView$3_1);
    this.imgViewMap.put("imgView$3_2", this.imgView$3_2);
    this.imgViewMap.put("imgView$3_3", this.imgView$3_3);
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
    GamePlayResult result = this.wordToResultMap.get(word);
    if (result == null) {
      result = this.game.getResult().get(word);
    }

    this.revisitResult(result);
  }

  public void revisitResult(GamePlayResult result) {
    List<ImageView> imgViews =
        result
            .getSeq()
            .stream()
            .map(
                gridUnit -> {
                  String id = Utils.getImgViewLabelFromGridPoint(gridUnit.getGridPoint());
                  return this.imgViewMap.get(id);
                })
            .collect(Collectors.toList());

    this.validator.reset();
    this.styler.revisit(imgViews);
  }

  public void onGameEnd() {
    this.imgViewMap.forEach(
        (label, imgView) -> {
          imgView.setDisable(true);
        });
    this.validator.reset();
    this.styler.invalidate();
  }
}
