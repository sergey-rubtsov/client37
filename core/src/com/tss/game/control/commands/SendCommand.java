package com.tss.game.control.commands;

public class SendCommand implements Command {

    public interface SendCommandListener {
	public void send(String string);
    }

    String message;
    
    SendCommandListener listener;

    public SendCommand(String message, SendCommandListener listener) {
	this.message = message;
	this.listener = listener;
    }

    @Override
    public void execute() {
	System.out.println(message);
	listener.send(message);
    }

}
