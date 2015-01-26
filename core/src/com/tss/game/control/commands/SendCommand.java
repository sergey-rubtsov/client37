package com.tss.game.control.commands;

import com.tss.game.control.GameSocket;
import com.tss.game.control.commands.send.Send;

public class SendCommand implements Command {

    String message;
    
    GameSocket socket;

    public SendCommand(Send message, GameSocket socket) {
	this.message = message.getCommandText();
	this.socket = socket;
    }

    public SendCommand(String message, GameSocket socket) {
	this.message = message;
	this.socket = socket;
    }
    
    @Override
    public void execute() {
	System.out.println("s:" + message);
	socket.send(message);
    }

}
