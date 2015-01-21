package com.tss.game.control;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;

public class GameSocket extends WebSocketClient {

    private SocketListener listener;

    public GameSocket(SocketListener listener) throws URISyntaxException {
	super(new URI(Commands.SERVER_URL), new Draft_10());
	this.listener = listener;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
	listener.opened();
    }

    public void addListener(SocketListener listener) {
	this.listener = listener;
    }

    @Override
    public void onMessage(String message) {
	listener.messageReceived(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
	listener.closed();
    }

    @Override
    public void onError(Exception ex) {
    }
}
