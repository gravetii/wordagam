package io.github.gravetii.pojo;

import io.github.gravetii.util.GridUnit;

import java.util.List;

public class GamePlayResult {

  private WordPoint wordPoint;

  private List<GridUnit> seq;

  public GamePlayResult(WordPoint wordPoint, List<GridUnit> seq) {
    this.wordPoint = wordPoint;
    this.seq = seq;
  }

  public WordPoint getWordPoint() {
    return wordPoint;
  }

  public List<GridUnit> getSeq() {
    return seq;
  }

  @Override
  public String toString() {
    return "GamePlayResult{" + "wordPoint=" + wordPoint + ", seq=" + seq + '}';
  }
}
