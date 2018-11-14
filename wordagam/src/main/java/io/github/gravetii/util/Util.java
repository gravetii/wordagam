package io.github.gravetii.util;

import java.util.ArrayList;
import java.util.List;

public class Util {

  public static List<GridPoint> getNeighbors(GridPoint point) {
    int x = point.x;
    int y = point.y;
    int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};
    List<GridPoint> neighbors = new ArrayList<>(8);
    for (int i = 0; i < dx.length; ++i) {
      GridPoint n = new GridPoint(x + dx[i], y + dy[i]);
      if (n.isValid()) {
        neighbors.add(n);
      }
    }

    return neighbors;
  }

  public static GridPoint getGridPointFromImageViewLabel(String label) {
    String[] tokens = label.split("\\$")[1].split("_");
    return new GridPoint(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
  }
}
