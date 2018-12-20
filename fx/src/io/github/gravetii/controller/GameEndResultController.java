package io.github.gravetii.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class GameEndResultController implements FxController {

  private final GameGridController ref;
  private GameResultDisplayer displayer;
  private TableViewRenderer renderer;

  @FXML private TextArea txtArea;

  @FXML private TableView<GameResultDisplayer.TableResult> tblDisplay;
  @FXML private TableColumn<GameResultDisplayer.TableResult, Integer> idTblCol;
  @FXML private TableColumn<GameResultDisplayer.TableResult, String> wordTblCol;
  @FXML private TableColumn<GameResultDisplayer.TableResult, Integer> pointsTblCol;

  public GameEndResultController(GameGridController ref) {
    this.ref = ref;
  }

  @FXML
  public void initialize() {
    this.idTblCol.prefWidthProperty().bind(tblDisplay.widthProperty().divide(4));
    this.wordTblCol.prefWidthProperty().bind(tblDisplay.widthProperty().divide(2));
    this.pointsTblCol.prefWidthProperty().bind(tblDisplay.widthProperty().divide(4));
    this.idTblCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    this.wordTblCol.setCellValueFactory(new PropertyValueFactory<>("word"));
    this.pointsTblCol.setCellValueFactory(new PropertyValueFactory<>("score"));
    this.renderer = new TableViewRenderer(this.ref);
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
    this.tblDisplay.setRowFactory(callback ->
            renderer.getStartFactory());
    this.displayUserWords();
    this.tblDisplay.setRowFactory(callback ->
            renderer.getEndFactory());
    this.displayGameWords();
  }

  public void updateText(String text) {
    this.txtArea.setText(text);
  }
}
