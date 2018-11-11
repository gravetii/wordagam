package io.github.gravetii;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartScene extends FxScene {

    public StartScene(Stage stage) {
        super(stage);
    }

    @Override
    public Scene build() throws Exception {
        MenuBar menuBar = this.builder.loadMenuBar();
        this.builder.addNode(menuBar);
        Pane pane = this.builder.loadStartPane();
        this.builder.addNode(pane);
        return this.builder.build();
    }

}
