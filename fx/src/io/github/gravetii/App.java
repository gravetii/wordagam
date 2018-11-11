package io.github.gravetii;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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

public class App extends Application {

    private static final Logger logger = Logger.getLogger(App.class.getCanonicalName());

    private static AnchorPane root = new AnchorPane();

    private Stage stage;

    @Override
    public void init() {
        AppContext.create();
    }

    private static Image getStartingImage() throws IOException {
        String fPath = App.class.getResource("skins").getFile();
        Stream<Path> files = Files.list((Paths.get(fPath)));
        int count = Math.toIntExact(files.count());
        int r = 1 + new Random().nextInt(count);
        return new Image(App.class.getResourceAsStream("skins/" + r + ".jpg"));
    }

    private MenuBar loadMenuBar() throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("menuBar.fxml"));
        loader.setController(new MenuBarController(this));
        MenuBar menuBar = loader.load();
        menuBar.prefWidthProperty().bind(root.widthProperty());
        System.out.println("Loaded menu bar: " + menuBar);
        return menuBar;
    }

    private Pane loadStartPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("start.fxml"));
        loader.setController(new StartController(this));
        Pane pane = loader.load();
        AnchorPane.setTopAnchor(pane, 29.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
        pane.prefHeightProperty().bind(root.heightProperty());
        pane.prefWidthProperty().bind(root.widthProperty());
        ImageView imgView = (ImageView) pane.getChildren().get(0);
        imgView.setImage(App.getStartingImage());
        imgView.fitHeightProperty().bind(pane.heightProperty());
        imgView.fitWidthProperty().bind(pane.widthProperty());
        return pane;
    }

    private GridPane loadGridPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("grid.fxml"));
        loader.setController(new GridPaneController(this));
        GridPane gridPane = loader.load();
        AnchorPane.setTopAnchor(gridPane, 29.0);
        AnchorPane.setBottomAnchor(gridPane, 0.0);
        gridPane.prefHeightProperty().bind(root.heightProperty());
        gridPane.prefWidthProperty().bind(root.widthProperty());

        for (int i=0;i<gridPane.getChildren().size();++i) {
            Pane pane = (Pane) gridPane.getChildren().get(i);
            ImageView imgView = (ImageView) pane.getChildren().get(0);
            imgView.setImage(new Image(App.class.getResourceAsStream("images/c.png")));
            imgView.fitHeightProperty().bind(pane.heightProperty());
            imgView.fitWidthProperty().bind(pane.widthProperty());
        }

        System.out.println("Loaded grid pane: " + gridPane);
        return gridPane;
    }

    @Override
    public void start(Stage stage) throws Exception {
        logger.info("Starting application...");
        this.stage = stage;
        MenuBar menuBar = this.loadMenuBar();
        root.getChildren().add(menuBar);
        Pane pane = this.loadStartPane();
        root.getChildren().add(pane);
        stage.setTitle("WORDAGAM");
        Scene scene = new Scene(root, 540, 460);
        stage.setScene(scene);
        stage.show();
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
