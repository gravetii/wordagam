package io.github.gravetii.controller;

import io.github.gravetii.pojo.WordPoint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

public class GridController implements FxController {

    private GameController ref;

    @FXML
    private TableView<WordPoint> tblDisplay;

    @FXML
    private TableColumn<WordPoint, String> wordTblCol;

    @FXML
    private TableColumn<WordPoint, Integer> pointsTblCol;

    public GridController(GameController ref) {
        this.ref = ref;
    }

    @FXML
    public void initialize() {
        this.wordTblCol.setCellValueFactory(new PropertyValueFactory<>("word"));
        this.pointsTblCol.setCellValueFactory(new PropertyValueFactory<>("points"));
    }

    @FXML
    public void onGoBtnClick(ActionEvent event) {
        Pair<String, Integer> wordAndPoints = this.ref.validateWordOnBtnClick();
        if (wordAndPoints != null) {
            WordPoint wordPoint = new WordPoint(wordAndPoints.getKey().toUpperCase(), wordAndPoints.getValue());
            this.tblDisplay.getItems().add(wordPoint);
        }
    }

}
