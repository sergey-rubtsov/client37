package com.tss.game.control;

public class ReceiveCommand implements Command {
    
    String message;
    
    ControllerListener listener;

    public ReceiveCommand(String message, ControllerListener listener) {
	this.listener = listener;
	this.message = message;
    }

    @Override
    public void execute() {
	System.out.println(message);
    }

}
