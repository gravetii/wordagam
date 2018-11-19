package io.github.gravetii.scene;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;

public class GameComponent {

  private GridPane gridPane;

  private SplitPane splitPane;

  public GameComponent(GridPane gridPane, SplitPane splitPane) {
    this.gridPane = gridPane;
    this.splitPane = splitPane;
  }

  public GridPane getGridPane() {
    return gridPane;
  }

  public SplitPane getSplitPane() {
    return splitPane;
  }
}
