package com.tss.game.view;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.tss.game.Constants;
import com.tss.game.model.Board;
import com.tss.game.model.Cell;
import com.tss.game.model.Dice;
import com.tss.game.model.Hexagon;
import com.tss.game.model.Point;

public class GameRenderer extends SpriteBatch implements Constants {

    ShapeRenderer renderer;

    public final Color CELLCOLOR = new Color(1f, 0f, 0f, 1);
    public final Color DICECOLOR = new Color(0.9f, 0.5f, 0.5f, 1);
    public final Color MENUCOLOR = new Color(0.5f, 0.5f, 0.9f, 1);
    public final Color DEFCOLOR = new Color(0.6f, 0.6f, 0.6f, 1);

    public GameRenderer() {
	super();
	this.renderer = new ShapeRenderer(512);
    }

    public void renderBackground() {
	disableBlending();
	begin();
	draw(Assets.backgroundRegion, 0, 0, GRAPHICS_WIDTH, GRAPHICS_HEIGHT);
	end();
    }

    public void renderBoard(Board board) {
	enableBlending();

	begin();
	drawCells(board.getCells());
	// drawDices(board.getDices());

	end();
    }

    private void drawDices(LinkedList<Dice> dices) {
	TextureRegion reg = null;
	for (Dice dice : dices) {
	    draw(reg, dice.x, dice.y, DICE_SIZE, DICE_SIZE);
	}
    }

    private void drawCells(Cell[] cells) {
	for (Cell cell : cells) drawCell(cell);
    }

    private void drawCell(Cell cell) {
	Hexagon h = cell.getHex();
	
	
	float[] vertices = {h.getT().x, h.getT().y, 
		h.getTL().x, h.getTL().y, 
		h.getBL().x, h.getBL().y, 
		h.getB().x, h.getB().y, 
		h.getBR().x, h.getBR().y, 
		h.getTR().x, h.getTR().y};
	renderer.begin(ShapeType.Line);
	renderer.setColor(CELLCOLOR);	
	renderer.polygon(vertices);
	renderer.end();
	//drawTriangle(h.getT(), h.getTL(), h.getTR(), CELLCOLOR);
	//drawTriangle(h.getB(), h.getBL(), h.getBR(), CELLCOLOR);
	//fillRectangle(h.getCenter().x, h.getCenter().y, h.getWidth(), h.getHeight(), CELLCOLOR);
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
	renderer.triangle(a.x, a.y, b.x, b.y, c.x, c.y, color, color, color);
	renderer.end();
    }

    @Override
    public void dispose() {
	super.dispose();
	renderer.dispose();
    }

}
