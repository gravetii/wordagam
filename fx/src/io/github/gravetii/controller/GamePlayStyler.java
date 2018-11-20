package io.github.gravetii.controller;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.LinkedList;

public class GamePlayStyler {

  private LinkedList<ImageView> seq;

  public GamePlayStyler() {
    this.seq = new LinkedList<>();
  }

  public void forInvalidClick() {
    this.revert();
    this.reset();
  }

  public void forCorrectWord() {
    this.revert();
    this.rotate();
    this.reset();
  }

  public void forIncorrectWord() {
    this.revert();
    this.fade();
    this.reset();
  }

  public void forRepeatedWord() {
    this.revert();
    this.scale();
    this.reset();
  }

  public void apply(ImageView imgView) {
    imgView.getStyleClass().add("custom-img-view-click");
    this.seq.add(imgView);
  }

  private void revert() {
    this.seq.forEach(imgView -> {
      imgView.getStyleClass().remove("custom-img-view-click");
    });
  }

  private void rotate() {
    this.seq.forEach(imgView -> {
      RotateTransition transition = new RotateTransition(Duration.millis(65), imgView);
      transition.setByAngle(360);
      transition.setCycleCount(4);
      transition.play();
    });
  }

  private void fade() {
    this.seq.forEach(imgView -> {
      FadeTransition transition = new FadeTransition(Duration.millis(50), imgView);
      transition.setFromValue(1.0);
      transition.setToValue(0.3);
      transition.setCycleCount(6);
      transition.setAutoReverse(true);
      transition.play();
    });
  }

  private void scale() {
    this.seq.forEach(imgView -> {
      ScaleTransition transition = new ScaleTransition(Duration.millis(50), imgView);
      transition.setFromX(1.0);
      transition.setToX(1.2);
      transition.setFromY(1.0);
      transition.setToY(1.2);
      transition.setCycleCount(6);
      transition.setAutoReverse(true);
      transition.play();
    });

  }

  private void reset() {
    this.seq.clear();
  }
}
