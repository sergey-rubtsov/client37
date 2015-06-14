package com.tss.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Builder {

    private final Cell[][] mesh;

    private final Cell[] cells;

    private final HashMap<Integer, Cell> index;

    public HashMap<Integer, Cell> getIndex() {
        return index;
    }

    public Builder(int yShift, float xUnit, float yUnit) {
        index = new HashMap<Integer, Cell>();
        ArrayList<Cell> result = new ArrayList<Cell>();
        this.mesh = new Cell[16][24];
        buildCenters(mesh);
        buildNeigbors(mesh);
        Point[][] points = mesh;
        for (int i = 0; i < mesh.length; i++) {
            for (int j = 0; j < mesh[i].length; j++) {
                Cell c = mesh[i][j];
                if (c != null) {
                    c.setX(i * xUnit);
                    c.setY(j * yUnit + yShift);
                    Point t = points[i][j + 2];
                    if (t == null) {
                        t = new Vertex(i * xUnit, (j + 2) * yUnit + yShift);
                    }
                    c.setT((Vertex) t);
                    Point b = points[i][j - 2];
                    if (b == null) {
                        b = new Vertex(i * xUnit, (j - 2) * yUnit + yShift);
                    }
                    c.setB((Vertex) b);
                    Point tr = points[i + 1][j + 1];
                    if (tr == null) {
                        tr = new Vertex((i + 1) * xUnit, (j + 1) * yUnit
                                + yShift);
                    }
                    c.setTR((Vertex) tr);
                    Point tl = points[i - 1][j + 1];
                    if (tl == null) {
                        tl = new Vertex((i - 1) * xUnit, (j + 1) * yUnit
                                + yShift);
                    }
                    c.setTL((Vertex) tl);
                    Point bl = points[i - 1][j - 1];
                    if (bl == null) {
                        bl = new Vertex((i - 1) * xUnit, (j - 1) * yUnit
                                + yShift);
                    }
                    c.setBL((Vertex) bl);
                    Point br = points[i + 1][j - 1];
                    if (br == null) {
                        br = new Vertex((i + 1) * xUnit, (j - 1) * yUnit
                                + yShift);
                    }
                    c.setBR((Vertex) br);
                    result.add(c);
                }
            }
        }
        this.cells = result.toArray(new Cell[result.size()]);
    }

    public Cell[][] getMesh() {
        return this.mesh;
    }

    public Cell[] getCells() {
        return this.cells;
    }

    public void buildMesh(int yShift, float xUnit, float yUnit) {

    }

    private Cell addToIndex(Cell cell) {
        index.put(cell.getIndex(), cell);
        return cell;
    }

    private void buildCenters(Cell[][] m) {
        m[5][21] = addToIndex(new Cell(1));
        m[7][21] = addToIndex(new Cell(2));
        m[9][21] = addToIndex(new Cell(3));
        m[11][21] = addToIndex(new Cell(4));

        m[4][18] = addToIndex(new Cell(8));
        m[6][18] = addToIndex(new Cell(9, 1));
        m[8][18] = addToIndex(new Cell(10, 1));
        m[10][18] = addToIndex(new Cell(11, 1));
        m[12][18] = addToIndex(new Cell(12));

        m[3][15] = addToIndex(new Cell(15));
        m[5][15] = addToIndex(new Cell(16, 1));
        m[7][15] = addToIndex(new Cell(17, 1));
        m[9][15] = addToIndex(new Cell(18, 1));
        m[11][15] = addToIndex(new Cell(19, 1));
        m[13][15] = addToIndex(new Cell(20));

        m[2][12] = addToIndex(new Cell(22));
        m[4][12] = addToIndex(new Cell(23, 1));
        m[6][12] = addToIndex(new Cell(24, 1));
        m[8][12] = addToIndex(new Cell(25, 1));
        m[10][12] = addToIndex(new Cell(26, 1));
        m[12][12] = addToIndex(new Cell(27, 1));
        m[14][12] = addToIndex(new Cell(28));

        m[3][9] = addToIndex(new Cell(30));
        m[5][9] = addToIndex(new Cell(31, 1));
        m[7][9] = addToIndex(new Cell(32, 1));
        m[9][9] = addToIndex(new Cell(33, 1));
        m[11][9] = addToIndex(new Cell(34, 1));
        m[13][9] = addToIndex(new Cell(35));

        m[4][6] = addToIndex(new Cell(38));
        m[6][6] = addToIndex(new Cell(39, 1));
        m[8][6] = addToIndex(new Cell(40, 1));
        m[10][6] = addToIndex(new Cell(41, 1));
        m[12][6] = addToIndex(new Cell(42));

        m[5][3] = addToIndex(new Cell(46));
        m[7][3] = addToIndex(new Cell(47));
        m[9][3] = addToIndex(new Cell(48));
        m[11][3] = addToIndex(new Cell(49));
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
            int[] nIndexes = {n - 1, n + 1, n - 7, n + 7, n - 8, n + 8};
            ArrayList<Cell> list = new ArrayList<Cell>();
            for (int i = 0; i < nIndexes.length; i++) {
                Cell c = exists.get(nIndexes[i]);
                if (c != null)
                    list.add(c);
            }
            entry.getValue().setNeighbors(list.toArray(new Cell[list.size()]));
        }
    }

}
