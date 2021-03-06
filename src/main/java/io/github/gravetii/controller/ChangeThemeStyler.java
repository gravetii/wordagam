package io.github.gravetii.controller;

import javafx.scene.image.ImageView;

public class ChangeThemeStyler {
  private ImageView current;

  public void applySelectStyle(ImageView imgView) {
    if (this.current != null) this.remove();
    this.current = imgView;
    this.apply();
  }

  private void apply() {
    this.current.getParent().getStyleClass().add("theme-img-view-click");
  }

  private void remove() {
    this.current.getParent().getStyleClass().remove("theme-img-view-click");
  }
}
