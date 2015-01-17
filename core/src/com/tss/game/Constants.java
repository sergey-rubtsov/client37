package com.tss.game;

import com.badlogic.gdx.Gdx;

public interface Constants {	
	public static final int GRAPHICS_WIDTH = Gdx.graphics.getWidth();
	public static final int GRAPHICS_HEIGHT = Gdx.graphics.getHeight();
	public static final int TOP_HEIGHT_RATIO = 5;
	public static final int BOARD_HEIGHT_RATIO = 13;
	public static final int BOARD_WIDTH_RATIO = 16;
	public static final int TOP_HEIGHT = (GRAPHICS_WIDTH  * TOP_HEIGHT_RATIO) / BOARD_WIDTH_RATIO;
	public static final int BOARD_HEIGHT = (GRAPHICS_WIDTH  * BOARD_HEIGHT_RATIO) / BOARD_WIDTH_RATIO;
	public static final int CHAT_HEIGHT = GRAPHICS_HEIGHT - (TOP_HEIGHT + BOARD_HEIGHT);
	public static final float CELL_EDGE = GRAPHICS_WIDTH / BOARD_WIDTH_RATIO;
	public static final float CELL_RADIUS = 0.866f;
	public static final int DICE_COUNT = 12;
	public static final float DICE_SIZE = CELL_RADIUS * 1.414f;
	public static final int K = 25;

}
