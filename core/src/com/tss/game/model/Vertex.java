package com.tss.game.model;

public class Vertex implements Point {

    public final float x;

    public final float y;

    public Vertex(float x, float y) {
	super();
	this.x = x;
	this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }    
}
