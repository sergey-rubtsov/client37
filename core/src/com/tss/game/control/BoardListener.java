package com.tss.game.control;

import com.tss.game.model.Cell;
import com.tss.game.model.Dice;

public interface BoardListener {

    void take(Dice dice);

    void touch(Cell cell);    

}
