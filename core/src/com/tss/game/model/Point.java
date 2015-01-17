package com.tss.game.model;

public class Point {
  
  public float x; 
  
  public float y;
  
  public Cell center;
  
  public Point(float x, float y) {
    this.x = x;
    this.y = y;
  } 
  
  public Point(float x, float y, Cell center) {
      this.x = x;
      this.y = y;
      this.center = center;
  } 
  
  public Point(Cell center) {
      this.center = center;
  } 
}
