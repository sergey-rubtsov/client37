package com.tss.game.control;

import com.tss.game.model.Board;
import com.tss.game.model.Cell;
import com.tss.game.view.GameScreen;

public class GameProcessor implements SocketListener {

    public interface GameListener {

	public void drugDice();

	public void dropDice();

	public void newGame();
    }
    
    Board board;
    
    GameScreen gameScreen;
    
    GameSocket gameSocket;

    private Status status;
    
    private boolean connectionOpened;

    public enum Status {
	SENDING, RECEIVING, EXECUTING, WAITING_ACTION
    }
    
    public GameProcessor(GameScreen gameScreen, Board board) {
	setConnectionOpened(false);
	this.gameScreen = gameScreen;
	this.board = board;
	this.status = Status.WAITING_ACTION;
    }

    public Status getStatus() {
	return status;
    }

    public void setStatus(Status status) {
	this.status = status;
    }

    @Override
    public void messageReceived(String message) {	
	System.out.println(message);
    }
    
    public void sendMessage(String message) {
	this.gameSocket.send(message);
    }

    public void touch(Cell cell) {
      if (cell != null) {
	sendMessage(Commands.PING);
	int col = cell.getColor();
	col++;
        cell.setColor(col);
      }
    }

    @Override
    public void setSocket(GameSocket gameSocket) {
	this.gameSocket = gameSocket;
    }

    @Override
    public void opened() {
	setConnectionOpened(true);	
    }

    @Override
    public void closed() {
	setConnectionOpened(false);	
    }

    public boolean isConnectionOpened() {
	return connectionOpened;
    }

    public void setConnectionOpened(boolean connectionOpened) {
	this.connectionOpened = connectionOpened;
    }
    
    

}
