package io.github.gravetii;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

import java.awt.event.ActionEvent;

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
