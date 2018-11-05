package io.github.gravetii;

import io.github.gravetii.game.Game;
import io.github.gravetii.service.GameService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {

    private App app;

    private GameService service;

    Controller(App app) {
        this.app = app;
        this.service = new GameService();
    }

    @FXML
    void playGame(ActionEvent event) {
        try {
            System.out.println("New game created");
            Game game = this.service.get();
            System.out.println(game);
            this.app.showGame(game);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
