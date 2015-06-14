package com.tss.game.control.commands;

import org.java_websocket.exceptions.WebsocketNotConnectedException;

public interface Command {

    void execute() throws Exception;

}
