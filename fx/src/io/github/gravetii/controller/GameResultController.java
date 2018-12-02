package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.pojo.GamePlayResult;
import io.github.gravetii.pojo.WordPoint;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Map;

public class GameResultController implements FxController {

  private final Game game;
  private final GameController ref;

  @FXML private TableView<WordPoint> tblDisplay;

  @FXML private TableColumn<WordPoint, Integer> idTblCol;

  @FXML private TableColumn<WordPoint, String> wordTblCol;

  @FXML private TableColumn<WordPoint, Integer> pointsTblCol;

  public GameResultController(Game game, GameController ref) {
    this.game = game;
    this.ref = ref;
  }

  @FXML
  public void initialize() {
    this.idTblCol.setCellValueFactory(new PropertyValueFactory<>("index"));
    this.wordTblCol.setCellValueFactory(new PropertyValueFactory<>("word"));
    this.pointsTblCol.setCellValueFactory(new PropertyValueFactory<>("points"));
    this.display();
    this.tblDisplay.setRowFactory(
        callback -> {
          TableRow<WordPoint> row = new TableRow<>();
          row.setOnMouseClicked(
              event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                  String word = row.getItem().getWord();
                  this.ref.revisitResult(game.getResult().get(word));
                }
              });

          return row;
        });
  }

  private void display() {
    Map<String, GamePlayResult> result = this.game.getResult();
    result.forEach(
        (word, gamePlayResult) -> {
          WordPoint wordPoint = gamePlayResult.getWordPoint();
          this.tblDisplay.getItems().add(wordPoint);
        });
  }
}
