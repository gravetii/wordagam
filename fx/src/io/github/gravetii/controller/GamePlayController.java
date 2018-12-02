package io.github.gravetii.controller;

import io.github.gravetii.pojo.GamePlayResult;
import io.github.gravetii.pojo.WordPoint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GamePlayController implements FxController {

  private final GameController ref;

  @FXML private TableView<WordPoint> tblDisplay;

  @FXML private TableColumn<WordPoint, Integer> idTblCol;

  @FXML private TableColumn<WordPoint, String> wordTblCol;

  @FXML private TableColumn<WordPoint, Integer> pointsTblCol;

  public GamePlayController(GameController ref) {
    this.ref = ref;
  }

  @FXML
  public void initialize() {
    this.idTblCol.prefWidthProperty().bind(tblDisplay.widthProperty().divide(4));
    this.wordTblCol.prefWidthProperty().bind(tblDisplay.widthProperty().divide(2));
    this.pointsTblCol.prefWidthProperty().bind(tblDisplay.widthProperty().divide(4));
    this.idTblCol.setCellValueFactory(new PropertyValueFactory<>("index"));
    this.wordTblCol.setCellValueFactory(new PropertyValueFactory<>("word"));
    this.pointsTblCol.setCellValueFactory(new PropertyValueFactory<>("points"));
    this.tblDisplay.setRowFactory(
        callback -> {
          TableRow<WordPoint> row = new TableRow<>();
          row.setOnMouseClicked(
              event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                  WordPoint wordPoint = row.getItem();
                  this.ref.revisitWord(wordPoint.getWord());
                }
              });

          return row;
        });
  }

  @FXML
  public void onGoBtnClick(ActionEvent event) {
    GamePlayResult result = this.ref.validateWordOnBtnClick();
    if (result != null) {
      this.tblDisplay.getItems().add(result.getWordPoint());
    }
  }
}
