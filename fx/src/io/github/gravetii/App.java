package io.github.gravetii;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class App extends Application {

    private static final Logger logger = Logger.getLogger(App.class.getCanonicalName());

    private Controller controller;

    @Override
    public void start(Stage stage) throws Exception {
        logger.info("Starting application...");
        this.controller = new Controller(stage);
        this.controller.start();
    }

    @Override
    public void stop() {
        logger.info("Stopping application...");
        this.controller.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
