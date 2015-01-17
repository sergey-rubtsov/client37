package com.tss.game.view;

import org.java_websocket.WebSocket.READYSTATE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.tss.game.Client;
import com.tss.game.Commands;
import com.tss.game.Constants;
import com.tss.game.GameSocket;
import com.tss.game.model.Board;

public class GameScreen implements Screen, Constants, Commands {
    
  GameSocket socket;  
  Client game;
  OrthographicCamera guiCam;
  GameRenderer batcher;
  Board board;
  Vector3 touchPoint;
  
  public GameScreen(Client game) {
    this.game = game;
    this.socket = game.getSocket();
    batcher = new GameRenderer();
    touchPoint = new Vector3();
    this.board = new Board();
    socket.connect();
    guiCam = new OrthographicCamera(WIDTH, HEIGHT);
    guiCam.position.set(WIDTH / 2, HEIGHT / 2, 0);

  }

  @Override
  public void show() {
      if(this.socket.getReadyState() == READYSTATE.OPEN) {
	  this.socket.send(PING);
      }
  }

  @Override
  public void render(float delta) {
    update(delta);
    draw();
  }

  private void update(float delta) {
    if (Gdx.input.justTouched()) {
      guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
      processTouch(touchPoint);
    }
    processMessages();
  }
  
  private void processTouch(Vector3 touchPoint) {
    
  }

  private void processMessages() {    
      
  }

  private void draw() {
	GL20 gl = Gdx.gl;
	gl.glClearColor(0, 0, 1, 1);
	gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	batcher.renderBackground();
	batcher.renderBoard(board);

	//debugRenderer.render(field.getWorld(), guiCam.combined);

	guiCam.update();
	batcher.setProjectionMatrix(guiCam.combined);
	batcher.enableBlending();
	batcher.begin();		

	batcher.end();

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
