package io.github.gravetii.controller;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.util.Comparator;

public class GameEndResultController implements FxController {

  private static final Interpolator INTERPOLATOR = Interpolator.SPLINE(0.400, 0.600, 0.600, 1.000);

  private final GameGridController ref;
  private GameResultDisplayer displayer;

  @FXML private Label resultBox;
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
          TableRow<GameResultDisplayer.TableResult> row =
              new TableRow<>() {
                @Override
                public void updateItem(GameResultDisplayer.TableResult item, boolean empty) {
                  getStyleClass().remove("user-table-row-cell");
                  super.updateItem(item, empty);
                  if (!empty && item.isByUser()) getStyleClass().add("user-table-row-cell");
                }
              };

          row.setOnMouseClicked(
              event -> {
                if (!row.isEmpty()) {
                  GameResultDisplayer.TableResult result = row.getItem();
                  if (!result.isEmpty()) {
                    String word = result.getWord();
                    if (result.isByUser()) this.ref.revisitUserWord(word);
                    else this.ref.revisitGameWord(word);
                  }
                }
              });

          return row;
        });

    this.wordTblCol.setComparator(Comparator.comparingInt(String::length));
    this.displayer = new GameResultDisplayer(this.tblDisplay);
    this.showAllWords();
  }

  private void showUserWords() {
    this.ref.getAllUserWords().values().forEach(x -> this.displayer.showUserWord(x));
  }

  private void showGameWords() {
    this.ref.getAllGameWords().values().forEach(x -> this.displayer.showGameWord(x));
  }

  private void showAllWords() {
    this.showUserWords();
    this.showGameWords();
  }

  private void applyAnimation(Node node) {
    Timeline timeline =
        new Timeline(
            new KeyFrame(
                Duration.millis(0),
                new KeyValue(node.opacityProperty(), 0, INTERPOLATOR),
                new KeyValue(node.translateYProperty(), -3000, INTERPOLATOR)),
            new KeyFrame(
                Duration.millis(600),
                new KeyValue(node.opacityProperty(), 1, INTERPOLATOR),
                new KeyValue(node.translateYProperty(), 25, INTERPOLATOR)),
            new KeyFrame(
                Duration.millis(750), new KeyValue(node.translateYProperty(), -10, INTERPOLATOR)),
            new KeyFrame(
                Duration.millis(900), new KeyValue(node.translateYProperty(), 5, INTERPOLATOR)),
            new KeyFrame(
                Duration.millis(1000), new KeyValue(node.translateYProperty(), 0, INTERPOLATOR)));

    timeline.play();
  }

  public void updateText(String text) {
    this.applyAnimation(this.resultBox);
    this.resultBox.setText(text);
    this.resultBox.setAlignment(Pos.CENTER);
  }
}
