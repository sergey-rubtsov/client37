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

    public static final int WIDTH = 320;
    public static final int HEIGHT = 480;

    public static final int BOARD_SIZE = WIDTH;

    public static final int BOARD_Y_BEGIN = (HEIGHT - WIDTH) / 2;

    public static final int BOARD_Y_END = HEIGHT - (HEIGHT - WIDTH) / 2;

    public static final int CHAT_HEIGHT = BOARD_Y_BEGIN;

    public static final int TOP_HEIGHT = BOARD_Y_BEGIN;

    public static final float apothem = (float) Math.cos(Math.PI / 6);

    public static final float xUnit = BOARD_SIZE / 16;

    public static final float yUnit = (BOARD_SIZE * apothem) / 24;

    public static final int DICE_COUNT = 12;

    public static final float DICE_SIZE = xUnit * 0.9f;

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
    public final Rectangle newGameButtonBounds;
    Rectangle rollButtonBounds;
    Rectangle giveUpButtonBounds;
    Vector3 touchPoint;

    public GameScreen(Client client) {
        this.client = client;
        this.board = new Board();
        this.controller = new GameProcessor();
        this.board.setListener(controller);
        this.controller.setListener(board);
        batcher = client.getBatch();
        touchPoint = new Vector3();
        guiCam = new OrthographicCamera(WIDTH, HEIGHT);
        guiCam.position.set(WIDTH / 2, HEIGHT / 2, 0);
        giveUpButtonBounds = new Rectangle(WIDTH / 6 - WIDTH / 24, BOARD_Y_END - TOP_HEIGHT / 4, WIDTH / 12, WIDTH / 12);
        newGameButtonBounds = new Rectangle(WIDTH / 2 - WIDTH / 24, BOARD_Y_END - TOP_HEIGHT / 4, WIDTH / 12, WIDTH / 12);
        rollButtonBounds = new Rectangle(WIDTH * 5 / 6 - WIDTH / 24, BOARD_Y_END - TOP_HEIGHT / 4, WIDTH / 12, WIDTH / 12);
        //fps = new FPSLogger();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
        //fps.log();
    }

    private void update(float delta) {
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
