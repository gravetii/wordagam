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

public class GameController implements FxController {

  private Stage stage;
  private Game game;

  private GamePlayValidator validator;
  private GamePlayStyler styler;

  public GameController(Stage stage, Game game) {
    this.stage = stage;
    this.game = game;
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
      this.styler.forInvalidClick();
    }
  }

  public Pair<String, Integer> validateWordOnBtnClick() {
    Pair<String, Integer> result = null;
    boolean valid = this.validator.validateWord();

    if (!valid) {
      this.styler.forIncorrectWord();
    }
    else {
      result = this.validator.get();
      if (result == null) {
        this.styler.forRepeatedWord();
      }
      else {
        this.styler.forCorrectWord();
      }
    }

    this.validator.invalidate();
    return result;
  }
}
