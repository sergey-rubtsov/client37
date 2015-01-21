package com.tss.game.control;

public class SendCommand implements Command {
    
    String message;

    public SendCommand(String message) {
	this.message = message;
    }

    @Override
    public void execute() {
	
    }

}
