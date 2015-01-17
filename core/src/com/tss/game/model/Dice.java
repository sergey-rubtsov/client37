package com.tss.game.model;

import java.util.Random;

public class Dice {

    public enum Number {
	NULL, ONE, TWO, THREE, FOUR, FIVE, SIX
    };

    public enum Status {
	LOCKED, UNLOCKED
    };

    private Status status;

    private Number number;

    private Player owner;

    public float y;

    public float x;

    public Status getStatus() {
	return status;
    }

    public void setStatus(Status status) {
	this.status = status;
    }

    public Number getNumber() {
	return number;
    }

    public void setNumber(Number number) {
	this.number = number;
    }

    public Dice(Player owner) {
	super();
	this.owner = owner;
	this.number = Number.NULL;
	this.status = Status.UNLOCKED;
    }

    public Number roll() {
	Random random = new Random();
	int num = random.nextInt(6) + 1;
	setNumber(Number.values()[num]);
	return this.number;
    }

    public Player getOwner() {
	return owner;
    }

    public void setOwner(Player owner) {
	this.owner = owner;
    }
}
