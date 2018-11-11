package io.github.gravetii;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MenuBarController {

    private App app;

    @FXML
    private MenuItem newGame;

    MenuBarController(App app) {
        this.app = app;
    }

    @FXML
    public void newGame(ActionEvent event) {

    }
}
