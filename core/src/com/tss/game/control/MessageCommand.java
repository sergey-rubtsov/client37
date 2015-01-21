package com.tss.game.control;

public class MessageCommand implements Command {
    
    String message;

    public MessageCommand(String message) {
	this.message = message;
    }

    @Override
    public void execute() {
	
    }

}
