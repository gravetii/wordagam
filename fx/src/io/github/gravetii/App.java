package io.github.gravetii;

import io.github.gravetii.game.Game;
import io.github.gravetii.scheduler.TaskScheduler;
import io.github.gravetii.service.WordService;
import io.github.gravetii.util.GridUnit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class App extends Application {

    private Stage stage;

    @Override
    public void init() {
        new WordService();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> TaskScheduler.get().close()));
    }

    private Image getStartingImage() {
        int r = 1 + new Random().nextInt(2);
        return new Image(App.class.getResourceAsStream("skins/" + r + ".jpg"));
    }

    private void start(FXMLLoader loader) throws IOException {
        AnchorPane root = loader.load();
        ImageView imgView = new ImageView();
        Image img = getStartingImage();
        imgView.setImage(img);
        BorderPane pane = (BorderPane) root.getChildren().get(1);
        pane.getChildren().add(imgView);
        imgView.fitWidthProperty().bind(pane.widthProperty());
        imgView.fitHeightProperty().bind(pane.heightProperty());
        Scene scene = new Scene(root, 540, 420);
        stage.setScene(scene);
        stage.setTitle("WORDAGAM!");
        stage.sizeToScene();
        stage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(App.class.getResource("main.fxml"));
        Controller controller = new Controller(this);
        loader.setController(controller);
        start(loader);
    }

    private void displayGame(FXMLLoader loader, Game game) {
        GridUnit[][] grid = game.getGrid();
        AnchorPane root = loader.getRoot();
        GridPane gridPane = (GridPane) root.getChildren().get(1);
        int c = 0;
        for (int i=0;i<4;++i) {
            for (int j=0;j<4;++j) {
                GridUnit unit = grid[j][i];
                BorderPane pane = (BorderPane) gridPane.getChildren().get(c++);
                String path = "images/" + unit.getLetter() + ".png";
                Image img = new Image(App.class.getResourceAsStream(path));
                ImageView imgView = new ImageView(img);
                imgView.fitWidthProperty().bind(pane.widthProperty());
                imgView.fitHeightProperty().bind(pane.heightProperty());
                pane.getChildren().add(imgView);
            }
        }
    }

    void showGame(Game game) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("game.fxml"));
        Controller controller = new Controller(this);
        loader.setController(controller);
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 540, 420);
        displayGame(loader, game);
        stage.setScene(scene);
        stage.setTitle("New game");
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
