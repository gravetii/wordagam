package io.github.gravetii;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;

public class App extends Application {

    private Stage stage;

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(App.class.getResource("main.fxml"));
        loader.setController(new Controller(this));
        AnchorPane pane = loader.load();
        stage.setTitle("WORDAGAM");
        int r = new Random().nextInt(2) + 1;
//        Image img = new Image(App.class.getResourceAsStream("skins/" + r + ".jpg"));
//        ImageView imgView = new ImageView(img);
//        imgView.fitWidthProperty().bind(pane.widthProperty());
//        imgView.fitHeightProperty().bind(pane.heightProperty());
//        pane.getChildren().add(imgView);

        ImageView imgView = (ImageView) pane.getChildren().get(1);
        Image img = new Image(App.class.getResourceAsStream("skins/" + r + ".jpg"));
        imgView.setImage(img);
        imgView.fitWidthProperty().bind(pane.widthProperty());
        imgView.fitHeightProperty().bind(pane.heightProperty());
        imgView.setPreserveRatio(true);

        Scene scene = new Scene(pane, 500, 450);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
