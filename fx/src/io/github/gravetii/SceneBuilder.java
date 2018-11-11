package io.github.gravetii;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class SceneBuilder {

    private static final Logger logger = Logger.getLogger(SceneBuilder.class.getCanonicalName());

    private AnchorPane root;

    private Stage stage;

    SceneBuilder(Stage stage) {
        this.root = new AnchorPane();
        this.stage = stage;
    }

    MenuBar loadMenuBar() throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("menuBar.fxml"));
        loader.setController(new MenuBarController(this.stage));
        MenuBar menuBar = loader.load();
        menuBar.prefWidthProperty().bind(this.root.widthProperty());
        logger.info("Loaded menu bar: " + menuBar);
        return menuBar;
    }

    Pane loadStartPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("start.fxml"));
        loader.setController(new StartController());
        Pane pane = loader.load();
        AnchorPane.setTopAnchor(pane, 29.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
        pane.prefHeightProperty().bind(root.heightProperty());
        pane.prefWidthProperty().bind(root.widthProperty());
        ImageView imgView = (ImageView) pane.getChildren().get(0);
        imgView.setImage(SceneBuilder.getStartingImage());
        imgView.fitHeightProperty().bind(pane.heightProperty());
        imgView.fitWidthProperty().bind(pane.widthProperty());
        return pane;
    }

    GridPane loadGridPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("grid.fxml"));
        loader.setController(new GridPaneController());
        GridPane gridPane = loader.load();
        AnchorPane.setTopAnchor(gridPane, 29.0);
        AnchorPane.setBottomAnchor(gridPane, 0.0);
        gridPane.prefHeightProperty().bind(this.root.heightProperty());
        gridPane.prefWidthProperty().bind(this.root.widthProperty());
        logger.info("Loaded grid pane: " + gridPane);
        return gridPane;
    }

    void addNode(Node node) {
        this.root.getChildren().add(node);
    }

    Scene build() {
        return new Scene(this.root, 540, 460);
    }

    private static Image getStartingImage() throws IOException {
        String fPath = App.class.getResource("skins").getFile();
        Stream<Path> files = Files.list((Paths.get(fPath)));
        int count = Math.toIntExact(files.count());
        int r = 1 + new Random().nextInt(count);
        return new Image(App.class.getResourceAsStream("skins/" + r + ".jpg"));
    }

}
