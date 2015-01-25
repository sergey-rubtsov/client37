package com.tss.game.desktop;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;

import com.tss.game.control.commands.Commands;

public class Console extends WebSocketClient {

    public Console() throws URISyntaxException {
	super(new URI(Commands.SERVER_URL), new Draft_10());	
    }
    
    //user:serg password:pass
    public static void main(String[] args) throws URISyntaxException {
	Scanner scanner = new Scanner(System.in);
	Console socket = new Console();
	socket.connect();
	while (true) {
	    String sentence = scanner.nextLine();
	    socket.send(sentence);	    
	    if (sentence.equals("exit")) {
		socket.close();
		break;
	    }
	}
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {	
    }

    @Override
    public void onMessage(String message) {
	System.out.println(message);	
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
    }

    @Override
    public void onError(Exception ex) {	
    }

}
