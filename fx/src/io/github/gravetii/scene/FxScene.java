package io.github.gravetii.scene;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class FxScene {

    SceneBuilder builder;

    private Stage stage;

    FxScene(Stage stage) {
        this.stage = stage;
        this.builder = new SceneBuilder(stage);
    }

    protected abstract Scene build() throws Exception;

    public void show(String title) throws Exception {
        this.stage.setScene(this.build());
        this.stage.setTitle(title);
        this.stage.show();
    }
}
