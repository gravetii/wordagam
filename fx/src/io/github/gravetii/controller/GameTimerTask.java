package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import javafx.concurrent.Task;
import javafx.scene.layout.BorderPane;

public class GameTimerTask extends Task<Void> {

  private BorderPane root;
  private int gameTime;

  public GameTimerTask(BorderPane root, int gameTime) {
    this.root = root;
    this.gameTime = gameTime;
  }

  @Override
  protected Void call() {
    try {
      double c = this.gameTime;
      while (c > 0) {
        Thread.sleep(50);
        this.updateProgress(c, this.gameTime);
        c -= 0.05;
      }

      this.updateProgress(Double.MIN_VALUE, this.gameTime);
    } catch (InterruptedException e) {
      // do nothing
    }

    this.root.getChildren().forEach(node -> node.fireEvent(new Game.EndEvent()));
    return null;
  }
}
