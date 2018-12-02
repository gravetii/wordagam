package io.github.gravetii.pojo;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class GameComponent {

  private GridPane gamePane;
  private BorderPane playPane;
  private BorderPane resultPane;

  public GameComponent(GridPane gamePane, BorderPane playPane, BorderPane resultPane) {
    this.gamePane = gamePane;
    this.playPane = playPane;
    this.resultPane = resultPane;
  }

  public GridPane getGamePane() {
    return gamePane;
  }

  public BorderPane getPlayVBox() {
    return playPane;
  }

  public BorderPane getResultVBox() {
    return resultPane;
  }
}
