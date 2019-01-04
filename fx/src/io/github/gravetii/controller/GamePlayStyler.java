package io.github.gravetii.controller;

import javafx.animation.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GamePlayStyler {

  private GridPane gamePane;
  private Collection<ImageView> imgViews;

  private LinkedList<ImageView> seq;
  private PauseTransition revisitPauser;
  private SequentialTransition sequencer;

  public GamePlayStyler(GridPane gamePane, Collection<ImageView> imgViews) {
    this.gamePane = gamePane;
    this.imgViews = imgViews;
    this.seq = new LinkedList<>();
    this.revisitPauser = new PauseTransition(Duration.millis(200));
    this.revisitPauser.setOnFinished(
        (event) -> {
          this.invalidate();
        });
    this.sequencer = new SequentialTransition();
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
    TranslateTransition tt = new TranslateTransition(Duration.millis(80));
    tt.setByX(20);
    tt.setByY(20);
    tt.setCycleCount(4);
    tt.setAutoReverse(true);
    ScaleTransition st = new ScaleTransition(Duration.millis(80));
    st.setByX(0.2);
    st.setByY(0.2);
    st.setCycleCount(4);
    st.setAutoReverse(true);
    ParallelTransition transition = new ParallelTransition(imgView, tt, st);
    transition.play();
  }

  public void rotateGamePane() {
    imgViews.forEach(
        imgView -> {
          RotateTransition imgViewTransition = new RotateTransition(Duration.millis(20), imgView);
          imgViewTransition.setByAngle(180);
          imgViewTransition.setCycleCount(1);
          this.sequencer.getChildren().add(imgViewTransition);
        });

    RotateTransition gridTransition = new RotateTransition(Duration.millis(100), this.gamePane);
    gridTransition.setByAngle(180);
    gridTransition.setCycleCount(1);
    this.sequencer.getChildren().add(gridTransition);
    this.sequencer.play();
    this.sequencer.setOnFinished(
        event -> {
          this.sequencer.getChildren().clear();
        });
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
    this.invalidate();
    Iterator<ImageView> itr = imgViews.iterator();

    Timeline timeline =
        new Timeline(
            new KeyFrame(
                Duration.millis(200),
                (event) -> {
                  ImageView imgView = itr.next();
                  this.forValidClick(imgView);
                }));

    timeline.setOnFinished(
        (event) -> {
          this.revisitPauser.play();
        });

    timeline.setCycleCount(imgViews.size());
    timeline.play();
  }
}
