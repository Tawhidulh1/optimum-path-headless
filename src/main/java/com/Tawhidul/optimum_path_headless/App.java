package com.Tawhidul.optimum_path_headless;

import java.io.*;
import java.util.Queue;
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
    private String[][] str;
    private int x;
    private int y;

    private String strVal;

    public Point(String[][] str, int x, int y) {
      this.str = str;
      this.x = x;
      this.y = y;

      strVal = str[y][x];
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public String getStrVal() {
      return strVal;
    }

    public String toString() {
      return "test";
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
    System.out.println(f(map, 4, 0));

    Queue<Point> queue = new LinkedList<>();
    queue.add(new Point(map, 3, 7));

    while ((int) (f(map, queue.peek().getX(), queue.peek().getY())) != 0) {
      Point cur = queue.remove();

    }
    System.out.println("reached end");
  }

  public static void diffuse(Queue<Point> q, Point p) {

  }

  public static double f(String[][] str, int x, int y) {
    if (str[y][x].toLowerCase().equals("e")) {
      return 0;
    }
    return h(str, x, y) + g(str, x, y);
  }

  // Assumed the goal is represented as 'E'
  public static double h(String[][] str, int x, int y) {
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

  // Assumed the start is represented as 'S'
  public static double g(String[][] str, int x, int y) {
    int x2 = 0;
    int y2 = 0;
    for (int r = 0; r < str.length; r++) {
      for (int c = 0; c < str[0].length; c++) {
        if (str[r][c].toLowerCase().equals("s")) {
          x2 = c;
          y2 = r;
        }
      }
    }
    double cost = Math.sqrt(Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2));
    return cost;
  }

}
