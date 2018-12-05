package io.github.gravetii.controller;

import javafx.scene.control.TableView;

import java.util.HashSet;
import java.util.Set;

public class TableResultDisplayer {

  private TableView<TableResult> table;
  private Set<String> displayedWords;
  private int counter;

  public TableResultDisplayer(TableView<TableResult> table) {
    this.table = table;
    this.displayedWords = new HashSet<>();
    this.counter = 0;
  }

  public void show(String word, int points) {
    if (!displayedWords.contains(word)) {
      this.table.getItems().add(new TableResult(++this.counter, word, points));
      this.displayedWords.add(word);
    }
  }

  public static class TableResult {
    private int id;
    private String word;
    private int score;

    private TableResult(int id, String word, int score) {
      this.id = id;
      this.word = word;
      this.score = score;
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
  }
}
