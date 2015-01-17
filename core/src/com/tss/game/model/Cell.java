package com.tss.game.model;

import com.tss.game.Constants;

public class Cell implements Point {

    private final int index;
    
    private final int color;

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
	if (color >= Constants.CELL_COLOR.length) color = Constants.CELL_COLOR.length - 1;
	this.color = color;
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
