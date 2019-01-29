package io.github.gravetii.controller;

import io.github.gravetii.pojo.WordResult;
import javafx.scene.control.TableView;

import java.util.HashSet;
import java.util.Set;

public class GameResultDisplayer {

  private int counter = 0;
  private TableView<TableResult> table;
  private Set<String> displayedWords;

  public GameResultDisplayer(TableView<TableResult> table) {
    this.table = table;
    this.displayedWords = new HashSet<>();
  }

  private boolean notAlreadyDisplayed(String word) {
    return !displayedWords.contains(word);
  }

  private void markDisplayed(String word) {
    this.displayedWords.add(word);
  }

  public void showGameWord(WordResult result) {
    this.show(result, false);
  }

  public void showUserWord(WordResult result) {
    this.show(result, true);
  }

  private void show(WordResult result, boolean byUser) {
    String word = result.getWord();
    if (this.notAlreadyDisplayed(word)) {
      TableResult tableResult = new TableResult(++this.counter, word, result.getScore(), byUser);
      this.table.getItems().add(tableResult);
      this.markDisplayed(word);
    }
  }

  public static class TableResult {
    private Integer id;
    private String word;
    private Integer score;
    private boolean byUser;

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
