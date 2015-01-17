package com.tss.game.model;

public class Cell {

    private int index;

    private Cell[] neighbors;

    private Hexagon hex;
    
    private Dice dice;

    public float y;

    public float x;

    public Cell(float x, float y) {
	this.x = x;
	this.y = y;
    }
    
    public Cell() {
    }
    
    public Cell(int index) {
	this.index = index;
    }

    public int getIndex() {
	return index;
    }

    public void setIndex(int index) {
	this.index = index;
    }
    
    public void setXY(float x, float y) {
	this.x = x;
	this.y = y;	
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
    
    public Hexagon getHex() {
	return hex;
    }

    public void setHex(Hexagon hex) {
	this.hex = hex;
    }    

}
