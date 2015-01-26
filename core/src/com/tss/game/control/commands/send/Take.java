package com.tss.game.control.commands.send;

import com.tss.game.model.GameState;

public class Take implements Send {

    String text;
    
    public Take(GameState state, int hex) {
	text = "take" + (state.getStep() + 1) + " " + hex;
    }

    @Override
    public String getCommandText() {
	return text;
    }

}
