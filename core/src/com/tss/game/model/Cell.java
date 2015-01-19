package com.tss.game.model;

import com.tss.game.Constants;

public class Cell implements Point, Constants { 
    
    public final static float y23 = yUnit;
    public final static float x32 = xUnit;
    public final static float y31 = 0;
    public final static float x13 = -2 * xUnit;
    public final static float det = y23 * x13 - x32 * y31;
    public final static float minD = Math.min(det, 0);
    public final static float maxD = Math.max(det, 0);
    public final static float invdet = x32 * y31 - y23 * x13;
    public final static float invminD = Math.min(invdet, 0);
    public final static float invmaxD = Math.max(invdet, 0);

    private final int index;

    private int color;

    public void setColor(int color) {
	if (color >= CELL_COLOR.length) {
	    color = 0;
	    return;
	}
	this.color = color;
    }

    private Cell[] neighbors;

    private Dice dice;

    private float x;

    private float y;

    private Vertex t;
    private Vertex tr;
    private Vertex br;
    private Vertex b;
    private Vertex bl;
    private Vertex tl;

    public Cell(int index) {
	this.index = index;
	this.color = 0;
    }

    public Cell(int index, int color) {
	setColor(color);
	this.index = index;
    }

    public int getIndex() {
	return index;
    }

    public Cell[] getNeighbors() {
	return neighbors;
    }

    public void setNeighbors(Cell[] neighbors) {
	this.neighbors = neighbors;
    }

    public Dice getDice() {
	return dice;
    }

    public void setDice(Dice dice) {
	this.dice = dice;
    }
    
    public boolean contains(float x, float y) {
	return (x >= bl.x && x <= br.x && y >= bl.y && y <= tl.y) || 
	barycentricTopTriangleContains(x, y, tr) ||
	barycentricBotTriangleContains(x, y, br); 

    }
    
    private static boolean barycentricTopTriangleContains(float x, float y, Point p) {	
	float dx = x - p.getX();
	float dy = y - p.getY();
	float a = y23 * dx + x32 * dy;
        if (a < minD || a > maxD)
            return false;
        float b = y31 * dx + x13 * dy;
        if (b < minD || b > maxD)
            return false;
        float c = det - a - b;
        if (c < minD || c > maxD)
            return false;
	return true;
    }
    
    private static boolean barycentricBotTriangleContains(float x, float y, Point p) {	
	float dx = x - p.getX();
	float dy = y - p.getY();
	float a = x32 * dy - y23 * dx;
        if (a < invminD || a > invmaxD)
            return false;
        float b = y31 * dx + x13 * dy;
        if (b < invminD || b > invmaxD)
            return false;
        float c = invdet - a - b;
        if (c < invminD || c > invmaxD)
            return false;
	return true;
    }

    public Vertex getT() {
	return t;
    }

    public void setT(Vertex t) {
	this.t = t;
    }

    public Vertex getTR() {
	return tr;
    }

    public void setTR(Vertex tr) {
	this.tr = tr;
    }

    public Vertex getBR() {
	return br;
    }

    public void setBR(Vertex br) {
	this.br = br;
    }

    public Vertex getB() {
	return b;
    }

    public void setB(Vertex b) {
	this.b = b;
    }

    public Vertex getBL() {
	return bl;
    }

    public void setBL(Vertex bl) {
	this.bl = bl;
    }

    public Vertex getTL() {
	return tl;
    }

    public void setTL(Vertex tl) {
	this.tl = tl;
    }

    @Override
    public float getX() {
	return x;
    }

    @Override
    public float getY() {
	return y;
    }

    public void setX(float x) {
	this.x = x;
    }

    public void setY(float y) {
	this.y = y;
    }

    public int getColor() {
	return color;
    }
}
