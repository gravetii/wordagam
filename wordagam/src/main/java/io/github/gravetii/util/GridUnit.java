package io.github.gravetii.util;

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

  public GridPoint getGridPoint() {
    return gridPoint;
  }
}
