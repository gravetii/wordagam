package io.github.gravetii.controller;

import io.github.gravetii.model.WordResult;
import javafx.scene.control.TableView;

import java.util.HashSet;
import java.util.Set;

public class GameResultDisplayer {
  private final TableView<TableResult> table;
  private final Set<String> displayedWords;

  private int counter = 0;

  public GameResultDisplayer(TableView<TableResult> table) {
    this.table = table;
    this.displayedWords = new HashSet<>();
  }

  public void showGameWord(WordResult result) {
    this.show(result, false);
  }

  public void showUserWord(WordResult result) {
    this.show(result, true);
  }

  public void setMarker() {
    this.table.scrollTo(this.counter);
  }

  private void show(WordResult result, boolean byUser) {
    String word = result.getWord();
    if (!this.displayedWords.contains(word)) {
      TableResult tableResult = new TableResult(++this.counter, word, result.getScore(), byUser);
      this.table.getItems().add(tableResult);
      this.displayedWords.add(word);
    }
  }

  public static class TableResult {
    private final Integer id;
    private final String word;
    private final Integer score;
    private final boolean byUser;

    private TableResult(Integer id, String word, Integer score, boolean byUser) {
      this.id = id;
      this.word = word;
      this.score = score;
      this.byUser = byUser;
    }

    public Integer getId() {
      return id;
    }

    public String getWord() {
      return word;
    }

    public Integer getScore() {
      return score;
    }

    public boolean isByUser() {
      return byUser;
    }

    public boolean isEmpty() {
      return this.word == null;
    }
  }
}
