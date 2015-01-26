package com.tss.game.control.commands.send;

import com.tss.game.model.Cell;
import com.tss.game.model.GameState;

public class Move implements Send {
    
    String text;

    public Move(GameState state, Cell cell) {
	text = "move" + state.getStep() + " " + cell.getIndex();	
    }

    @Override
    public String getCommandText() {
	return text;
    }

}
