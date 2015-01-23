package com.tss.game.view;

import java.net.URISyntaxException;

import org.java_websocket.WebSocket.READYSTATE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tss.game.Client;
import com.tss.game.Constants;
import com.tss.game.control.Commands;
import com.tss.game.control.GameProcessor;
import com.tss.game.control.GameSocket;
import com.tss.game.control.InputListener.Button;
import com.tss.game.model.Board;

public class GameScreen implements Screen, Constants, Commands {
    
    FPSLogger fps;    
    Client client;
    OrthographicCamera guiCam;
    GameRenderer batcher;
    GameProcessor controller;
    Board board;
    Rectangle diceButtonBounds;
    Vector3 touchPoint;
    
    public GameScreen(Client client) {
	this.client = client;
	this.board = new Board();
	this.controller = new GameProcessor();
	this.board.setListener(controller);
	this.controller.setListener(board);
	batcher = new GameRenderer();
	touchPoint = new Vector3();
	guiCam = new OrthographicCamera(WIDTH, HEIGHT);
	guiCam.position.set(WIDTH / 2, HEIGHT / 2, 0);
	//diceButtonBounds = new Rectangle(WIDTH / 3, BOARD_Y_END - TOP_HEIGHT / 4, WIDTH / 6, TOP_HEIGHT / 2);
	diceButtonBounds = new Rectangle(320 - 64, 480 - 64, 64, 64);
	fps = new FPSLogger();
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
	if (diceButtonBounds.contains(touchPoint.x, touchPoint.y)) {
	    controller.click(Button.DICE);
	} else {
	    board.touch(touchPoint.x, touchPoint.y);
	}
	
    }

    private void draw() {
	batcher.render(board);
	batcher.render(diceButtonBounds);
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
