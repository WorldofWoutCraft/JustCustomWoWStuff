package com.woutwoot.jcwows.checkedevents;

import com.woutwoot.jcwows.Main;
import com.woutwoot.jcwows.tools.jpushbullet.Devices;
import com.woutwoot.jcwows.tools.jpushbullet.PushbulletClient;
import com.woutwoot.jcwows.tools.jpushbullet.PushbulletDevice;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

/**
 * Created by Wout on 11/12/2014 - 13:36.
 */
public class PlayerJoinHandler implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        int players = Main.getInstance().getServer().getOnlinePlayers().size();
        if (players >= 10) {
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

}
