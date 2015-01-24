package com.tss.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static Texture background;
    public static Texture items;
    public static TextureRegion backgroundRegion;
    public static TextureRegion buttonRoll;
    public static TextureRegion buttonStart;
    public static TextureRegion buttonGiveUp;

    public static Texture loadTexture(String file) {
	return new Texture(Gdx.files.internal(file));
    }

    public static void load() {
	background = loadTexture("background.png");
	items = loadTexture("items.png");
	backgroundRegion = new TextureRegion(background, 0, 0, 354, 354);
	buttonGiveUp = new TextureRegion(items, 64, 64, 64, 64);
	buttonStart = new TextureRegion(items, 0, 0, 64, 64);
	buttonRoll = new TextureRegion(items, 64, 0, 64, 64);
    }

}
