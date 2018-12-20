package io.github.gravetii.controller;

import javafx.css.PseudoClass;
import javafx.scene.control.TableRow;

import static io.github.gravetii.controller.GameResultDisplayer.TableResult;

public class TableViewRenderer {

  private GameGridController ref;
  private PseudoClass gamePseudoClass = PseudoClass.getPseudoClass("game");
  private PseudoClass endPseudoClass = PseudoClass.getPseudoClass("end");

  public TableViewRenderer(GameGridController ref) {
    this.ref = ref;
  }

  public TableRow<TableResult> getStartFactory() {
    return this.getRowFactory(Integer.MAX_VALUE);
  }

  public TableRow<TableResult> getEndFactory() {
    return this.getRowFactory(this.ref.getAllUserWords().size());
  }

  private void onClickAction(TableRow<TableResult> row) {
    if (!row.isEmpty()) {
      TableResult result = row.getItem();
      String word = result.getWord();
      if (result.isByUser()) {
        this.ref.revisitUserWord(word);
      } else {
        this.ref.revisitGameWord(word);
      }
    }
  }

  private TableRow<TableResult> getRowFactory(int index) {
    TableRow<TableResult> row =
        new TableRow<TableResult>() {
          @Override
          public void updateItem(TableResult item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
              if (item.getId() <= index) {
                pseudoClassStateChanged(gamePseudoClass, true);
              }
              else {
                pseudoClassStateChanged(endPseudoClass, true);
              }
            }
          }
        };

    row.setOnMouseClicked(
        event -> {
          if (event.getClickCount() == 2) {
            this.onClickAction(row);
          }
        });

    return row;
  }
}
