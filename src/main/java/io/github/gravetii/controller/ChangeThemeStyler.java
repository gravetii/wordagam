package io.github.gravetii.controller;

import javafx.scene.image.ImageView;

public class ChangeThemeStyler {
  private ImageView current;

  public void applySelectStyle(ImageView imgView) {
    if (this.current != null) {
      this.revert();
    }

    this.current = imgView;
    this.apply();
  }

  private void apply() {
    this.current.getParent().getStyleClass().add("theme-img-view-click");
  }

  private void revert() {
    this.current.getParent().getStyleClass().remove("theme-img-view-click");
  }
}
