package com.tss.game.model;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class Dice {

    public enum Number {
        NULL, ONE, TWO, THREE, FOUR, FIVE, SIX
    }

    ;

    public enum Status {
        LOCKED, UNLOCKED
    }

    ;

    private Color color;

    private Status status;

    private Cell cell;

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
        this.owner = owner;
        this.color = owner.getColor();
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

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Color getColor() {
        return color;
    }

    public void reset() {

    }
}
