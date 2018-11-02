package io.github.gravetii.util;

public class GridPoint {

    public final int x;
    public final int y;

    public GridPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isValid() {
        return !(x < 0 || x > 3 || y < 0 || y > 3);
    }
}
