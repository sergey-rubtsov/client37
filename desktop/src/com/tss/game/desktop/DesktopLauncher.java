package com.tss.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tss.game.Client;
import com.tss.game.Device;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.vSyncEnabled = true;
        config.title = "37.6";
        config.height = 480;
        config.width = 320;
        Client.device = Device.DESKTOP;
        new LwjglApplication(new Client(), config);
    }
}
