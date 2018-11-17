package io.github.gravetii.controller;

import io.github.gravetii.pojo.WordPoint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        this.wordTblCol.setCellValueFactory(new PropertyValueFactory<WordPoint, String>("word"));
        this.pointsTblCol.setCellValueFactory(new PropertyValueFactory<WordPoint, Integer>("points"));
    }

    @FXML
    public void onGoBtnClick(ActionEvent event) {
        WordPoint wordPoint = this.ref.validateWordOnBtnClick();
        if (wordPoint != null) {
            this.tblDisplay.getItems().add(wordPoint);
        }
    }

}
