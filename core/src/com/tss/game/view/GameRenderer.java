package com.tss.game.view;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.tss.game.Constants;
import com.tss.game.model.Board;
import com.tss.game.model.Cell;
import com.tss.game.model.Dice;

public class GameRenderer extends SpriteBatch implements Constants {

    ShapeRenderer renderer;

    public GameRenderer() {
	super();
	this.renderer = new ShapeRenderer(512);
    }

    public void renderBackground() {
	disableBlending();
	begin();
	draw(Assets.backgroundRegion, 0, 0, 354, 354);
	draw(Assets.backgroundRegion, 0, 354, 354, 354);
	end();
    }
    
    public void render(Board board) {
	GL20 gl = Gdx.gl;
	gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	renderBackground();	
	renderBoard(board);
    }

    public void renderBoard(Board board) {
	enableBlending();
	drawCells(board.getCells());
	drawDices(board.getDices());
    }

    private void drawDices(LinkedList<Dice> dices) {
	for (Dice dice : dices) {
	    drawDice(dice);
	}
    }

    private void drawDice(Dice dice) {
	renderer.begin(ShapeType.Filled);
	renderer.setColor(dice.getOwner().getColor());
	renderer.rect(dice.getCell().getBL().x, dice.getCell().getBL().y, xUnit * 2, yUnit * 2);
	renderer.end();		
    }

    private void drawCells(Cell[] cells) {
	for (Cell cell : cells) drawCell(cell);
    }

    private void drawCell(Cell c) {
	drawCellMain(c);
	drawCellBorder(c);
    }

    private void drawCellMain(Cell c) {
	renderer.begin(ShapeType.Filled);
	renderer.setColor(CELL_COLOR[c.getColor()]);	
	renderer.triangle(c.getB().x, c.getB().y, 
		c.getBL().x, c.getBL().y, c.getBR().x, c.getBR().y);
	renderer.rect(c.getBL().x, c.getBL().y, xUnit * 2, yUnit * 2);
	renderer.triangle(c.getT().x, c.getT().y, 
		c.getTL().x, c.getTL().y, c.getTR().x, c.getTR().y);
	renderer.end();	
    }
    
    private void drawCellBorder(Cell c) {	
	float[] vertices = {c.getT().x, c.getT().y, 
		c.getTL().x, c.getTL().y, 
		c.getBL().x, c.getBL().y, 
		c.getB().x, c.getB().y, 
		c.getBR().x, c.getBR().y, 
		c.getTR().x, c.getTR().y};
	renderer.begin(ShapeType.Line);
	renderer.setColor(CBCOLOR);	
	renderer.polygon(vertices);
	renderer.end();
    }

    @Override
    public void dispose() {
	super.dispose();
	renderer.dispose();
    }

    public void render(TextureRegion button, Rectangle diceButtonBounds) {	
	begin();	
	draw(button, diceButtonBounds.getX(), diceButtonBounds.getY(), diceButtonBounds.getWidth(), diceButtonBounds.getHeight());
	end();
    }

}
