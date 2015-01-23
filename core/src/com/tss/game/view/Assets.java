package com.tss.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static Texture background;
    public static Texture items;
    public static TextureRegion backgroundRegion;
    public static TextureRegion button;

    public static Texture loadTexture(String file) {
	return new Texture(Gdx.files.internal(file));
    }

    public static void load() {
	background = loadTexture("pattern2.png");
	items = loadTexture("items.png");
	backgroundRegion = new TextureRegion(background, 0, 0, 354, 354);
	button = new TextureRegion(items, 64, 64, 64, 64);
    }

}
