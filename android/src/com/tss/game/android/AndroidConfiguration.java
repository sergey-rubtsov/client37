package com.tss.game.android;

import android.util.DisplayMetrics;
import android.view.Display;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.tss.game.ApplicationConfiguration;

public class AndroidConfiguration extends AndroidApplicationConfiguration implements ApplicationConfiguration {

    private final int width;

    private final int height;

    public AndroidConfiguration(AndroidApplication application) {
        if(android.os.Build.VERSION.SDK_INT < 10) {
            Display display = application.getWindowManager().getDefaultDisplay();
            width = display.getWidth();
            height = display.getHeight();
        } else {
            DisplayMetrics metrics = new DisplayMetrics();
            application.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            width = metrics.widthPixels;
            height = metrics.heightPixels;
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
