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
  private final Collection<ImageView> imgViews;
  private final GamePlayRotater rotater;
  private final LinkedList<ImageView> seq;
  private final PauseTransition revisitPauser;

  private Timeline revisitTimeline;

  public GamePlayStyler(GridPane gamePane, Collection<ImageView> imgViews) {
    this.imgViews = imgViews;
    this.rotater = new GamePlayRotater(gamePane, imgViews);
    this.seq = new LinkedList<>();
    this.revisitTimeline = new Timeline();
    this.revisitPauser = new PauseTransition(Duration.millis(200));
    this.revisitPauser.setOnFinished((e) -> this.invalidate());
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
    this.rotater.apply();
  }

  public void rotateOnEnd() {
    this.rotater.applyOnEnd();
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
    TranslateTransition translate = new TranslateTransition(Duration.millis(100));
    translate.setByX(50);
    translate.setByY(50);
    translate.setCycleCount(4);
    translate.setAutoReverse(true);
    ScaleTransition scale = new ScaleTransition(Duration.millis(100));
    scale.setByX(0.4);
    scale.setByY(0.4);
    scale.setCycleCount(4);
    scale.setAutoReverse(true);
    ParallelTransition transition = new ParallelTransition(imgView, translate, scale);
    transition.play();
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
    KeyFrame keyframe = new KeyFrame(Duration.millis(300), (e) -> this.forValidClick(itr.next()));
    this.revisitTimeline = new Timeline(keyframe);
    this.revisitTimeline.setOnFinished((e) -> this.revisitPauser.play());
    this.revisitTimeline.setCycleCount(imgViews.size());
    this.revisitTimeline.play();
  }
}
