package com.tss.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Board {

    /**  
     *      board index is:
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

    public Board() {
	this.dices = new LinkedList<Dice>();
	initCells();
    }

    private void initCells() {
	Cell[] indexes = new Cell[49];
	for (int i = 0; i < indexes.length; i++) {
	    indexes[i] = new Cell(i + 1);
	}
	buildMesh(indexes);
	// setHexagons(indexes);

	int exCount = excludeCells(indexes);
	this.cells = new Cell[indexes.length - exCount];
	int j = 0;
	for (int i = 0; i < indexes.length; i++) {
	    if (indexes[i] != null) {
		this.cells[j] = indexes[i];
		j++;
	    }
	}
	for (int i = 0; i < this.cells.length; i++) {
	    this.cells[i].setNeighbors(findNeigbors(this.cells[i].getIndex(),
		    indexes));
	}
    }
    
    public static void buildMesh(Cell[] c) {
	Point[][] mesh = new Point[16][24];
	buildCenters(mesh);
	buildNeigbors(mesh);
    }

    private static void buildCenters(Point[][] m) {
	m[5][21] = new Point(new Cell(1));
	m[7][21] = new Point(new Cell(2));
	m[9][21] = new Point(new Cell(3));
	m[11][21] = new Point(new Cell(4));
	
	m[4][18] = new Point(new Cell(8));
	m[6][18] = new Point(new Cell(9));
	m[8][18] = new Point(new Cell(10));
	m[10][18] = new Point(new Cell(11));
	m[12][18] = new Point(new Cell(12));
	
	m[3][14] = new Point(new Cell(15));
	m[5][14] = new Point(new Cell(16));
	m[7][14] = new Point(new Cell(17));
	m[9][14] = new Point(new Cell(18));
	m[11][14] = new Point(new Cell(19));
	m[13][14] = new Point(new Cell(20));
	
	m[2][12] = new Point(new Cell(22));
	m[4][12] = new Point(new Cell(23));
	m[6][12] = new Point(new Cell(24));
	m[8][12] = new Point(new Cell(25));
	m[10][12] = new Point(new Cell(26));
	m[12][12] = new Point(new Cell(27));
	m[14][12] = new Point(new Cell(28));
	
	m[3][9] = new Point(0, 0, new Cell(30));
	m[5][9] = new Point(0, 0, new Cell(31));
	m[7][9] = new Point(0, 0, new Cell(32));
	m[9][9] = new Point(0, 0, new Cell(33));
	m[11][9] = new Point(0, 0, new Cell(34));
	m[13][9] = new Point(0, 0, new Cell(35));
	
	m[4][6] = new Point(0, 0, new Cell(38));
	m[6][6] = new Point(0, 0, new Cell(39));
	m[8][6] = new Point(0, 0, new Cell(40));
	m[10][6] = new Point(0, 0, new Cell(41));
	m[12][6] = new Point(0, 0, new Cell(42));
	
	m[5][3] = new Point(0, 0, new Cell(46));
	m[7][3] = new Point(0, 0, new Cell(47));
	m[9][3] = new Point(0, 0, new Cell(48));
	m[11][3] = new Point(0, 0, new Cell(49));
    }
    
    private static void buildNeigbors(Point[][] m) {
	HashMap<Integer, Cell> exists = new HashMap<Integer, Cell>();
	for (int i = 0; i < m.length; i++) {
	    for (int j = 0; j < m[i].length; j++) {
		if (m[i][j] != null) {
		    exists.put(m[i][j].center.getIndex(), m[i][j].center);
		}
	    }
	}
	for (Map.Entry<Integer, Cell> entry : exists.entrySet()) {
	    int n = entry.getKey();
	    int[] nIndexes = { n - 1, n + 1, n - 7, n + 7, n - 8, n + 8 };
	    ArrayList<Cell> list = new ArrayList<Cell>();
	    for (int i = 0; i < nIndexes.length; i++) {
		Cell c = exists.get(nIndexes[i]);
		if (c != null) list.add(c);		
	    }
	    entry.getValue().setNeighbors(list.toArray(new Cell[list.size()]));
	}
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

    @Deprecated
    public static void setHexagons(Cell[] c) {
	c[0].setHex(new Hexagon(5, 10.5f));
	c[1].setHex(new Hexagon(7, 10.5f));
	c[2].setHex(new Hexagon(9, 10.5f));
	c[3].setHex(new Hexagon(11, 10.5f));

	c[7].setHex(new Hexagon(4, 9f));
	c[8].setHex(new Hexagon(6, 9f));
	c[9].setHex(new Hexagon(8, 9f));
	c[10].setHex(new Hexagon(10, 9f));
	c[11].setHex(new Hexagon(12, 9f));

	c[14].setHex(new Hexagon(3, 7.5f));
	c[15].setHex(new Hexagon(5, 7.5f));
	c[16].setHex(new Hexagon(7, 7.5f));
	c[17].setHex(new Hexagon(9, 7.5f));
	c[18].setHex(new Hexagon(11, 7.5f));
	c[19].setHex(new Hexagon(13, 7.5f));

	c[21].setHex(new Hexagon(2, 6f));
	c[22].setHex(new Hexagon(4, 6f));
	c[23].setHex(new Hexagon(6, 6f));
	c[24].setHex(new Hexagon(8, 6f));
	c[25].setHex(new Hexagon(10, 6f));
	c[26].setHex(new Hexagon(12, 6f));
	c[27].setHex(new Hexagon(14, 6f));

	c[29].setHex(new Hexagon(3, 4.5f));
	c[30].setHex(new Hexagon(5, 4.5f));
	c[31].setHex(new Hexagon(7, 4.5f));
	c[32].setHex(new Hexagon(9, 4.5f));
	c[33].setHex(new Hexagon(11, 4.5f));
	c[34].setHex(new Hexagon(13, 4.5f));

	c[37].setHex(new Hexagon(4, 3f));
	c[38].setHex(new Hexagon(6, 3f));
	c[39].setHex(new Hexagon(8, 3f));
	c[40].setHex(new Hexagon(10, 3f));
	c[41].setHex(new Hexagon(12, 3f));

	c[45].setHex(new Hexagon(5, 1.5f));
	c[46].setHex(new Hexagon(7, 1.5f));
	c[47].setHex(new Hexagon(9, 1.5f));
	c[48].setHex(new Hexagon(11, 1.5f));
    }
    
    @Deprecated
    public Cell[] findNeigbors(int n, Cell[] indexes) {
	int[] nIndexes = { n - 1, n + 1, n - 7, n + 7, n - 8, n + 8 };
	ArrayList<Cell> list = new ArrayList<Cell>();
	for (int i = 0; i < nIndexes.length; i++) {
	    if (nIndexes[i] >= 1 && nIndexes[i] <= 49) {
		if (indexes[nIndexes[i] - 1] != null) {
		    list.add(indexes[nIndexes[i] - 1]);
		}
	    }
	}
	return list.toArray(new Cell[list.size()]);
    }
    
    @Deprecated
    public static int excludeCells(Cell[] c) {
	int[] out = { 5, 6, 7, 13, 14, 21, 29, 36, 37, 43, 44, 45 };
	for (int i = 0; i < out.length; i++) {
	    c[out[i] - 1] = null;
	}
	return out.length;
	
    }

}
