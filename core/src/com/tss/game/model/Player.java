package com.tss.game.model;

import com.badlogic.gdx.graphics.Color;

public class Player {

    private String Id;

    private Dice[] dices;

    private Color color;

    public String getId() {
	return Id;
    }

    public void setId(String id) {
	Id = id;
    }

    public Dice[] getDices() {
	return dices;
    }

    public void setDices(Dice[] dices) {
	this.dices = dices;
    }

    public Color getColor() {
	return color;
    }

    public void setColor(Color color) {
	this.color = color;
    }

    public Player(String id, Color color) {
	super();
	this.Id = id;
	this.color = color;
	Dice[] dices = new Dice[12];
	for (int i = 0; i < dices.length; i++) {
	    dices[i] = new Dice(this);
	}
	setDices(dices);
    }
    
    public Player() {
	super();
	this.Id = "serg";
	this.color = new Color(0, 1f, 0, 1);
	Dice[] dices = new Dice[12];
	for (int i = 0; i < dices.length; i++) {
	    dices[i] = new Dice(this);
	}
	setDices(dices);
    }
}
