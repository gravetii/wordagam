package io.github.gravetii.pojo;

public class WordPoint {

  private int index;

  private String word;

  private int points;

  public WordPoint(int index, String word, int points) {
    this.index = index;
    this.word = word;
    this.points = points;
  }

  public int getIndex() {
    return index;
  }

  public String getWord() {
    return word;
  }

  public int getPoints() {
    return points;
  }
}
