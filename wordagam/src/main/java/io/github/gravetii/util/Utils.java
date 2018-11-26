package io.github.gravetii.util;

import javafx.stage.Stage;

public class Utils {

  public static GridPoint getGridPointFromImageViewLabel(String label) {
    String[] tokens = label.split("\\$")[1].split("_");
    return new GridPoint(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
  }

  public static int getImageViewIndexFromLabel(String label) {
    String[] tokens = label.split("_");
    return Integer.parseInt(tokens[1]);
  }

  public static boolean[][] arrCopy(boolean[][] arr) {
    int len = arr.length;
    boolean[][] result = new boolean[len][arr[0].length];
    for (int i = 0; i < len; ++i) {
      System.arraycopy(arr[i], 0, result[i], 0, arr[i].length);
    }

    return result;
  }

  public static void setPrimaryDimensions(Stage stage) {
    stage.setMinWidth(Constants.DEFAULT_SCENE_WIDTH);
    stage.setMinHeight(Constants.DEFAULT_SCENE_HEIGHT);
    stage.setMaxWidth(Constants.MAX_SCENE_WIDTH);
    stage.setMaxHeight(Constants.MAX_SCENE_HEIGHT);
    stage.setWidth(Constants.DEFAULT_SCENE_WIDTH);
    stage.setHeight(Constants.DEFAULT_SCENE_HEIGHT);
  }

  public static void setThemeDimensions(Stage stage) {
    stage.setWidth(Constants.THEME_SCENE_WIDTH);
    stage.setHeight(Constants.THEME_SCENE_HEIGHT);
  }
}
