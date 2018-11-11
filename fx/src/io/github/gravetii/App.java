package io.github.gravetii;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class App extends Application {

    private static final Logger logger = Logger.getLogger(App.class.getCanonicalName());

    private Stage stage;

    @Override
    public void init() {
        AppContext.create();
    }

    @Override
    public void start(Stage stage) throws Exception {
        logger.info("Starting application...");
        this.stage = stage;
        StartScene scene = new StartScene(stage);
        scene.showGraph();
    }

    @Override
    public void stop() {
        logger.info("Stopping application...");
        AppContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
