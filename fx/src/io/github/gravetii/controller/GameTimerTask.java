package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import javafx.concurrent.Task;
import javafx.scene.layout.BorderPane;

public class GameTimerTask extends Task<Void> {

  private BorderPane root;
  private double time;

  public GameTimerTask(BorderPane root, double time) {
    this.root = root;
    this.time = time;
  }

  @Override
  protected Void call() {
    if (this.time == 0) {
      this.withoutLimit();
    } else {
      this.withLimit();
    }

    return null;
  }

  private void withLimit() {
    double c = this.time;
    try {
      while (c > 0) {
        Thread.sleep(50);
        this.updateProgress(c, this.time);
        c -= 0.05;
      }

      this.updateProgress(Double.MIN_VALUE, this.time);
    } catch (InterruptedException e) {
      // do nothing
    }

    this.root.getChildren().forEach(node -> node.fireEvent(new Game.EndEvent()));
  }

  private void withoutLimit() {
    this.updateProgress(1, 1);
  }
}
