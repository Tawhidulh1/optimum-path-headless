package com.Tawhidul.optimum_path_headless;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import org.jline.terminal.*;
import org.jline.reader.*;
import org.jline.keymap.*;
import org.jline.widget.*;
import org.jline.utils.InfoCmp;
import org.jline.utils.InfoCmp.Capability;

// This application is currently in Demo

public class App {

  public static class Point {
    private int x;
    private int y;
    public double f;
    public double g;
    private Point parent;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
      this.f = Double.MAX_VALUE;
      this.g = Double.MAX_VALUE;
      parent = null;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public double getF() {
      return f;
    }

    public double getG() {
      return g;
    }

    public Point getParent() {
      return parent;
    }

    public void setF(double f) {
      this.f = f;
    }

    public void setG(double g) {
      this.g = g;
    }

    public void setParent(Point parent) {
      this.parent = parent;
    }

    public String toString() {
      return "Point: " + x + " " + y;
    }
  }

  public static void main(String[] args) throws IOException {
    String[][] map = new String[8][8];
    map[0] = new String[] { "O", "O", "W", "O", "E", "W", "O", "O" };
    map[1] = new String[] { "O", "O", "W", "W", "O", "W", "O", "O" };
    map[2] = new String[] { "O", "O", "W", "O", "O", "W", "O", "O" };
    map[3] = new String[] { "O", "O", "W", "O", "W", "W", "O", "O" };
    map[4] = new String[] { "O", "O", "W", "O", "O", "W", "O", "O" };
    map[5] = new String[] { "O", "O", "W", "W", "O", "W", "O", "O" };
    map[6] = new String[] { "O", "O", "W", "W", "O", "W", "O", "O" };
    map[7] = new String[] { "O", "O", "W", "S", "O", "W", "O", "O" };

    Point start = findStart(map);
    System.out.println(start);

    List<Point> neighbors = getNeighbors(map, start);
    for (Point p : neighbors) {
      System.out.print(p + " ");
    }

  }

  // Assumed the goal is represented as 'E'
  public static double heuristic(String[][] str, int x, int y) {
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
