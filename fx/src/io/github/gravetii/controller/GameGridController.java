package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.game.UserResult;
import io.github.gravetii.pojo.GameStats;
import io.github.gravetii.pojo.WordResult;
import io.github.gravetii.util.GridPoint;
import io.github.gravetii.util.GridUnit;
import io.github.gravetii.util.Utils;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameGridController implements FxController {
  private Game game;
  private Pane[][] panes;
  private Map<String, ImageView> imgViewMap;
  private GamePlayValidator validator;
  private GamePlayStyler styler;
  private UserResult userResult;
  private int rotCount = 0;

  @FXML private GridPane gamePane;

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

  public GameGridController(Game game) {
    this.game = game;
    this.imgViewMap = new HashMap<>();
    this.validator = new GamePlayValidator(game.result());
    this.userResult = new UserResult();
    this.panes =  new Pane[4][4];
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
    this.styler = new GamePlayStyler(this.gamePane, this.imgViewMap.values());
    this.setPanes();
  }

  private void setPanes() {
    int c = 0;
    for (int i=0;i<4;++i) {
      for (int j=0;j<4;++j) {
        this.panes[i][j] = (Pane) this.gamePane.getChildren().get(c++);
      }
    }
  }

  @FXML
  public void onImgViewClick(MouseEvent event) {
    ImageView imgView = (ImageView) event.getSource();
    GridPoint point = Utils.getGridPointFromImageViewLabel(imgView.getId());
    GridUnit unit = game.getGridUnit(point);
    ValidationResult validation = this.validator.validate(unit);
    this.applyStyleAfterValidation(imgView, validation);
  }

  private void rotateGrid() {
    for (int x = 0; x < 2; x++) {
      for (int y = x; y < 3-x; y++) {
        Pane temp = this.panes[y][3-x];
        this.panes[y][3-x] = this.panes[x][y];
        this.panes[x][y] = this.panes[3-y][x];
        this.panes[3-y][x] = this.panes[3-x][3-y];
        this.panes[3-x][3-y] = temp;
      }
    }
  }

  public void rotate() {
    this.styler.rotateGamePane();
    this.rotateGrid();
    for (int i=0;i<4;++i) {
      for (int j=0;j<4;++j) {
        Pane pane = this.panes[i][j];
        GridPane.setRowIndex(pane, i);
        GridPane.setColumnIndex(pane, j);
      }
    }

    ++this.rotCount;
    if (this.rotCount % 4 == 0) {
      this.rotCount = 0;
    }
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

  public Optional<WordResult> validateWordOnBtnClick() {
    WordResult result = null;
    String word = this.validator.validate();

    if (word == null) {
      this.styler.forIncorrectWord();
    } else if (this.userResult.exists(word)) {
      this.styler.forRepeatedWord();
    } else {
      int points = this.game.result().getPoints(word);
      List<GridPoint> seq = this.validator.getSeq();
      result = new WordResult(word, points, seq);
      this.userResult.add(word, result);
      this.styler.forCorrectWord();
    }

    this.validator.reset();
    return result == null ? Optional.empty() : Optional.of(result);
  }

  public void revisitUserWord(String word) {
    WordResult result = this.userResult.get(word);
    this.revisit(result);
  }

  public void revisitGameWord(String word) {
    WordResult result = this.game.result().getWordResult(word);
    this.revisit(result);
  }

  public Map<String, WordResult> getAllGameWords() {
    return this.game.result().all();
  }

  public Map<String, WordResult> getAllUserWords() {
    return this.userResult.all();
  }

  private void revisit(WordResult result) {
    List<ImageView> imgViews =
        result
            .getSeq()
            .stream()
            .map(
                point -> {
                  String id = Utils.getImgViewLabelFromGridPoint(point);
                  return this.imgViewMap.get(id);
                })
            .collect(Collectors.toList());

    this.validator.reset();
    this.styler.revisit(imgViews);
  }

  public void endGame() {
    this.imgViewMap.forEach(
        (label, imgView) -> {
          imgView.setDisable(true);
        });

    this.validator.reset();
    this.styler.invalidate();
    while (this.rotCount != 0) {
      this.rotate();
    }

    this.styler.applyEndTransition();
  }

  public GameStats computeStats() {
    return new GameStats(this.game.result(), this.userResult);
  }
}
