package com.tss.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.tss.game.control.BoardListener;
import com.tss.game.control.ControllerListener;
import com.tss.game.view.GameScreen;

public class Board implements ControllerListener {

    /**
     * board index is:
     * 01  02  03  04
     * 08  09  10  11  12
     * 15  16  17  18  19  20
     * 22  23  24  25  26  27  28
     * 30  31  32  33  34  35
     * 38  39  40  41  42
     * 46  47  48  49
     */
    private final Cell[] cells;

    private LinkedList<Dice> dices;

    private int horizontalPosition;

    private int verticalPosition;

    private BoardListener listener;

    private HashMap<Integer, Cell> index;

    public BoardListener getListener() {
        return listener;
    }

    public void setListener(BoardListener listener) {
        this.listener = listener;
    }

    public Board(float xUnit, float yUnit, int verticalPosition, int horizontalPosition) {
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
        this.dices = new LinkedList<Dice>();
        Builder b = new Builder(horizontalPosition, xUnit, yUnit);
        this.cells = b.getCells();
        this.index = b.getIndex();
    }

    public Board() {
        this(GameScreen.xUnit, GameScreen.yUnit, 0, GameScreen.BOARD_Y_BEGIN);
    }

    public Cell[] getAllCells() {
        return cells;
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

    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    public void setHorizontalPosition(int horizontalPosition) {
        this.horizontalPosition = horizontalPosition;
    }

    public int getVerticalPosition() {
        return verticalPosition;
    }

    public void setVerticalPosition(int verticalPosition) {
        this.verticalPosition = verticalPosition;
    }

    public void touch(float x, float y) {
        Cell cell = getProximate(x, y);
        if (cell.contains(x, y)) {
            Dice dice = cell.getDice();
            if (dice != null) {
                listener.take(dice);
            } else {
                listener.touch(cell);
            }
        }
    }

    @Override
    public void moveTo(Cell cell, Dice dice) {
        cell.setDice(dice);
        dice.setCell(cell);
        dices.add(dice);
    }

    @Override
    public void removeFrom(Cell cell) {
        int index = cell.getIndex();
        for (Dice dice : dices) {
            if (dice.getCell().getIndex() == index) {
                dices.remove(dice);
                dice.setCell(null);
                cell.setDice(null);
                return;
            }
        }
    }

    @Override
    public void unlockDice(Dice dice) {
    }

    @Override
    public void reset() {
    }

    @Override
    public void lockDice(Dice dice) {
    }

    @Override
    public Cell getCell(int cellIndex) {
        return index.get(cellIndex);
    }

}
