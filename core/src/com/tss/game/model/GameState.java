package com.tss.game.model;

public class GameState {
    
    Player[] players;
    
    private Player currentPlayer;
    
    private Player nextPlayer;
    
    int currentSeat;
    
    Status status;
    
    int step;
    
    int mySeat;

    public enum Next {ME, NOT_ME, NULL}
    
    public enum Status {WAIT_SERVER_ANSWER, WAIT_USER_ACTION}
    
    public GameState(){
	this.step = 0;
    }
    
    public void newGame(int mySeat, String[] playersId) {
	this.mySeat = mySeat;
	this.players = new Player[playersId.length];
	for (int i = 0; i < playersId.length; i++) {
	    Player p;
	    if (i == (mySeat - 1)) {
		p = new Player(playersId[i], true);
	    } else p = new Player(playersId[i]);
	    players[i] = p;
	}
	setCurrentPlayer(this.players[0]);
	setNextPlayer(this.players[1]);
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
    
    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCurrentSeat(int currentSeat) {
	setCurrentPlayer(players[currentSeat - 1]);
	this.currentSeat = currentSeat;
    }

    public void setMySeat(int mySeat) {
	if (this.mySeat != mySeat) {
	    System.out.println("Sync error!");
	}
	this.mySeat = mySeat;
    }

    public Player getCurrentPlayer() {
	return currentPlayer;
    }

    private void setCurrentPlayer(Player currentPlayer) {
	this.currentPlayer = currentPlayer;
    };
    
    public Next whoNext() {
	if (currentPlayer.isMe) return Next.ME;
	return Next.NOT_ME;
    }

    public void setNext(int next) {
	
    }

    public Player getNextPlayer() {
	return nextPlayer;
    }

    private void setNextPlayer(Player nextPlayer) {
	this.nextPlayer = nextPlayer;
    }

    public void reset() {
    }

}
