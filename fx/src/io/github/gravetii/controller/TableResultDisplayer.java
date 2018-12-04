package io.github.gravetii.controller;

import javafx.scene.control.TableView;

public class TableResultDisplayer {

  private TableView<TableResult> table;
  private int counter;

  public TableResultDisplayer(TableView<TableResult> table) {
    this.table = table;
    this.counter = 0;
  }

  public void show(String word, int points) {
    this.table.getItems().add(new TableResult(++this.counter, word, points));
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
