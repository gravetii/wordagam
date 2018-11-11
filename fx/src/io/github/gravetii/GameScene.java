package io.github.gravetii;

import io.github.gravetii.game.Game;
import io.github.gravetii.util.GridUnit;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameScene {

    private SceneBuilder builder;

    private Stage stage;

    private Game game;

    public GameScene(Stage stage, Game game) {
        this.stage = stage;
        this.game = game;
        this.builder = new SceneBuilder(stage);
    }

    public void showGraph() throws Exception {
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

        System.out.println("Loaded new game");
        this.builder.addNode(gridPane);
        Scene scene = this.builder.build();
        this.stage.setScene(scene);
        this.stage.setTitle("New game");
        this.stage.show();
    }
}
