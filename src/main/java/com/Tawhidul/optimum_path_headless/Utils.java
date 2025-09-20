package com.Tawhidul.optimum_path_headless;

import java.util.ArrayList;
import java.util.List;

public class Utils {

  public static class Helper {

    public static double Heuristic(String[][] str, int x, int y) {
      int x2 = 0;
      int y2 = 0;
      for (int r = 0; r < str.length; r++) {
        for (int c = 0; c < str[0].length; c++) {
          if (str[r][c].toLowerCase().equals("e")) {
            x2 = c;
            y2 = r;
          }
        }
      }
      double heuristic = Math.sqrt(Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2));
      return heuristic;
    }

    public static int getId(String[][] map, Point p) {
      int id;
      id = p.getY() * map[0].length + p.getX();
      return id;
    }

    public static List<Point> getNeighbors(String[][] map, Point p) {
      List<Point> ret = new ArrayList<>();
      int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
      for (int[] dir : directions) {
        int x2 = p.getX() + dir[0];
        int y2 = p.getY() + dir[1];

        if ((y2 >= 0 && map.length > y2) && (x2 >= 0 && map[0].length > x2) && !map[y2][x2].toLowerCase().equals("w")) {
          ret.add(new Point(x2, y2));
        }

      }
      return ret;
    }

    public static Point findStart(String[][] map) {
      for (int r = 0; r < map.length; r++) {
        for (int c = 0; c < map[0].length; c++) {
          if (map[r][c].toLowerCase().equals("s")) {
            return new Point(c, r);
          }
        }
      }
      return null;
    }

    public static Point findEnd(String[][] map) {
      for (int r = 0; r < map.length; r++) {
        for (int c = 0; c < map[0].length; c++) {
          if (map[r][c].toLowerCase().equals("e")) {
            return new Point(c, r);
          }
        }
      }
      return null;
    }
  }

}
