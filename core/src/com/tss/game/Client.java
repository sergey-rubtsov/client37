package com.tss.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tss.game.view.Assets;
import com.tss.game.view.GameScreen;

public class Client extends Game {

    private SpriteBatch batch;

    @Override
    public void create() {
	Assets.load();
	setBatch(new SpriteBatch());
	setScreen(new GameScreen(this));
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
