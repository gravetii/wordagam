package io.github.gravetii;

import io.github.gravetii.game.Game;
import io.github.gravetii.game.GameFactory;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.GameScene;
import io.github.gravetii.scene.StartScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class Controller {

    private static final Logger logger = Logger.getLogger(Controller.class.getCanonicalName());

    private Stage stage;

    private GameFactory factory;

    Controller(Stage stage) {
        this.stage = stage;
        this.factory = new GameFactory();
        logger.info("Created instance of Controller");
    }

    void start() throws Exception {
        FxScene scene = new StartScene(this.stage, this);
        scene.show("WORDAGAM!");
    }

    @FXML
    public void newGame(ActionEvent event) {
        try {
            Game game = factory.fetch();
            FxScene scene = new GameScene(this.stage, this, game);
            scene.show("New game");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void close(ActionEvent event) {
        this.close();
    }

    void close() {
        this.factory.close();
        this.stage.close();
    }
}
