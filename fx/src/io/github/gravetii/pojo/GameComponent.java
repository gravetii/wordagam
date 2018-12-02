package io.github.gravetii.pojo;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class GameComponent {

  private GridPane gamePane;
  private BorderPane playPane;
  private BorderPane resultPane;
  private ProgressBar progressBar;

  public GameComponent(GridPane gamePane, BorderPane playPane, BorderPane resultPane, ProgressBar progressBar) {
    this.gamePane = gamePane;
    this.playPane = playPane;
    this.resultPane = resultPane;
    this.progressBar = progressBar;
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

  public ProgressBar getProgressBar() {
    return progressBar;
  }
}
