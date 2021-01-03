package io.github.gravetii.model;

import io.github.gravetii.util.Alphabet;
import javafx.scene.image.Image;

import java.util.Objects;

public class GridUnit {
  private final Alphabet alphabet;
  private final GridPoint gridPoint;
  private final String imgPath;
  private Image img;

  public GridUnit(Alphabet alphabet, GridPoint gridPoint) {
    this.alphabet = alphabet;
    this.gridPoint = gridPoint;
    this.imgPath = "/images/" + this.alphabet.get() + ".png";
  }

  public String getLetter() {
    return alphabet.get();
  }

  public int getScore() {
    return alphabet.getScore();
  }

  public int getWeight() {
    return alphabet.getWeight();
  }

  public GridPoint getPoint() {
    return gridPoint;
  }

  public Image getImage() {
    if (this.img == null) {
      String path = getClass().getResource(this.imgPath).toExternalForm();
      this.img = new Image(path);
    }

    return this.img;
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof GridUnit) {
      GridUnit unit = (GridUnit) object;
      return this.alphabet == unit.alphabet && this.gridPoint.equals(unit.gridPoint);
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(alphabet, gridPoint);
  }
}
