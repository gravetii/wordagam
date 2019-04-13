package io.github.gravetii.pojo;

import io.github.gravetii.util.GridPoint;

import java.util.List;

public class WordResult {

  private String word;
  private int score;
  private List<GridPoint> seq;

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
