package io.github.gravetii;

import io.github.gravetii.game.Game;
import io.github.gravetii.service.GameFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MenuBarController {

    private Stage stage;

    public MenuBarController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void newGame(ActionEvent event) {
        try {
            Game game = GameFactory.get().fetch();
            GameScene scene = new GameScene(this.stage, game);
            scene.show("New game");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
