package io.github.gravetii;

import io.github.gravetii.service.GameFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class App extends Application {

    private static final Logger logger = Logger.getLogger(App.class.getCanonicalName());

    @Override
    public void init() {
        GameFactory.get();
    }

    @Override
    public void start(Stage stage) throws Exception {
        logger.info("Starting application...");
        StartScene scene = new StartScene(stage);
        scene.show("WORDAGAM");
    }

    @Override
    public void stop() {
        logger.info("Stopping application...");
        GameFactory.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
