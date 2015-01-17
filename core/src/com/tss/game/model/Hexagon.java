package com.tss.game.model;

import com.tss.game.Constants;

public class Hexagon implements Constants {
  
  public static final float apothem = (float) Math.cos(Math.PI / 6) * K;
  
  public Point center;

  public Point getCenter() {
    return center;
  }
  
  public Hexagon(float x, float y) {
      this(new Point(x * K, y * K));
  }
  
  public Hexagon(Point center) {
    this.center = center;
  }
  
  public float getWidth() {
    return 2 * apothem * K;
  }
  
  public float getHeight() {
      return 2 * K;
    }
  
  public Point getT() {
    return new Point(center.x, center.y + K);
  }
  
  public Point getB() {
    return new Point(center.x, center.y - K);
  }
  
  public Point getBL() {
    return new Point(center.x - apothem, center.y - 0.5f * K);
  }
  
  public Point getBR() {
    return new Point(center.x + apothem, center.y - 0.5f * K);
  }
  
  public Point getTL() {
    return new Point(center.x - apothem, center.y + 0.5f * K);
  }
  
  public Point getTR() {
    return new Point(center.x + apothem, center.y + 0.5f * K);
  }  
}