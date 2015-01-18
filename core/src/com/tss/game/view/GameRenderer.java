package com.tss.game.view;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.tss.game.Constants;
import com.tss.game.model.Board;
import com.tss.game.model.Cell;
import com.tss.game.model.Dice;
import com.tss.game.model.Point;

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
	renderBackground();	
	renderBoard(board);
    }

    public void renderBoard(Board board) {
	drawCells(board.getCells());
	drawDices(board.getDices());
    }

    private void drawDices(LinkedList<Dice> dices) {
	for (Dice dice : dices) {
	    drawDice(dice);
	}
    }

    private void drawDice(Dice dice) {
	
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

    public void fillRectangle(float x, float y, float width, float height,
	    Color color) {
	renderer.begin(ShapeType.Filled);
	renderer.setColor(color);
	renderer.rect(x + width / 2, y + height / 2, width, height);
	renderer.end();
    }

    public void drawTriangle(Point a, Point b, Point c, Color color) {
	renderer.setColor(color);
	renderer.begin(ShapeType.Filled);	
	//renderer.triangle(a.x, a.y, b.x, b.y, c.x, c.y, color, color, color);
	renderer.end();
    }

    @Override
    public void dispose() {
	super.dispose();
	renderer.dispose();
    }

}
