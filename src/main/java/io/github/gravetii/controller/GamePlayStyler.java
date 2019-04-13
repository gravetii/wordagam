package io.github.gravetii.controller;

import javafx.animation.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GamePlayStyler {
  private GridPane gamePane;
  private Collection<ImageView> imgViews;

  private LinkedList<ImageView> seq;
  private Timeline revisitTimeline;
  private PauseTransition revisitPauser;
  private SequentialTransition sequencer;
  private Pane[][] panes;
  private int gridRotCount = 0;

  public GamePlayStyler(GridPane gamePane, Collection<ImageView> imgViews) {
    this.gamePane = gamePane;
    this.imgViews = imgViews;
    this.panes = this.fetchPanes();
    this.seq = new LinkedList<>();
    this.revisitTimeline = new Timeline();
    this.revisitPauser = new PauseTransition(Duration.millis(200));
    this.revisitPauser.setOnFinished(
        (event) -> {
          this.invalidate();
        });
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

  public void invalidate() {
    this.revert();
    this.reset();
  }

  public void forCorrectWord() {
    this.revert();
    this.applyRotateTransition();
    this.reset();
  }

  public void forIncorrectWord() {
    this.revert();
    this.applyFadeTransition();
    this.reset();
  }

  public void forRepeatedWord() {
    this.revert();
    this.applyScaleTransition();
    this.reset();
  }

  public void forLastInvalidClick(ImageView imgView) {
    this.revert(imgView);
    this.truncate();
  }

  public void forValidClick(ImageView imgView) {
    imgView.getStyleClass().add("custom-img-view-click");
    this.seq.add(imgView);
  }

  private void revert(ImageView imgView) {
    imgView.getStyleClass().remove("custom-img-view-click");
  }

  private void revert() {
    this.seq.forEach(this::revert);
  }

  private void rotate(ImageView imgView) {
    RotateTransition transition = new RotateTransition(Duration.millis(65), imgView);
    transition.setByAngle(360);
    transition.setCycleCount(4);
    transition.play();
  }

  private void applyRotateTransition() {
    this.seq.forEach(this::rotate);
  }

  private void fade(ImageView imgView) {
    FadeTransition transition = new FadeTransition(Duration.millis(50), imgView);
    transition.setFromValue(1.0);
    transition.setToValue(0.3);
    transition.setCycleCount(6);
    transition.setAutoReverse(true);
    transition.play();
  }

  private void applyFadeTransition() {
    this.seq.forEach(this::fade);
  }

  private void scale(ImageView imgView) {
    ScaleTransition transition = new ScaleTransition(Duration.millis(50), imgView);
    transition.setByX(0.2);
    transition.setByY(0.2);
    transition.setCycleCount(6);
    transition.setAutoReverse(true);
    transition.play();
  }

  private void applyScaleTransition() {
    this.seq.forEach(this::scale);
  }

  private void endTransition(ImageView imgView) {
    TranslateTransition tt = new TranslateTransition(Duration.millis(100));
    tt.setByX(50);
    tt.setByY(50);
    tt.setCycleCount(4);
    tt.setAutoReverse(true);
    ScaleTransition st = new ScaleTransition(Duration.millis(100));
    st.setByX(0.4);
    st.setByY(0.4);
    st.setCycleCount(4);
    st.setAutoReverse(true);
    ParallelTransition transition = new ParallelTransition(imgView, tt, st);
    transition.play();
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

  public void rotateOnEnd() {
    while (this.gridRotCount != 0) {
      this.rotate();
    }
  }

  public void rotate() {
    this.rotateGamePane();
    this.rotateGrid();
    ++this.gridRotCount;
    if (this.gridRotCount % 4 == 0) {
      this.gridRotCount = 0;
    }
  }

  private void rotateGrid() {
    for (int x = 0; x < 2; x++) {
      for (int y = x; y < 3 - x; y++) {
        Pane temp = this.panes[y][3 - x];
        this.panes[y][3 - x] = this.panes[x][y];
        this.panes[x][y] = this.panes[3 - y][x];
        this.panes[3 - y][x] = this.panes[3 - x][3 - y];
        this.panes[3 - x][3 - y] = temp;
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

  public void applyEndTransition() {
    this.imgViews.forEach(this::endTransition);
  }

  private void truncate() {
    this.seq.pollLast();
  }

  private void reset() {
    this.seq.clear();
  }

  public void revisit(List<ImageView> imgViews) {
    this.revisitTimeline.stop();
    this.invalidate();
    Iterator<ImageView> itr = imgViews.iterator();
    this.revisitTimeline =
        new Timeline(
            new KeyFrame(
                Duration.millis(200),
                (event) -> {
                  ImageView imgView = itr.next();
                  this.forValidClick(imgView);
                }));

    this.revisitTimeline.setOnFinished(
        (event) -> {
          this.revisitPauser.play();
        });

    this.revisitTimeline.setCycleCount(imgViews.size());
    this.revisitTimeline.play();
  }
}
