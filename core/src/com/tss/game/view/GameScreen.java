package com.tss.game.view;

import java.net.URISyntaxException;

import org.java_websocket.WebSocket.READYSTATE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.tss.game.Client;
import com.tss.game.Constants;
import com.tss.game.control.Commands;
import com.tss.game.control.GameProcessor;
import com.tss.game.control.GameSocket;
import com.tss.game.model.Board;
import com.tss.game.model.Cell;

public class GameScreen implements Screen, Constants, Commands {
    
    FPSLogger fps;

    GameSocket socket;
    Client client;
    OrthographicCamera guiCam;
    GameRenderer batcher;
    GameProcessor game;
    Board board;
    Vector3 touchPoint;
    
    public GameScreen(Client client) {
	this.client = client;
	this.board = new Board();
	this.game = new GameProcessor(this, board);
	try {
	    socket = new GameSocket(game);
	} catch (URISyntaxException e) {
	    
	}
	batcher = new GameRenderer();
	touchPoint = new Vector3();	
	socket.connect();
	guiCam = new OrthographicCamera(WIDTH, HEIGHT);
	guiCam.position.set(WIDTH / 2, HEIGHT / 2, 0);
	fps = new FPSLogger();
    }

    @Override
    public void show() {
	if (this.socket.getReadyState() == READYSTATE.OPEN) {
	    this.socket.send(PING);
	}
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
    }

    private void processTouch(Vector3 touchPoint2) {
	Cell cell = board.getProximate(touchPoint.x, touchPoint.y);
	if (cell.contains(touchPoint.x, touchPoint.y)) {
	    game.touch(cell);            
	} else {
	    
	}
    }

    private void draw() {
	batcher.render(board);
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
	socket.close();
    }

}
