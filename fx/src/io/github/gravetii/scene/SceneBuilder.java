package io.github.gravetii.scene;

import io.github.gravetii.App;
import io.github.gravetii.controller.*;
import io.github.gravetii.game.Game;
import io.github.gravetii.pojo.GameComponent;
import io.github.gravetii.themes.Theme;
import io.github.gravetii.themes.ThemeService;
import io.github.gravetii.util.GridUnit;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class SceneBuilder {

  private Stage stage;
  private BorderPane root;
  private ThemeService themes;

  SceneBuilder(Stage stage, BorderPane root) {
    this.stage = stage;
    this.root = root;
    this.themes = new ThemeService();
  }

  private static Image getSkin() throws Exception {
    String fPath = App.class.getResource("skins").getFile();
    Stream<Path> files = Files.list(Paths.get(fPath));
    int count = Math.toIntExact(files.count());
    int r = 1 + ThreadLocalRandom.current().nextInt(count);
    return new Image(App.class.getResourceAsStream("skins/" + r + ".jpg"), 0, 0, false, false);
  }

  private Node loadFxComponent(String fxml, FxController controller) throws Exception {
    FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
    loader.setController(controller);
    return loader.load();
  }

  public MenuBar loadMenuBar() throws Exception {
    MenuBar menuBar =
        (MenuBar) loadFxComponent("fxml/menuBar.fxml", new MenuBarController(this.stage));
    menuBar.prefWidthProperty().bind(this.root.widthProperty());
    return menuBar;
  }

  public Pane loadStartPane() throws Exception {
    Pane pane = (Pane) loadFxComponent("fxml/start.fxml", new StartController(this.stage));
    pane.prefHeightProperty().bind(root.heightProperty());
    pane.prefWidthProperty().bind(root.widthProperty());
    ImageView imgView = (ImageView) pane.getChildren().get(0);
    imgView.setImage(SceneBuilder.getSkin());
    imgView.fitHeightProperty().bind(pane.heightProperty());
    imgView.fitWidthProperty().bind(pane.widthProperty());
    return pane;
  }

  private SplitPane loadSplitPane(FxController controller) throws Exception {
    return (SplitPane) loadFxComponent("fxml/grid.fxml", controller);
  }

  private GridPane loadGamePane(Game game, FxController controller) throws Exception {
    GridPane gridPane = (GridPane) loadFxComponent("fxml/game.fxml", controller);
    BackgroundSize size = new BackgroundSize(100, 100, true, true, true, true);
    BackgroundImage background =
        new BackgroundImage(
            this.themes.current().getImage(),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            size);

    gridPane.setBackground(new Background(background));

    for (int i = 0, c = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        GridUnit unit = game.getGrid()[i][j];
        Pane pane = (Pane) gridPane.getChildren().get(c++);
        ImageView imgView = (ImageView) pane.getChildren().get(0);
        imgView.setImage(unit.getImage());
        imgView.fitHeightProperty().bind(pane.heightProperty());
        imgView.fitWidthProperty().bind(pane.widthProperty());
      }
    }

    return gridPane;
  }

  public GameComponent loadGameComponent(Game game) throws Exception {
    GameController gameController = new GameController(this.stage, game);
    GridController gridController = new GridController(gameController);
    GridPane gridPane = this.loadGamePane(game, gameController);
    SplitPane splitPane = this.loadSplitPane(gridController);
    return new GameComponent(gridPane, splitPane);
  }

  public GridPane loadEditThemePane() throws Exception {
    GridPane gridPane =
        (GridPane) loadFxComponent("fxml/editTheme.fxml", new ListThemesController(this.stage));
    List<Theme> allThemes = this.themes.getAll();
    for (int c = 0; c < allThemes.size(); ++c) {
      Theme theme = allThemes.get(c);
      BorderPane pane = (BorderPane) gridPane.getChildren().get(c);
      ImageView imgView = (ImageView) pane.getCenter();
      imgView.setImage(theme.getImage());
      Label label = new Label(theme.getShowableName());
      BorderPane.setAlignment(label, Pos.CENTER);
      pane.setBottom(label);
    }

    return gridPane;
  }

  public Scene build() {
    return new Scene(this.root);
  }
}
