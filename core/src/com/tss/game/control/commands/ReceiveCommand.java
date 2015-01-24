package com.tss.game.control.commands;

import com.tss.game.control.ControllerListener;

public class ReceiveCommand implements Command {
    
    public interface ReceiveCommandListener {
	
    }
    
    String message;
    
    ReceiveCommandListener listener;

    public ReceiveCommand(String message, ReceiveCommandListener listener) {
	this.listener = listener;
	this.message = message;
    }

    @Override
    public void execute() {
	System.out.println(message);
    }

}
