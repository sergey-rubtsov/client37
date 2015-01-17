package com.tss.game;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.drafts.Draft_10;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tss.game.view.Assets;
import com.tss.game.view.GameScreen;

public class Client extends Game {

    private GameSocket socket;
    private SpriteBatch batch;

    @Override
    public void create() {
	Assets.load();
	setBatch(new SpriteBatch());
	try {
	    socket = new GameSocket(new URI(Commands.SERVER_URL), new Draft_10());
	} catch (URISyntaxException e) {
	    
	}
	setScreen(new GameScreen(this));
    }

    public GameSocket getSocket() {
        return socket;
    }

    @Override
    public void render() {
	super.render();
    }

    public SpriteBatch getBatch() {
	return batch;
    }

    public void setBatch(SpriteBatch batch) {
	this.batch = batch;
    }

}
