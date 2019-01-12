package io.github.gravetii.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import static io.github.gravetii.controller.GameResultDisplayer.TableResult;

public class GameResultController implements FxController {

  private final GameGridController ref;
  private GameResultDisplayer displayer;

  @FXML private TableView<TableResult> tblDisplay;
  @FXML private TableColumn<TableResult, String> wordTblCol;
  @FXML private TableColumn<TableResult, Integer> pointsTblCol;

  public GameResultController(GameGridController ref) {
    this.ref = ref;
  }

  @FXML
  public void initialize() {
    this.wordTblCol.setCellValueFactory(new PropertyValueFactory<>("word"));
    this.pointsTblCol.setCellValueFactory(new PropertyValueFactory<>("score"));
    this.tblDisplay.setRowFactory(
        callback -> {
          TableRow<TableResult> row = new TableRow<>();
          row.setOnMouseClicked(
              event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                  TableResult result = row.getItem();
                  String word = result.getWord();
                  if (result.isByUser()) {
                    this.ref.revisitUserWord(word);
                  } else {
                    this.ref.revisitGameWord(word);
                  }
                }
              });

          return row;
        });

    this.displayer = new GameResultDisplayer(this.tblDisplay);
  }

  @FXML
  public void rotate(MouseEvent event) {
    this.ref.rotate();
  }

  @FXML
  public void onGoBtnClick(ActionEvent event) {
    this.ref
        .validateWordOnBtnClick()
        .ifPresent(
            result -> {
              this.displayer.showUserWord(result);
            });
  }
}
