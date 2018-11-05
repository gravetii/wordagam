package io.github.gravetii;

import io.github.gravetii.game.Game;
import io.github.gravetii.service.GameService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class Controller {

    private App app;

    private GameService service;

    Controller(App app) {
        this.app = app;
        this.service = new GameService();
    }


    @FXML
    private MenuItem play;

    @FXML
    void playGame(ActionEvent event) {
        System.out.println("New game created");
        Game game = this.service.get();
        System.out.println(game);
    }



}
