package com.tss.game.model;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Color;

public class Player {

    private String Id;
    
    public boolean isMe;
    
    int seat;
    
    public Dice takenDice;

    public Dice getTakenDice() {
        return takenDice;
    }

    public void setTakenDice(Dice takenDice) {
        this.takenDice = takenDice;
    }

    private LinkedList<Dice> dices;

    private Color color;

    public String getId() {
	return Id;
    }

    public void setId(String id) {
	Id = id;
    }

    public LinkedList<Dice> getDices() {
	return dices;
    }

    public Color getColor() {
	return color;
    }

    public void setColor(Color color) {
	this.color = color;
    }
 
    private void initDices() {
	dices = new LinkedList<Dice>();
	for (int i = 0; i < 12; i++) {
	    dices.add(new Dice(this));
	}
    }

    public Player(String id, int seat) {
	this.isMe = false;
	this.Id = id;
	this.seat = seat;
	this.color = new Color(1f, 0, 0, 1);
	initDices();
    }
    
    public Player(String id, Color color, int seat) {
	this.isMe = false;
	this.Id = id;
	this.seat = seat;
	this.color = color;
	initDices();
    }
    
    public Player(String id, boolean isMe, int seat) {
	this.isMe = isMe;	
	this.Id = id;
	this.seat = seat;
	this.color = new Color(0, 1f, 0, 1);
	initDices();
    }
}
