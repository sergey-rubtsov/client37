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
	this.cells = Builder.buildMesh();
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
}
