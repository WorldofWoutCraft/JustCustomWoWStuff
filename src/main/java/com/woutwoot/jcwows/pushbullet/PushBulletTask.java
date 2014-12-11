package com.woutwoot.jcwows.pushbullet;

import com.woutwoot.jcwows.Main;
import com.woutwoot.jcwows.tools.jpushbullet.Devices;
import com.woutwoot.jcwows.tools.jpushbullet.PushbulletClient;
import com.woutwoot.jcwows.tools.jpushbullet.PushbulletDevice;

import java.io.IOException;

/**
 * Created by Wout on 11/12/2014 - 15:36.
 */
public class PushBulletTask implements Runnable {

    private int players;

    public PushBulletTask(int players) {
        this.players = players;
    }

    @Override
    public void run() {
        PushbulletClient client = new PushbulletClient(Main.getInstance().getConfig().getString("pushbullet_API_key"));
        try {
            PushbulletDevice devices = client.getDevices();
            for (Devices d : devices.getDevices()) {
                client.sendNote(true, d.getIden(), players + " online on WoW", "The World of WoutCraft has just reached a player count of " + players + "!");
            }
        } catch (IllegalStateException | IOException e) {
            return;
        }
    }

}
