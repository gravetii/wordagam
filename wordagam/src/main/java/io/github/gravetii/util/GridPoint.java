package io.github.gravetii.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GridPoint {

  public final int x;
  public final int y;

  private List<GridPoint> neighbors;

  public GridPoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  private List<GridPoint> computeNeighbors() {
    int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    List<GridPoint> neighbors = new ArrayList<>(8);
    for (int i = 0; i < dx.length; ++i) {
      GridPoint n = new GridPoint(this.x + dx[i], this.y + dy[i]);
      if (n.isValid()) {
        neighbors.add(n);
      }
    }

    return neighbors;
  }

  private boolean isValid() {
    return !(x < 0 || x > 3 || y < 0 || y > 3);
  }

  public List<GridPoint> getNeighbors() {
    if (this.neighbors == null) {
      this.neighbors = this.computeNeighbors();
    }

    return this.neighbors;
  }

  boolean isNeighbor(GridPoint point) {
    return this.getNeighbors().contains(point);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof GridPoint) {
      GridPoint point = (GridPoint) object;
      return this.x == point.x && this.y == point.y;
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
