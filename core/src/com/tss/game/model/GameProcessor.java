package com.tss.game.model;

import com.tss.game.control.Commands;
import com.tss.game.control.GameSocket;
import com.tss.game.control.SocketListener;
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

    public enum Status {
	SENDING, RECEIVING, EXECUTING, WAITING_ACTION
    }
    
    public GameProcessor(GameScreen gameScreen, Board board) {
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
	sendMessage(Commands.PING);
	int col = cell.getColor();
	col++;
        cell.setColor(col);
    }

    @Override
    public void setSocket(GameSocket gameSocket) {
	this.gameSocket = gameSocket;
    }

    @Override
    public void opened() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void closed() {
	// TODO Auto-generated method stub
	
    }
    
    

}
