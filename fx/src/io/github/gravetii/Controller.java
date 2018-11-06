package io.github.gravetii;

import io.github.gravetii.game.Game;
import io.github.gravetii.service.GameService;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;

import java.awt.*;
import java.util.logging.Logger;

public class Controller {

    private static final Logger logger = Logger.getLogger(Controller.class.getCanonicalName());

    private App app;

    private GameService service;

    Controller(App app) {
        this.app = app;
        this.service = new GameService();
    }

    @FXML
    void playGame(ActionEvent event) {
        try {
            logger.info("Playing new game...");
            Game game = this.service.get();
            System.out.println(game);
            this.app.showGame(game);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
