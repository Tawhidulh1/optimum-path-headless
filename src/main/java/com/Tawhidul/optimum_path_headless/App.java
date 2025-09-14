package com.Tawhidul.optimum_path_headless;

import com.Tawhidul.optimum_path_headless.AStar;
import com.Tawhidul.optimum_path_headless.AStar.Graph;
import com.Tawhidul.optimum_path_headless.AStar.PathAndDistance;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
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
    map[0] = new String[] { "W", "W", "W", "O", "E", "W", "W", "W" };
    map[1] = new String[] { "W", "W", "W", "W", "O", "W", "W", "W" };
    map[2] = new String[] { "W", "W", "W", "O", "O", "W", "W", "W" };
    map[3] = new String[] { "W", "W", "W", "O", "W", "W", "W", "W" };
    map[4] = new String[] { "W", "W", "W", "O", "O", "W", "W", "W" };
    map[5] = new String[] { "W", "W", "W", "W", "O", "W", "W", "W" };
    map[6] = new String[] { "W", "W", "W", "W", "O", "W", "W", "W" };
    map[7] = new String[] { "W", "W", "W", "S", "O", "W", "W", "W" };

    List<Integer> data = new ArrayList<Integer>();
    int[] heuristics = new int[map.length * map[0].length];
    Graph graph = new Graph(heuristics.length);

    Point start = findStart(map);
    Point end = findEnd(map);

    for (int y = 0; y < map.length; y++) {
      for (int x = 0; x < map[0].length; x++) {
        if (!map[y][x].toUpperCase().equals("W")) {
          for (Point p : getNeighbors(map, new Point(x, y))) {
            if (!map[p.getY()][p.getX()].toUpperCase().equals("W")) {
              data.add(y * map[0].length + x);
              data.add(p.getY() * map[0].length + p.getX());
              data.add(1);
              data.add(0);
            }
          }
        }
      }
    }
    for (int r = 0; r < map.length; r++) {
      for (int c = 0; c < map[0].length; c++) {
        heuristics[r * map[0].length + c] = (int) Heuristic(map, c, r);
      }
    }
    AStar.initializeGraph(graph, data);
    PathAndDistance aStar = AStar.aStar(getId(map, start), getId(map, end), graph, heuristics);
    ArrayList<Integer> path = aStar.getPath();
    for (int i : path) {
      System.out.println(i + " ");
    }
  }

  // Assumed the goal is represented as 'E'
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
