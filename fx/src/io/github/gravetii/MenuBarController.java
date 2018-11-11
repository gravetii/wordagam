package io.github.gravetii;

import io.github.gravetii.game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MenuBarController {

    private Stage stage;

    public MenuBarController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private MenuItem newGame;

    @FXML
    public void newGame(ActionEvent event) {
        try {
            Game game = AppContext.get().games().fetch();
            GameScene scene = new GameScene(this.stage, game);
            scene.showGraph();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
