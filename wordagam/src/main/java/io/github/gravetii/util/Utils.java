package io.github.gravetii.util;

public class Utils {

    public static GridPoint getGridPointFromImageViewLabel(String label) {
        String[] tokens = label.split("\\$")[1].split("_");
        return new GridPoint(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
    }

    public static boolean[][] arrCopy(boolean [][] arr) {
        int len = arr.length;
        boolean[][] result = new boolean[len][arr[0].length];
        for (int i=0;i<len;++i) {
            System.arraycopy(arr[i], 0, result[i], 0, arr[i].length);
        }

        return result;
    }

}
