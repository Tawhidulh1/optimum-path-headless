package com.Tawhidul.optimum_path_headless;

public class Point {
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
