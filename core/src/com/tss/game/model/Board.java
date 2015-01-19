package com.tss.game.model;

import java.util.ArrayList;
import java.util.LinkedList;

import com.tss.game.Constants;

public class Board implements Constants {

    /**  
     *       board index is:
     *       01  02  03  04
     *     08  09  10  11  12 
     *   15  16  17  18  19  20
     * 22  23  24  25  26  27  28
     *   30  31  32  33  34  35
     *     38  39  40  41  42
     *       46  47  48  49
     */
    private Cell[] cells;

    private LinkedList<Dice> dices;

    public Board(float xUnit, float yUnit, int xShift, int yShift) {
	this.dices = new LinkedList<Dice>();
	Builder b = new Builder(yShift, xUnit, yUnit);
	this.cells = b.getCells();
    }

    public Board() {
	this(xUnit, yUnit, 0, BOARD_Y_BEGIN);
    }

    public Cell[] getCells() {
	return cells;
    }

    public void setCells(Cell[] cells) {
	this.cells = cells;
    }

    public LinkedList<Dice> getDices() {
	return dices;
    }

    public Cell getProximate(float x, float y) {
	ArrayList<Cell> sorted = new ArrayList<Cell>();
	Cell cell = cells[0];
	float dY = delta(cell.getY(), y);
	for (int i = 0; i < cells.length; i++) {
	    Cell object = cells[i];
	    if (delta(object.getY(), y) < dY) {
		dY = delta(object.getY(), y);
		sorted.clear();
		sorted.add(object);
	    } else if (delta(object.getY(), y) == dY) {
		sorted.add(object);
	    }
	}
	cell = sorted.get(0);
	float dX = delta(cell.getX(), x);
	for (int i = 0; i < sorted.size(); i++) {
	    Cell object = sorted.get(i);
	    if (delta(object.getX(), x) < dX) {
		dX = delta(object.getX(), x);
		cell = sorted.get(i);
	    }
	}
	return cell;
    }

    public static float delta(float a, float b) {
	return Math.abs(a - b);
    }
}
