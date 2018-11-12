package io.github.gravetii.scene;

import io.github.gravetii.App;
import io.github.gravetii.Controller;
import io.github.gravetii.util.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Stream;

class SceneBuilder {

    private static final Logger logger = Logger.getLogger(SceneBuilder.class.getCanonicalName());

    private AnchorPane root;

    private Stage stage;
    private Controller controller;

    SceneBuilder(Stage stage, Controller controller) {
        this.root = new AnchorPane();
        this.stage = stage;
        this.controller = controller;
    }

    MenuBar loadMenuBar() throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/menuBar.fxml"));
        loader.setController(controller);
        MenuBar menuBar = loader.load();
        menuBar.prefWidthProperty().bind(this.root.widthProperty());
        logger.info("Loaded menu bar: " + menuBar);
        return menuBar;
    }

    Pane loadStartPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/start.fxml"));
        loader.setController(controller);
        Pane pane = loader.load();
        AnchorPane.setTopAnchor(pane, 29.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
        pane.prefHeightProperty().bind(root.heightProperty());
        pane.prefWidthProperty().bind(root.widthProperty());
        ImageView imgView = (ImageView) pane.getChildren().get(0);
        imgView.setImage(SceneBuilder.getRandomImage("skins"));
        imgView.fitHeightProperty().bind(pane.heightProperty());
        imgView.fitWidthProperty().bind(pane.widthProperty());
        return pane;
    }

    GridPane loadGridPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/grid.fxml"));
        loader.setController(controller);
        GridPane gridPane = loader.load();
        AnchorPane.setTopAnchor(gridPane, 29.0);
        AnchorPane.setBottomAnchor(gridPane, 0.0);
        gridPane.prefHeightProperty().bind(this.root.heightProperty());
        gridPane.prefWidthProperty().bind(this.root.widthProperty());
        Image img = getRandomImage("background");
        BackgroundSize size = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage background = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size);
        gridPane.setBackground(new Background(background));
        logger.info("Loaded grid pane: " + gridPane);
        return gridPane;
    }

    void addNode(Node node) {
        this.root.getChildren().add(node);
    }

    Scene build() {
        Scene currentScene = this.stage.getScene();
        double width = currentScene == null ? Constants.DEFAULT_SCENE_WIDTH : currentScene.getWidth();
        double height = currentScene == null ? Constants.DEFAULT_SCENE_HEIGHT : currentScene.getHeight();
        return new Scene(this.root, width, height);
    }

    private static Image getRandomImage(String dir) throws Exception {
        String fPath = App.class.getResource(dir).getFile();
        Stream<Path> files = Files.list(Paths.get(fPath));
        int count = Math.toIntExact(files.count());
        int r = 1 + new Random().nextInt(count);
        return new Image(App.class.getResourceAsStream(dir + "/" + r + ".jpg"), 0, 0, false, false);
    }

}
