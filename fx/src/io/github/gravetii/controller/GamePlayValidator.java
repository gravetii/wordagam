package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.pojo.WordPoint;
import io.github.gravetii.util.GridUnit;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class GamePlayValidator {

  private Game game;
  private LinkedList<GridUnit> seq;
  private StringBuilder builder;
  private Map<String, Integer> validatedEntries;

  public GamePlayValidator(Game game) {
    this.game = game;
    this.seq = new LinkedList<>();
    this.builder = new StringBuilder();
    this.validatedEntries = new LinkedHashMap<>();
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
    this.builder.deleteCharAt(this.seq.size() - 1);
    this.seq.pollLast();
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

  public ValidationResult validateClick(GridUnit unit) {
    if (this.seq.isEmpty()) {
      this.append(unit);
      return ValidationResult.ALL_VALID;
    } else {
      return this.validateSubsequentClick(unit);
    }
  }

  public boolean validateWord() {
    String word = this.builder.toString();
    if (word.isEmpty()) {
      return false;
    } else {
      return this.game.exists(word);
    }
  }

  public WordPoint get() {
    String word = this.builder.toString();
    if (this.validatedEntries.containsKey(word)) {
      return null;
    } else {
      int points = this.game.getWordPoints(word);
      this.validatedEntries.put(word, points);
      return new WordPoint(this.validatedEntries.size(), word.toUpperCase(), points);
    }
  }
}
