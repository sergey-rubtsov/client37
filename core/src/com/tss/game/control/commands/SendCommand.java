package com.tss.game.control.commands;

import org.java_websocket.exceptions.WebsocketNotConnectedException;

import com.tss.game.socket.GameSocket;
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
    public void execute() throws WebsocketNotConnectedException {
        System.out.println("s:" + message);
        socket.send(message);
    }

}
