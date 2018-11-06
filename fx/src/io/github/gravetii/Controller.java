package io.github.gravetii;

import io.github.gravetii.game.Game;
import io.github.gravetii.service.GameService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.logging.Logger;

class Controller {

    private static final Logger logger = Logger.getLogger(Controller.class.getCanonicalName());

    private final App app;

    private final GameService service;

    Controller(App app) {
        this.app = app;
        this.service = new GameService();
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
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

    private void close() {
        this.service.close();
    }

}
