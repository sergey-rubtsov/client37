package com.tss.game.control;

import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.Queue;

import org.java_websocket.WebSocket.READYSTATE;

import com.tss.game.model.Board;
import com.tss.game.model.Cell;
import com.tss.game.model.Dice;

public class GameProcessor implements SocketListener, BoardListener {
    
    Board board;
    
    ControllerListener listener;
    
    GameSocket socket;
    
    Queue<Command> commands;
    
    private boolean connectionOpened;
    
    public GameProcessor() {
	setConnectionOpened(false);
	try {
	    socket = new GameSocket(this);
	} catch (URISyntaxException e) {
	    
	}
	socket.connect();
	commands = new LinkedList<Command>();
    }    

    public ControllerListener getListener() {
        return listener;
    }

    public void setListener(ControllerListener listener) {
        this.listener = listener;
    }

    @Override
    public void messageReceived(String message) {	
	System.out.println(message);
    }

    public void execute(Dice dice) {
	listener.lockDice(dice);
    }

    @Override
    public void opened() {
	setConnectionOpened(true);	
    }

    public boolean isConnectionOpened() {
	return connectionOpened;
    }

    public void setConnectionOpened(boolean connectionOpened) {
	this.connectionOpened = connectionOpened;
    }
    
    public void update() {
	if (!commands.isEmpty()) {
            Command c = commands.peek();
            c.execute();
            commands.remove();            
        }
    }

    @Override
    public void pickUp(Dice dice) {
	//listener.
    }

    @Override
    public void touch(Cell cell) {
	
    }

    @Override
    public void closed() {
		
    }

    public void close() {
	socket.close();	
    }
    
	//if (this.socket.getReadyState() == READYSTATE.OPEN) {
	    //this.socket.send(PING);
	//}
    
    
}
