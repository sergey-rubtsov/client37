package com.tss.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tss.game.Client;
import com.tss.game.control.GameProcessor;
import com.tss.game.control.commands.Commands;
import com.tss.game.model.Board;

public class GameScreen implements Screen, Commands {

    public static int width = 320;
    public static int height = 480;

    public static int boardSize = width;
    public static int boardYBegin = (height - width) / 2;
    public static int boardYEnd = height - (height - width) / 2;
    public static int chatHeight = boardYBegin;
    public static int topHeight = boardYBegin;

    public static final float APOTHEM = (float) Math.cos(Math.PI / 6);

    public static float xUnit = boardSize / 16;
    public static float yUnit = (boardSize * APOTHEM) / 24;
    public static float diceSize = xUnit * 0.9f;

    public static final int DICE_COUNT = 12;

    public static final Color CMCOLOR = new Color(1f, 0.9f, 0f, 1);
    public static final Color CCCOLOR = new Color(1f, 0.2f, 0f, 1);
    public static final Color CBCOLOR = new Color(0.1f, 0.1f, 0.1f, 1);

    public static final Color CELLCOLOR = new Color(1f, 0f, 0f, 1);
    public static final Color MENUCOLOR = new Color(0.5f, 0.5f, 0.9f, 1);
    public static final Color DEFCOLOR = new Color(0.6f, 0.6f, 0.6f, 1);

    public static final Color[] CELL_COLOR = {CCCOLOR, CMCOLOR, CBCOLOR, CELLCOLOR, MENUCOLOR, DEFCOLOR};

    public final Color RED_DICE_COLOR = new Color(0.9f, 0f, 0f, 1);
    public final Color GREEN_DICE_COLOR = new Color(0f, 0.9f, 0f, 1);
    public final Color BLUE_DICE_COLOR = new Color(0f, 0f, 0.9f, 1);

    FPSLogger fps;
    Client client;
    OrthographicCamera guiCam;
    GameRenderer batcher;
    GameProcessor controller;
    Board board;
    Rectangle newGameButtonBounds;
    Rectangle rollButtonBounds;
    Rectangle giveUpButtonBounds;
    Vector3 touchPoint;

    public GameScreen(Client client) {
        width = client.getWidth();
        height = client.getHeight();
        this.client = client;
        this.board = new Board();
        this.controller = new GameProcessor();
        this.board.setListener(controller);
        this.controller.setListener(board);
        batcher = client.getBatch();
        touchPoint = new Vector3();
        guiCam = new OrthographicCamera(width, height);
        guiCam.position.set(width / 2, height / 2, 0);
        giveUpButtonBounds = new Rectangle(width / 6 - width / 24, boardYEnd - topHeight / 4, width / 12, width / 12);
        newGameButtonBounds = new Rectangle(width / 2 - width / 24, boardYEnd - topHeight / 4, width / 12, width / 12);
        rollButtonBounds = new Rectangle(width * 5 / 6 - width / 24, boardYEnd - topHeight / 4, width / 12, width / 12);
        //fps = new FPSLogger();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update();
        draw();
        //fps.log();
    }

    private void update() {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(),
                    0));
            processTouch(touchPoint);
        }
        controller.update();
    }

    private void processTouch(Vector3 touchPoint2) {
        if (rollButtonBounds.contains(touchPoint.x, touchPoint.y)) {
            controller.take();
        } else if (newGameButtonBounds.contains(touchPoint.x, touchPoint.y)) {
            controller.bot();
        } else if (giveUpButtonBounds.contains(touchPoint.x, touchPoint.y)) {
            controller.quit();
        } else {
            board.touch(touchPoint.x, touchPoint.y);
        }
    }

    private void draw() {
        batcher.render(board);
        batcher.renderTake(controller.getState().getTakenDice(), controller.getState().getMyColor(), rollButtonBounds);
        batcher.render(Assets.play, newGameButtonBounds);
        batcher.render(Assets.flag, giveUpButtonBounds);
        guiCam.update();
        batcher.setProjectionMatrix(guiCam.combined);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batcher.dispose();
        controller.close();
    }

}
