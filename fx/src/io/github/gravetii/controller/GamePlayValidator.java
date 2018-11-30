package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.util.GridUnit;

import java.util.LinkedList;
import java.util.List;

public class GamePlayValidator {

  private Game game;
  private LinkedList<GridUnit> seq;
  private StringBuilder builder;

  public GamePlayValidator(Game game) {
    this.game = game;
    this.seq = new LinkedList<>();
    this.builder = new StringBuilder();
  }

  public void reset() {
    this.builder = new StringBuilder();
    this.seq.clear();
  }

  private void append(GridUnit unit) {
    this.builder.append(unit.getLetter());
    this.seq.add(unit);
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
    if (unit == this.seq.getLast()) {
      this.truncate();
      return ValidationResult.LAST_INVALID;
    } else {
      if (this.seq.contains(unit) || !unit.isNeighbor(this.seq.getLast())) {
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
    return word.isEmpty() || !this.game.exists(word) ? null : word;
  }

  public List<GridUnit> getSeq() {
    return new LinkedList<>(this.seq);
  }
}
