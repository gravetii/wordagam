package io.github.gravetii.pojo;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameComponent {

  private GridPane gamePane;
  private VBox playVBox;
  private VBox resultVBox;

  public GameComponent(GridPane gamePane, VBox playVBox, VBox resultVBox) {
    this.gamePane = gamePane;
    this.playVBox = playVBox;
    this.resultVBox = resultVBox;
  }

  public GridPane getGamePane() {
    return gamePane;
  }

  public VBox getPlayVBox() {
    return playVBox;
  }

  public VBox getResultVBox() {
    return resultVBox;
  }
}
