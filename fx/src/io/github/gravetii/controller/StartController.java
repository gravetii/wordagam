package io.github.gravetii.controller;

import javafx.stage.Stage;

import java.util.logging.Logger;

public class StartController implements FxController {

    private static final Logger logger = Logger.getLogger(StartController.class.getCanonicalName());

    private Stage stage;

    public StartController(Stage stage) {
        this.stage = stage;
    }


}
