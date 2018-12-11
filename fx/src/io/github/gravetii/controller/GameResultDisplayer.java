package io.github.gravetii.controller;

import io.github.gravetii.pojo.WordResult;
import javafx.scene.control.TableView;

import java.util.HashSet;
import java.util.Set;

public class GameResultDisplayer {

  private TableView<TableResult> table;
  private Set<String> displayedWords;
  private int counter;

  public GameResultDisplayer(TableView<TableResult> table) {
    this.table = table;
    this.displayedWords = new HashSet<>();
    this.counter = 0;
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
    private int id;
    private String word;
    private int score;
    private boolean byUser;

    private TableResult(int id, String word, int score, boolean byUser) {
      this.id = id;
      this.word = word;
      this.score = score;
      this.byUser = byUser;
    }

    public int getId() {
      return id;
    }

    public String getWord() {
      return word;
    }

    public int getScore() {
      return score;
    }

    public boolean isByUser() {
      return byUser;
    }
  }
}
