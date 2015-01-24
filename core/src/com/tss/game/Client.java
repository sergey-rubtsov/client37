package com.tss.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tss.game.view.Assets;
import com.tss.game.view.GameRenderer;
import com.tss.game.view.GameScreen;

public class Client extends Game {

    GameRenderer batch;

    @Override
    public void create() {
	Assets.load();
	setBatch(new GameRenderer());
	setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
	super.render();
    }

    public GameRenderer getBatch() {
	return batch;
    }

    public void setBatch(GameRenderer batch) {
	this.batch = batch;
    }

}
