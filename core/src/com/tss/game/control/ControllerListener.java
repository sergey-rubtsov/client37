package com.tss.game.control;

import com.tss.game.model.Dice;

public interface ControllerListener {
    
    public void roll(Dice dice);
    
    public void removeDice(Dice dice);
    
    public void lockDice(Dice dice);
    
    public void unlockDice(Dice dice);
    
    public void resetGame();
    
}
