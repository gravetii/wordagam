package io.github.gravetii.model;

import java.util.List;

public class WordResult {
  private final String word;
  private final int score;
  private final List<GridPoint> seq;

  public WordResult(String word, int score, List<GridPoint> seq) {
    this.word = word;
    this.score = score;
    this.seq = seq;
  }

  public String getWord() {
    return this.word;
  }

  public int getScore() {
    return this.score;
  }

  public List<GridPoint> getSeq() {
    return this.seq;
  }

  @Override
  public String toString() {
    return '{' + "word='" + word + '\'' + ", score=" + score + '}';
  }
}
