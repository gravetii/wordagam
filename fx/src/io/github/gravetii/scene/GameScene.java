package io.github.gravetii.scene;

import io.github.gravetii.game.Game;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameScene extends FxScene {

  private Game game;

  public GameScene(Stage stage, Game game) {
    super(stage);
    this.game = game;
  }

  @Override
  protected Scene build() throws Exception {
    MenuBar menuBar = this.builder.loadMenuBar();
    this.builder.root.setTop(menuBar);
    GridPane gridPane = this.builder.loadGamePane(this.game);
    this.builder.root.setCenter(gridPane);
    //this.builder.addNode(gridPane);
    SplitPane splitPane = this.builder.loadSplitPane();
    this.builder.root.setRight(splitPane);
    BorderPane.setAlignment(gridPane, Pos.CENTER);
    System.out.println(BorderPane.getAlignment(gridPane));
    return this.builder.build();
  }
}
