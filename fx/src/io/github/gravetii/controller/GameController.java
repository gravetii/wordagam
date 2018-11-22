package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.pojo.WordPoint;
import io.github.gravetii.util.GridPoint;
import io.github.gravetii.util.GridUnit;
import io.github.gravetii.util.Utils;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    GridPoint point = Utils.getGridPointFromImageViewLabel(imgView.getId());
    GridUnit unit = game.getGridUnit(point);
    ValidationResult result = this.validator.validateClick(unit);
    this.applyStyleAfterValidation(imgView, result);
  }

  private void applyStyleAfterValidation(ImageView imgView, ValidationResult result) {
    switch (result) {
      case ALL_INVALID:
        this.styler.forInvalidClick();
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

  public WordPoint validateWordOnBtnClick() {
    WordPoint result = null;
    boolean valid = this.validator.validateWord();

    if (!valid) {
      this.styler.forIncorrectWord();
    } else {
      result = this.validator.get();
      if (result == null) {
        this.styler.forRepeatedWord();
      } else {
        this.styler.forCorrectWord();
      }
    }

    this.validator.reset();
    return result;
  }
}
