package com.tss.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static Texture background;
    public static Texture items;
    public static Texture dice1t;
    public static Texture dice2t;
    public static Texture dice3t;
    public static Texture dice4t;
    public static Texture dice5t;
    public static Texture dice6t;
    public static Texture playt;
    public static Texture flagt;
    public static Texture questiont;
    public static TextureRegion dice1;
    public static TextureRegion dice2;
    public static TextureRegion dice3;
    public static TextureRegion dice4;
    public static TextureRegion dice5;
    public static TextureRegion dice6;
    public static TextureRegion question;
    public static TextureRegion backgroundRegion;
    public static TextureRegion buttonRoll;
    public static TextureRegion buttonStart;
    public static TextureRegion buttonGiveUp;
    public static TextureRegion play;
    public static TextureRegion flag;

    public static Texture loadTexture(String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load() {
        background = loadTexture("img/background.png");
        items = loadTexture("img/items.png");
        playt = loadTexture("img/play.png");
        flagt = loadTexture("img/flag.png");
        dice1t = loadTexture("img/dice1.png");
        dice2t = loadTexture("img/dice2.png");
        dice3t = loadTexture("img/dice3.png");
        dice4t = loadTexture("img/dice4.png");
        dice5t = loadTexture("img/dice5.png");
        dice6t = loadTexture("img/dice6.png");
        questiont = loadTexture("img/question.png");
        play = new TextureRegion(playt, 0, 0, 166, 166);
        flag = new TextureRegion(flagt, 0, 0, 58, 85);
        dice1 = new TextureRegion(dice1t, 0, 0, 104, 104);
        dice2 = new TextureRegion(dice2t, 0, 0, 104, 104);
        dice3 = new TextureRegion(dice3t, 0, 0, 104, 104);
        dice4 = new TextureRegion(dice4t, 0, 0, 104, 104);
        dice5 = new TextureRegion(dice5t, 0, 0, 104, 104);
        dice6 = new TextureRegion(dice6t, 0, 0, 104, 104);
        question = new TextureRegion(questiont, 0, 0, 104, 104);
        backgroundRegion = new TextureRegion(background, 0, 0, 15, 15);
        buttonGiveUp = new TextureRegion(items, 64, 64, 64, 64);
        buttonStart = new TextureRegion(items, 0, 0, 64, 64);
        buttonRoll = new TextureRegion(items, 64, 0, 64, 64);
    }

}
