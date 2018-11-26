package io.github.gravetii.controller;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class EditThemeStyler {

  private ImageView current;

  public void applySelectStyle(ImageView imgView) {
    if (this.current != null) {
      this.revert(this.current);
    }

    this.apply(imgView);
    this.current = imgView;
  }

  private void apply(ImageView imgView) {
    imgView.getParent().getStyleClass().add("theme-img-view-click");
  }

  private void revert(ImageView imgView) {
    imgView.getParent().getStyleClass().remove("theme-img-view-click");
  }
}
