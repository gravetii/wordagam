package io.github.gravetii.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class GameEndResultController implements FxController {

  private final GameGridController ref;
  private GameResultDisplayer displayer;

  @FXML private TextArea txtArea;

  @FXML private TableView<GameResultDisplayer.TableResult> tblDisplay;
  @FXML private TableColumn<GameResultDisplayer.TableResult, String> wordTblCol;
  @FXML private TableColumn<GameResultDisplayer.TableResult, Integer> pointsTblCol;

  public GameEndResultController(GameGridController ref) {
    this.ref = ref;
  }

  @FXML
  public void initialize() {
    this.wordTblCol.setCellValueFactory(new PropertyValueFactory<>("word"));
    this.pointsTblCol.setCellValueFactory(new PropertyValueFactory<>("score"));
    this.tblDisplay.setRowFactory(
        callback -> {
          TableRow<GameResultDisplayer.TableResult> row = new TableRow<>();
          row.setOnMouseClicked(
              event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                  GameResultDisplayer.TableResult result = row.getItem();
                  if (!result.isEmpty()) {
                    String word = result.getWord();
                    if (result.isByUser()) {
                      this.ref.revisitUserWord(word);
                    } else {
                      this.ref.revisitGameWord(word);
                    }
                  }
                }
              });

          return row;
        });

    this.displayer = new GameResultDisplayer(this.tblDisplay);
    this.showAllWords();
  }

  private void displayUserWords() {
    this.ref
        .getAllUserWords()
        .forEach(
            (word, result) -> {
              this.displayer.showUserWord(result);
            });
  }

  private void displayGameWords() {
    this.ref
        .getAllGameWords()
        .forEach(
            (word, result) -> {
              this.displayer.showGameWord(result);
            });
  }

  private void showAllWords() {
    this.displayUserWords();
    this.displayer.showEmpty();
    this.displayGameWords();
  }

  public void updateText(String text) {
    this.txtArea.setText(text);
  }
}
