package com.tss.game.model;

public class GameState {
    
    Player[] players;
    
    Turn turn;
    
    public enum Turn {ME, NEXT, NULL}

    public GameState() {
	this.turn = Turn.NULL;
    }
    
    
    public GameState(Player[] players) {
	this.players = players;
	this.turn = Turn.NULL;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    };
    
    

}
