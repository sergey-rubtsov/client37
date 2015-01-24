package com.tss.game.control.commands;

import com.tss.game.control.GameSocket;
import com.tss.game.model.Player;

public class MessageCommand implements Command {
    
    String message;
    
    GameSocket socket;
    
    Player player;

    public MessageCommand(String message, Player player, GameSocket socket) {
	this.message = message;
	this.player = player;
	this.socket = socket;
    }

    private String processMessage(String message) {
	return message;
    }

    @Override
    public void execute() {
	socket.send(processMessage(message));
    }

}
