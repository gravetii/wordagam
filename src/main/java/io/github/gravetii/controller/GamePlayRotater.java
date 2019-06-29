package io.github.gravetii.controller;

import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Collection;

public class GamePlayRotater {
  private GridPane gamePane;
  private Pane[][] panes;
  private Collection<ImageView> imgViews;
  private int count = 0;
  private SequentialTransition sequencer;

  public GamePlayRotater(GridPane gamePane, Collection<ImageView> imgViews) {
    this.gamePane = gamePane;
    this.panes = fetchPanes();
    this.imgViews = imgViews;
    this.sequencer = new SequentialTransition();
  }

  private Pane[][] fetchPanes() {
    int c = 0;
    Pane[][] panes = new Pane[4][4];
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        panes[i][j] = (Pane) this.gamePane.getChildren().get(c++);
      }
    }

    return panes;
  }

  public void apply() {
    this.rotateGamePane();
    this.rotateGrid();
  }

  private void rearrange() {
    for (int x = 0; x < 2; x++) {
      for (int y = x; y < 3-x; y++) {
        Pane temp = this.panes[y][3-x];
        this.panes[y][3-x] = this.panes[x][y];
        this.panes[x][y] = this.panes[3-y][x];
        this.panes[3-y][x] = this.panes[3-x][3-y];
        this.panes[3-x][3-y] = temp;
      }
    }

    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        Pane pane = this.panes[i][j];
        GridPane.setRowIndex(pane, i);
        GridPane.setColumnIndex(pane, j);
      }
    }
  }

  private void rotateGrid() {
    this.rearrange();
    if (++this.count % 4 == 0) {
      this.count = 0;
    }
  }

  private void rotateGamePane() {
    RotateTransition gridTransition = new RotateTransition(Duration.millis(75), this.gamePane);
    gridTransition.setByAngle(360);
    gridTransition.setCycleCount(1);
    this.sequencer.getChildren().add(gridTransition);
    imgViews.forEach(
        imgView -> {
          RotateTransition imgViewTransition = new RotateTransition(Duration.millis(20), imgView);
          imgViewTransition.setByAngle(90);
          imgViewTransition.setCycleCount(2);
          imgViewTransition.setAutoReverse(true);
          this.sequencer.getChildren().add(imgViewTransition);
        });

    this.sequencer.play();
    this.sequencer.setOnFinished(
        event -> {
          this.sequencer.getChildren().clear();
        });
  }

  public void applyOnEnd() {
    while (this.count != 0) {
      this.rotateGrid();
    }
  }
}
