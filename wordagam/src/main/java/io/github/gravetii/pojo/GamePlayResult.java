package io.github.gravetii.pojo;

import io.github.gravetii.util.GridUnit;

import java.util.List;

public class GamePlayResult {

  private String word;
  private int score;
  private List<GridUnit> seq;

  public GamePlayResult(String word, int score, List<GridUnit> seq) {
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

  public List<GridUnit> getSeq() {
    return this.seq;
  }

  @Override
  public String toString() {
    return "GamePlayResult{" + "word='" + word + '\'' + ", score=" + score + ", seq=" + seq + '}';
  }
}
