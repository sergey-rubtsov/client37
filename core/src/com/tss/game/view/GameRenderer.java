package com.tss.game.view;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.tss.game.model.Board;
import com.tss.game.model.Cell;
import com.tss.game.model.Dice;

public class GameRenderer extends SpriteBatch {

    ShapeRenderer renderer;

    Sprite backgroundSprite;

    TextureRegion question;

    public class Impulse {

    }

    public GameRenderer() {
        super();
        this.renderer = new ShapeRenderer(512);
        // backgroundSprite = new Sprite(Assets.background);
    }

    public void renderBackground() {
        disableBlending();
        begin();
        // backgroundSprite.draw(this);
        draw(Assets.backgroundRegion, 0, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
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
        drawCells(board.getAllCells());
        drawDices(board.getDices());
    }

    private void drawLocked(Dice dice) {
        Cell cell = dice.getCell();
        Cell[] neigbors = cell.getNeighbors();
        int nDices = 0;
        for (Cell n : neigbors) {
            if (n.getDice() != null)
                nDices++;
        }
        if (nDices == dice.getNumber().ordinal()) {
            Color cellCol = new Color(dice.getColor().r, dice.getColor().g, dice.getColor().b, 0.8f);
            drawCellMain(cell, cellCol);
        }
    }

    private void drawDices(LinkedList<Dice> dices) {
        for (Dice dice : dices) {
            drawLocked(dice);
            drawDice(dice);
        }
    }

    private void drawDice(Dice dice) {
        renderer.begin(ShapeType.Filled);
        renderer.setColor(dice.getOwner().getColor());
        renderer.rect(dice.getCell().getX() - GameScreen.diceSize / 2, dice.getCell()
                .getY() - GameScreen.diceSize / 2, GameScreen.diceSize, GameScreen.diceSize);
        renderer.end();
        begin();
        draw(getDiceTextureRegion(dice.getNumber()), dice.getCell().getX()
                        - GameScreen.diceSize / 2, dice.getCell().getY() - GameScreen.diceSize / 2,
                GameScreen.diceSize, GameScreen.diceSize);
        end();
    }

    private TextureRegion getDiceTextureRegion(Dice.Number dice) {
        TextureRegion diceTexture;
        switch (dice) {
            case ONE:
                diceTexture = Assets.dice1;
                break;
            case TWO:
                diceTexture = Assets.dice2;
                break;
            case THREE:
                diceTexture = Assets.dice3;
                break;
            case FOUR:
                diceTexture = Assets.dice4;
                break;
            case FIVE:
                diceTexture = Assets.dice5;
                break;
            case SIX:
                diceTexture = Assets.dice6;
                break;
            default:
                diceTexture = Assets.question;
                break;
        }
        return diceTexture;
    }

    private void drawCells(Cell[] cells) {
        for (Cell cell : cells)
            drawCell(cell);
    }

    private void drawCell(Cell c) {
        drawCellMain(c);
        drawCellBorder(c);
    }

    private void drawCellMain(Cell c) {
        drawCellMain(c, GameScreen.CELL_COLOR[c.getColor()]);
    }

    private void drawCellMain(Cell c, Color color) {
        renderer.begin(ShapeType.Filled);
        renderer.setColor(color);
        renderer.triangle(c.getB().x, c.getB().y, c.getBL().x, c.getBL().y,
                c.getBR().x, c.getBR().y);
        renderer.rect(c.getBL().x, c.getBL().y, GameScreen.xUnit * 2, GameScreen.yUnit * 2);
        renderer.triangle(c.getT().x, c.getT().y, c.getTL().x, c.getTL().y,
                c.getTR().x, c.getTR().y);
        renderer.end();
    }

    private void drawCellBorder(Cell c) {
        float[] vertices = {c.getT().x, c.getT().y, c.getTL().x, c.getTL().y,
                c.getBL().x, c.getBL().y, c.getB().x, c.getB().y, c.getBR().x,
                c.getBR().y, c.getTR().x, c.getTR().y};
        renderer.begin(ShapeType.Line);
        renderer.setColor(GameScreen.CBCOLOR);
        renderer.polygon(vertices);
        renderer.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        renderer.dispose();
    }

    public void render(TextureRegion button, Rectangle buttonBounds) {
        begin();
        draw(button, buttonBounds.getX(), buttonBounds.getY(),
                buttonBounds.getWidth(), buttonBounds.getHeight());
        end();
    }

    public void renderTake(int takenDice, Color color, Rectangle r) {
        renderer.begin(ShapeType.Filled);
        renderer.setColor(color);
        renderer.rect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        renderer.end();
        begin();
        draw(getDiceTextureRegion(Dice.Number.values()[takenDice]), r.getX(),
                r.getY(), r.getWidth(), r.getHeight());
        end();
    }
}
