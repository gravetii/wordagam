package io.github.gravetii.util;

import io.github.gravetii.model.GridPoint;

public class Utils {
  public static GridPoint getGridPointFromImageViewLabel(String label) {
    String[] tokens = label.split("\\$")[1].split("_");
    return new GridPoint(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
  }

  public static String getImgViewLabelFromGridPoint(GridPoint point) {
    return "imgView$" + point.x + "_" + point.y;
  }

  public static int getImageViewIndexFromLabel(String label) {
    String[] tokens = label.split("_");
    return Integer.parseInt(tokens[1]);
  }
}
