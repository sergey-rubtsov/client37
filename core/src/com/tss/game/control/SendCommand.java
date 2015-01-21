package com.tss.game.control;

public class SendCommand implements Command {
    
    String message;
    
    GameSocket socket;

    public SendCommand(String message, GameSocket socket) {
	this.message = message;
	this.socket = socket;
    }

    @Override
    public void execute() {
	socket.send("some text" + message);
    }

}
