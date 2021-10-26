module wordagam {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.logging;
    requires java.prefs;
    requires java.base;
    requires kotlin.stdlib;

    opens io.github.gravetii.game;
    opens io.github.gravetii.scene;
    opens io.github.gravetii.scene.start;
    opens io.github.gravetii.scene.help;
    opens io.github.gravetii.scene.menu;
    opens io.github.gravetii.scene.settings;
    opens io.github.gravetii.scene.theme;
    opens io.github.gravetii.controller;
    opens io.github.gravetii.theme;
    opens io.github.gravetii.dictionary;
    opens io.github.gravetii.validation;

    exports io.github.gravetii;
    exports io.github.gravetii.scene;
    exports io.github.gravetii.scene.help;
    exports io.github.gravetii.scene.start;
    exports io.github.gravetii.scene.menu;
    exports io.github.gravetii.scene.settings;
    exports io.github.gravetii.scene.theme;
    exports io.github.gravetii.controller;
    exports io.github.gravetii.db;
    exports io.github.gravetii.dictionary;
    exports io.github.gravetii.game;
    exports io.github.gravetii.model;
    exports io.github.gravetii.theme;
    exports io.github.gravetii.validation;
}
