package com.Tawhidul.optimum_path_headless;

import com.Tawhidul.optimum_path_headless.AStar.Graph;
import com.Tawhidul.optimum_path_headless.AStar.PathAndDistance;
import com.Tawhidul.optimum_path_headless.Utils.Helper;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

// This application is currently in Demo

public class App {

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

    Point start = Helper.findStart(map);
    Point end = Helper.findEnd(map);

    for (int r = 0; r < map.length; r++) {
      for (int c = 0; c < map[0].length; c++) {
        if (!map[r][c].toUpperCase().equals("W")) {
          for (Point p : Helper.getNeighbors(map, new Point(c, r))) {
            if (!map[p.getY()][p.getX()].toUpperCase().equals("W")) {
              data.add(r * map[0].length + c);
              data.add(p.getX() + map[0].length * p.getY());
              data.add(1);
            }
          }
        }
      }
    }
    for (int r = 0; r < map.length; r++) {
      for (int c = 0; c < map[0].length; c++) {
        heuristics[r * map[0].length + c] = (int) Helper.Heuristic(map, c, r);
      }
    }
    AStar.initializeGraph(graph, data);
    PathAndDistance aStar = AStar.aStar(Helper.getId(map, start), Helper.getId(map, end), graph, heuristics);
    ArrayList<Integer> path = aStar.getPath();
    Render render = new Render();
    render.drawLayout();
    System.in.read();
    render.close();
  }

}
