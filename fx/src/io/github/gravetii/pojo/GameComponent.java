package io.github.gravetii.pojo;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameComponent {

  private GridPane gridPane;

  private VBox vBox;

  public GameComponent(GridPane gridPane, VBox vBox) {
    this.gridPane = gridPane;
    this.vBox = vBox;
  }

  public GridPane getGridPane() {
    return gridPane;
  }

  public VBox getvBox() {
    return vBox;
  }
}
