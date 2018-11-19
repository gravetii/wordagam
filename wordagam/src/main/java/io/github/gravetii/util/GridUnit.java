package io.github.gravetii.util;

import java.util.Objects;

public class GridUnit {

  private Alphabet alphabet;
  private GridPoint gridPoint;

  public GridUnit(Alphabet alphabet, GridPoint gridPoint) {
    this.alphabet = alphabet;
    this.gridPoint = gridPoint;
  }

  public Alphabet getAlphabet() {
    return alphabet;
  }

  public String getLetter() {
    return alphabet.get();
  }

  public int getPoints() {
    return alphabet.getPoints();
  }

  public int getWeight() {
    return alphabet.getWeight();
  }

  private GridPoint getGridPoint() {
    return gridPoint;
  }

  public boolean isNeighbor(GridUnit unit) {
    return this.getGridPoint().isNeighbor(unit.getGridPoint());
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
