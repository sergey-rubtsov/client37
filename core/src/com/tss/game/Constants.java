package com.tss.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public interface Constants {
    public static final int WIDTH = Gdx.graphics.getWidth();
    public static final int HEIGHT = Gdx.graphics.getHeight();

    public static final int BOARD_SIZE = WIDTH;

    public static final int BOARD_Y_BEGIN = (HEIGHT - WIDTH) / 2;

    public static final int BOARD_Y_END = HEIGHT - (HEIGHT - WIDTH) / 2;

    public static final int CHAT_HEIGHT = BOARD_Y_BEGIN;

    public static final int TOP_HEIGHT = BOARD_Y_BEGIN;

    public static final float apothem = (float) Math.cos(Math.PI / 6);

    public static final float xUnit = BOARD_SIZE / 16;

    public static final float yUnit = (BOARD_SIZE * apothem) / 24;

    public static final int DICE_COUNT = 12;

    public static final float DICE_SIZE = xUnit * 0.8f;

    public final Color CMCOLOR = new Color(1f, 0.9f, 0f, 1);
    public final Color CCCOLOR = new Color(1f, 0.2f, 0f, 1);
    public final Color CBCOLOR = new Color(0.1f, 0.1f, 0.1f, 1);

    public final Color[] CELL_COLOR = { CCCOLOR, CMCOLOR, CBCOLOR };

    public final Color CELLCOLOR = new Color(1f, 0f, 0f, 1);
    public final Color MENUCOLOR = new Color(0.5f, 0.5f, 0.9f, 1);
    public final Color DEFCOLOR = new Color(0.6f, 0.6f, 0.6f, 1);

    public final Color RED_DICE_COLOR = new Color(0.9f, 0f, 0f, 1);
    public final Color GREEN_DICE_COLOR = new Color(0f, 0.9f, 0f, 1);
    public final Color BLUE_DICE_COLOR = new Color(0f, 0f, 0.9f, 1);

}
