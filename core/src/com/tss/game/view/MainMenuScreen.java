package com.tss.game.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.tss.game.Constants;
import com.tss.game.control.commands.Commands;

public class MainMenuScreen implements Screen, Constants, Commands {
    
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonPlay, buttonOptions, buttonChat, buttonExit;
    private BitmapFont white, black;
    private Label heading;
    
    public MainMenuScreen() {
	super();
    }

    @Override
    public void show() {
	white = new BitmapFont();
	black = new BitmapFont();
    }

    @Override
    public void render(float delta) {
	
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
	
    }

}
