package io.github.gravetii;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartScene {

    private SceneBuilder builder;

    private Stage stage;

    public StartScene(Stage stage) {
        this.stage = stage;
        this.builder = new SceneBuilder(stage);
    }

    public void showGraph() throws Exception {
        MenuBar menuBar = this.builder.loadMenuBar();
        this.builder.addNode(menuBar);
        Pane pane = this.builder.loadStartPane();
        this.builder.addNode(pane);
        Scene scene = this.builder.build();
        this.stage.setScene(scene);
        this.stage.setTitle("WORDAGAM");
        this.stage.show();
    }

}
