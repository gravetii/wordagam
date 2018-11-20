package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.util.GridUnit;
import javafx.util.Pair;

import java.util.HashMap;
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
    this.validatedEntries = new HashMap<>();
  }

  public void invalidate() {
    this.seq.clear();
    this.builder = new StringBuilder();
  }

  private void append(GridUnit unit) {
    this.seq.add(unit);
    this.builder.append(unit.getLetter());
  }

  private boolean validateSubsequentClick(GridUnit unit) {
    if (this.seq.contains(unit) || !unit.isNeighbor(this.seq.getLast())) {
      this.invalidate();
      return false;
    } else {
      this.append(unit);
      return true;
    }
  }

  public boolean validateClick(GridUnit unit) {
    if (this.seq.isEmpty()) {
      this.append(unit);
      return true;
    } else {
      return this.validateSubsequentClick(unit);
    }
  }

  public boolean validateWord() {
    String word = this.builder.toString();
    return this.game.exists(word);
  }

  public Pair<String, Integer> get() {
    String word = this.builder.toString();
    if (this.validatedEntries.containsKey(word)) {
      return null;
    }
    else {
      int points = this.game.getWordPoints(word);
      this.validatedEntries.put(word, points);
      return new Pair<>(word, points);
    }
  }
}
