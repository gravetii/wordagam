package io.github.gravetii;

import io.github.gravetii.game.Game;
import io.github.gravetii.service.GameFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class Controller {

    private static final Logger logger = Logger.getLogger(Controller.class.getCanonicalName());

    private Stage stage;

    Controller(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void newGame(ActionEvent event) {
        try {
            Game game = GameFactory.get().fetch();
            FxScene scene = new GameScene(this.stage, game);
            scene.show("New game");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
