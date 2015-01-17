package com.tss.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.tss.game.Constants;

public class Builder implements Constants {
    
    public static Cell[] buildMesh() {
	ArrayList<Cell> result = new ArrayList<Cell>();
	Cell[][] mesh = new Cell[16][24];
	buildCenters(mesh);
	buildNeigbors(mesh);
	Point[][] points = mesh;
	for (int i = 0; i < mesh.length; i++) {
	    for (int j = 0; j < mesh[i].length; j++) {
		Cell c = mesh[i][j];
		if (c != null) {
		    c.setX(i * xUnit);
		    c.setY(j * yUnit + BOARD_Y_BEGIN);
		    Point t = points[i][j + 2];
		    if (t == null) {
			t = new Vertex(i * xUnit, (j + 2) * yUnit + BOARD_Y_BEGIN);
		    } c.setT((Vertex) t);
		    
		    Point b = points[i][j - 2];
		    if (b == null) {
			b = new Vertex(i * xUnit, (j - 2) * yUnit + BOARD_Y_BEGIN);
		    } c.setB((Vertex) b);
		    
		    Point tr = points[i + 1][j + 1];		    
		    if (tr == null) {
			tr = new Vertex((i + 1) * xUnit, (j + 1) * yUnit + BOARD_Y_BEGIN);
		    } c.setTR((Vertex) tr);
		    
		    Point tl = points[i - 1][j + 1];		    
		    if (tl == null) {
			tl = new Vertex((i - 1) * xUnit, (j + 1) * yUnit + BOARD_Y_BEGIN);
		    } c.setTL((Vertex) tl);
		    
		    Point bl = points[i - 1][j - 1];		    
		    if (bl == null) {
			bl = new Vertex((i - 1) * xUnit, (j - 1) * yUnit + BOARD_Y_BEGIN);
		    } c.setBL((Vertex) bl);
		    
		    Point br = points[i + 1][j - 1];		    
		    if (br == null) {
			br = new Vertex((i + 1) * xUnit, (j - 1) * yUnit + BOARD_Y_BEGIN);
		    } c.setBR((Vertex) br);		    
		    result.add(c);
		}
	    }
	}
	return result.toArray(new Cell[result.size()]);
    }
    
    private static void buildCenters(Cell[][] m) {
	m[5][21] = new Cell(1);
	m[7][21] = new Cell(2);
	m[9][21] = new Cell(3);
	m[11][21] = new Cell(4);
	
	m[4][18] = new Cell(8);
	m[6][18] = new Cell(9, 1);
	m[8][18] = new Cell(10, 1);
	m[10][18] = new Cell(11, 1);
	m[12][18] = new Cell(12);
	
	m[3][15] = new Cell(15);
	m[5][15] = new Cell(16, 1);
	m[7][15] = new Cell(17, 1);
	m[9][15] = new Cell(18, 1);
	m[11][15] = new Cell(19, 1);
	m[13][15] = new Cell(20);
	
	m[2][12] = new Cell(22);
	m[4][12] = new Cell(23, 1);
	m[6][12] = new Cell(24, 1);
	m[8][12] = new Cell(25, 1);
	m[10][12] = new Cell(26, 1);
	m[12][12] = new Cell(27, 1);
	m[14][12] = new Cell(28);
	
	m[3][9] = new Cell(30);
	m[5][9] = new Cell(31, 1);
	m[7][9] = new Cell(32, 1);
	m[9][9] = new Cell(33, 1);
	m[11][9] = new Cell(34, 1);
	m[13][9] = new Cell(35);
	
	m[4][6] = new Cell(38);
	m[6][6] = new Cell(39, 1);
	m[8][6] = new Cell(40, 1);
	m[10][6] = new Cell(41, 1);
	m[12][6] = new Cell(42);
	
	m[5][3] = new Cell(46);
	m[7][3] = new Cell(47);
	m[9][3] = new Cell(48);
	m[11][3] = new Cell(49);
    }
    
    private static void buildNeigbors(Cell[][] m) {
	HashMap<Integer, Cell> exists = new HashMap<Integer, Cell>();
	for (int i = 0; i < m.length; i++) {
	    for (int j = 0; j < m[i].length; j++) {
		if (m[i][j] != null) {
		    exists.put(m[i][j].getIndex(), m[i][j]);
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

}
