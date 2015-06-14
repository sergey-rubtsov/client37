package com.tss.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tss.game.view.Assets;
import com.tss.game.view.GameRenderer;
import com.tss.game.view.GameScreen;

public class Client extends Game {

    GameRenderer batch;

    private int width = 320;
    private int height = 480;

    public static Device device = null;

    @Override
    public void create() {
        Assets.load();
        if (Device.DESKTOP.equals(device)) {
            //setWidth(Gdx.graphics.getWidth());
            //setHeight(Gdx.graphics.getHeight());
        }
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
