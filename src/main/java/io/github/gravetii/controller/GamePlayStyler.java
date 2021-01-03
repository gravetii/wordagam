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
import java.util.stream.Collectors;

public class GamePlayStyler {

  private final GridPane gamePane;
  private final Collection<ImageView> imgViews;
  private final LinkedList<ImageView> seq;
  private final PauseTransition pauser;

  private Timeline timeline;
  private int gridRotationCount = 0;

  public GamePlayStyler(GridPane gamePane, Collection<ImageView> imgViews) {
    this.gamePane = gamePane;
    this.imgViews = imgViews;
    this.seq = new LinkedList<>();
    this.timeline = new Timeline();
    this.pauser = new PauseTransition(Duration.millis(200));
    this.pauser.setOnFinished((e) -> this.invalidate());
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

  public void rotate() {
    this.rotateGamePane();
    this.rotateGrid();
  }

  public void rotateOnEnd() {
    while (this.gridRotationCount != 0) this.rotateGrid();
  }

  private void applyRotateTransition() {
    ParallelTransition transition = new ParallelTransition();
    List<RotateTransition> children =
        this.seq.stream()
            .map(
                x -> {
                  RotateTransition rotate = new RotateTransition(Duration.millis(65), x);
                  rotate.setByAngle(360);
                  rotate.setCycleCount(4);
                  return rotate;
                })
            .collect(Collectors.toList());
    transition.getChildren().addAll(children);
    transition.play();
  }

  private void applyFadeTransition() {
    ParallelTransition transition = new ParallelTransition();
    List<FadeTransition> children =
        this.seq.stream()
            .map(
                x -> {
                  FadeTransition fade = new FadeTransition(Duration.millis(50), x);
                  fade.setFromValue(1.0);
                  fade.setToValue(0.3);
                  fade.setCycleCount(6);
                  fade.setAutoReverse(true);
                  return fade;
                })
            .collect(Collectors.toList());
    transition.getChildren().addAll(children);
    transition.play();
  }

  private void applyScaleTransition() {
    ParallelTransition transition = new ParallelTransition();
    List<ScaleTransition> children =
        this.seq.stream()
            .map(
                x -> {
                  ScaleTransition scale = new ScaleTransition(Duration.millis(50), x);
                  scale.setByX(0.2);
                  scale.setByY(0.2);
                  scale.setCycleCount(6);
                  scale.setAutoReverse(true);
                  return scale;
                })
            .collect(Collectors.toList());
    transition.getChildren().addAll(children);
    transition.play();
  }

  public void applyEndTransition() {
    ParallelTransition transition = new ParallelTransition();
    List<ParallelTransition> children =
        this.imgViews.stream()
            .map(
                x -> {
                  ScaleTransition scale = new ScaleTransition(Duration.millis(100));
                  scale.setByX(0.5);
                  scale.setByY(0.5);
                  scale.setCycleCount(4);
                  scale.setAutoReverse(true);
                  TranslateTransition translate = new TranslateTransition(Duration.millis(100));
                  translate.setByX(0.5);
                  translate.setByY(0.5);
                  translate.setCycleCount(4);
                  translate.setAutoReverse(true);
                  return new ParallelTransition(x, scale, translate);
                })
            .collect(Collectors.toList());
    transition.getChildren().addAll(children);
    transition.play();
  }

  private void truncate() {
    this.seq.pollLast();
  }

  private void reset() {
    this.seq.clear();
  }

  public void revisit(List<ImageView> imgViews) {
    this.timeline.stop();
    this.invalidate();
    Iterator<ImageView> itr = imgViews.iterator();
    KeyFrame keyframe = new KeyFrame(Duration.millis(300), (e) -> this.forValidClick(itr.next()));
    this.timeline = new Timeline(keyframe);
    this.timeline.setOnFinished((e) -> this.pauser.play());
    this.timeline.setCycleCount(imgViews.size());
    this.timeline.play();
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

  private void rearrange() {
    Pane[][] panes = this.fetchPanes();
    for (int x=0;x<2;x++) {
      for (int y=x;y<3-x;y++) {
        Pane temp = panes[y][3-x];
        panes[y][3-x] = panes[x][y];
        panes[x][y] = panes[3-y][x];
        panes[3-y][x] = panes[3-x][3-y];
        panes[3-x][3-y] = temp;
      }
    }

    for (int i=0;i<4;++i) {
      for (int j=0;j<4;++j) {
        Pane pane = panes[i][j];
        GridPane.setRowIndex(pane, i);
        GridPane.setColumnIndex(pane, j);
      }
    }
  }

  private void rotateGrid() {
    this.rearrange();
    if (++this.gridRotationCount % 4 == 0) this.gridRotationCount = 0;
  }

  private void rotateGamePane() {
    SequentialTransition sequencer = new SequentialTransition();
    RotateTransition gridTransition = new RotateTransition(Duration.millis(200), this.gamePane);
    gridTransition.setByAngle(360);
    gridTransition.setCycleCount(1);
    sequencer.getChildren().add(gridTransition);
    imgViews.forEach(
        imgView -> {
          RotateTransition imgViewTransition = new RotateTransition(Duration.millis(20), imgView);
          imgViewTransition.setByAngle(90);
          imgViewTransition.setCycleCount(2);
          imgViewTransition.setAutoReverse(true);
          sequencer.getChildren().add(imgViewTransition);
        });

    sequencer.play();
    sequencer.setOnFinished((e) -> sequencer.getChildren().clear());
  }
}
