package com.tss.game.control;

import java.util.LinkedList;

import com.tss.game.model.Cell;
import com.tss.game.model.Dice;

public interface ControllerListener {    
    
    public void lockDice(Dice dice);
    
    public void unlockDice(Dice dice);
    
    public void reset();

    public void moveTo(Cell cell, Dice dice);
    
    public void removeFrom(Cell cell);

    public Cell getCell(int index);
    
    public LinkedList<Dice> getDices();
    
}
