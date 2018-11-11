package io.github.gravetii.scene;

import io.github.gravetii.App;
import io.github.gravetii.Controller;
import io.github.gravetii.game.Game;
import io.github.gravetii.util.GridUnit;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameScene extends FxScene {

    private Game game;

    public GameScene(Stage stage, Controller controller, Game game) {
        super(stage, controller);
        this.game = game;
    }

    @Override
    public Scene build() throws Exception {
        MenuBar menuBar = this.builder.loadMenuBar();
        this.builder.addNode(menuBar);
        GridPane gridPane = this.builder.loadGridPane();
        GridUnit[][] grid = this.game.getGrid();

        int c = 0;
        for (int i=0;i<4;++i) {
            for (int j=0;j<4;++j) {
                String letter = grid[i][j].getLetter();
                Pane pane = (Pane) gridPane.getChildren().get(c++);
                ImageView imgView = (ImageView) pane.getChildren().get(0);
                imgView.setImage(new Image(App.class.getResourceAsStream("images/" + letter + ".png")));
                imgView.fitHeightProperty().bind(pane.heightProperty());
                imgView.fitWidthProperty().bind(pane.widthProperty());
            }
        }

        this.builder.addNode(gridPane);
        return this.builder.build();
    }

}
