package io.github.gravetii.controller;

import io.github.gravetii.game.GameResult;
import io.github.gravetii.util.GridPoint;
import io.github.gravetii.util.GridUnit;

import java.util.LinkedList;
import java.util.List;

public class GamePlayValidator {

  private GameResult result;
  private LinkedList<GridPoint> seq;
  private StringBuilder builder;

  public GamePlayValidator(GameResult result) {
    this.result = result;
    this.seq = new LinkedList<>();
    this.builder = new StringBuilder();
  }

  public void reset() {
    this.builder = new StringBuilder();
    this.seq.clear();
  }

  private void append(GridUnit unit) {
    this.builder.append(unit.getLetter());
    this.seq.add(unit.getPoint());
  }

  private void truncate() {
    this.builder.deleteCharAt(this.builder.length() - 1);
    this.seq.pollLast();
  }

  private ValidationResult validateFirstClick(GridUnit unit) {
    this.append(unit);
    return ValidationResult.ALL_VALID;
  }

  private ValidationResult validateSubsequentClick(GridUnit unit) {
    GridPoint point = unit.getPoint();
    if (point == this.seq.getLast()) {
      this.truncate();
      return ValidationResult.LAST_INVALID;
    } else {
      if (this.seq.contains(point) || !point.isNeighbor(this.seq.getLast())) {
        this.reset();
        return ValidationResult.ALL_INVALID;
      } else {
        this.append(unit);
        return ValidationResult.ALL_VALID;
      }
    }
  }

  public ValidationResult validate(GridUnit unit) {
    if (this.seq.isEmpty()) {
      return this.validateFirstClick(unit);
    } else {
      return this.validateSubsequentClick(unit);
    }
  }

  public String validate() {
    String word = this.builder.toString();
    return word.isEmpty() || !this.result.exists(word) ? null : word;
  }

  public List<GridPoint> getSeq() {
    return new LinkedList<>(this.seq);
  }
}
