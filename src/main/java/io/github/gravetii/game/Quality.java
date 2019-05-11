package io.github.gravetii.game;

public enum Quality {
  HIGH(500),
  MEDIUM(450),
  LOW(0);

  private int minCount;

  Quality(int minCount) {
    this.minCount = minCount;
  }

  public int getMinCount() {
    return minCount;
  }
}
