package io.github.gravetii.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static io.github.gravetii.controller.GameResultDisplayer.TableResult;

public class GameResultController implements FxController {

  private final GameGridController ref;
  private GameResultDisplayer displayer;
  private TableViewRenderer renderer;

  @FXML private TableView<TableResult> tblDisplay;
  @FXML private TableColumn<TableResult, Integer> idTblCol;
  @FXML private TableColumn<TableResult, String> wordTblCol;
  @FXML private TableColumn<TableResult, Integer> pointsTblCol;

  public GameResultController(GameGridController ref) {
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
    this.tblDisplay.setRowFactory(callback -> this.renderer.getStartFactory());
    this.displayer = new GameResultDisplayer(this.tblDisplay);
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
