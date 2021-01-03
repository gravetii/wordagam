package io.github.gravetii.game;

public enum GameQuality {
  HIGH(500),
  MEDIUM(450),
  LOW(0);

  private final int minCount;

  GameQuality(int minCount) {
    this.minCount = minCount;
  }

  public int getMinCount() {
    return minCount;
  }
}
