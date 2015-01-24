package com.tss.game.model;

public class GameState {
    
    Player[] players;
    
    Turn turn;
    
    Status status;
    
    int step;

    public enum Turn {ME, NEXT, NULL}
    
    public enum Status {WAIT_SERVER_ANSWER, WAIT_USER_ACTION}
    
   // private static GameState instance = new GameState();
    
    public GameState(){
	this.turn = Turn.NULL;
	this.step = 0;
    }
    
    //public static GameState getInstance() {
       //return instance;
    //}

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
    
    public void nextStep() {
	this.step++;
    }
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    };
    
    

}
