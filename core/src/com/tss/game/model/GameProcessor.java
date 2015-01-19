package com.tss.game.model;

import com.badlogic.gdx.math.Vector3;
import com.tss.game.GameSocket.SocketListener;
import com.tss.game.view.GameScreen;
import com.tss.game.view.GameScreen.TouchListener;

public class GameProcessor implements SocketListener, TouchListener {

    public interface GameListener {

	public void drugDice();

	public void dropDice();

	public void newGame();
    }
    
    Board board;
    
    GameScreen gameScreen;

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

    @Override
    public void touch(Vector3 touchPoint) {
      
    }

    public void touch(Cell cell) {
      if (cell != null) {
        int c = cell.getColor();
        c++;
        cell.setColor(c);
      }
    }
    
    

}
